package com.flyhigh.framework.permission;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.flyhigh.common.threadLocal.SelfUpdateOrDeleteHolder;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class UpdateAndDeleteInterceptor implements InnerInterceptor {

    protected static final Map<String, MappedStatement> queryByIdMsCache = new ConcurrentHashMap();

    private static final String QUERY_ID_SQL = "select id from %s where id in (%s) and create_user_id = %s";

    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        if (!canIntercept(ms)) {
            return true;
        }
        log.info("===== UpdateAndDeleteInterceptor ");

        MappedStatement newMs = this.buildQueryByIdMappedStatement(ms);
        try {
            String tableName = getTableName(ms, parameter);
            return runSelfUpdateOrDeleteCheck(tableName, newMs, executor);
        } catch (Exception e) {
            log.error("获取表名失败！ms={}", ms.getId(), e);
        }
        return false;
    }

    /**
     * 解析表名
     *
     * @param ms
     * @param parameter
     * @return
     * @throws JSQLParserException
     */
    private String getTableName(MappedStatement ms, Object parameter) throws JSQLParserException {
        BoundSql sourceSql = ms.getBoundSql(parameter);
        Statement stmt = CCJSqlParserUtil.parse(sourceSql.getSql());
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(stmt);
        // 简单处理取第一个表
        return tableList.get(0);
    }

    /**
     * 执行校验
     *
     * @param tableName
     * @param ms
     * @param executor
     */
    private boolean runSelfUpdateOrDeleteCheck(String tableName, MappedStatement ms, Executor executor) throws SQLException {
        // 拿到数据持有者中保存的信息
        SelfUpdateOrDeleteHolder.Info info = SelfUpdateOrDeleteHolder.get();
        if (info.getUserId() == null || info.getDataId() == null) {
            log.warn("数据持有者中没有数据！tableName={}, ms={}", tableName, ms.getId());
            return false;
        }
        boolean tableNameInEntity = false;
        for (Class entity : info.getEntities()) {
            String entityTableName = SqlHelper.table(entity).getTableName();
            // 当前表和数据holder中申明的表一致，则向下执行
            if (tableName.equals(entityTableName)) {
                tableNameInEntity = true;
                break;
            }
        }
        if (!tableNameInEntity) {
            // 表名不是注解标注的实体的表，直接执行
            return true;
        }
        String queryByIdSqlStr = autoQueryByIdSql(tableName, info);
        BoundSql queryByIdSql = new BoundSql(ms.getConfiguration(), queryByIdSqlStr, Lists.newArrayList(), info.getDataId());

        PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(queryByIdSql);
        PluginUtils.setAdditionalParameter(queryByIdSql, mpBoundSql.additionalParameters());

        // 执行
        RowBounds rowBounds = new RowBounds(0, 1);
        CacheKey cacheKey = executor.createCacheKey(ms, info.getDataId(), rowBounds, queryByIdSql);
        List<Object> res = executor.query(ms, info.getDataId(), rowBounds, Executor.NO_RESULT_HANDLER, cacheKey, queryByIdSql);
        return res.size() > 0;
    }

    /**
     * 查询的sql
     *
     * @param tableName
     * @return
     * @throws JSQLParserException
     */
    private String autoQueryByIdSql(String tableName, SelfUpdateOrDeleteHolder.Info info) {
        Object resDataId = info.getDataId();
        if (info.getDataId() instanceof Collection) {
            Collection<Object> dataIds = (Collection<Object>) info.getDataId();
            StringBuilder sb = new StringBuilder();
            for (Object dataId : dataIds) {
                sb.append(dataId);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            resDataId = sb.toString();
        }
        return String.format(QUERY_ID_SQL, tableName, resDataId, info.getUserId());
    }

    /**
     * 构建QueryByIdstatement
     *
     * @param ms
     * @return
     */
    private MappedStatement buildQueryByIdMappedStatement(MappedStatement ms) {
        String newMsId = ms.getId() + "_queryById";
        Configuration configuration = ms.getConfiguration();
        return CollectionUtils.computeIfAbsent(queryByIdMsCache, newMsId, (key) -> {
            MappedStatement.Builder builder = new MappedStatement.Builder(configuration, key, ms.getSqlSource(), SqlCommandType.SELECT);
            builder.resource(ms.getResource());
            builder.fetchSize(ms.getFetchSize());
            builder.statementType(ms.getStatementType());
            builder.timeout(ms.getTimeout());
            builder.parameterMap(ms.getParameterMap());
            builder.resultMaps(Collections.singletonList((new ResultMap.Builder(configuration, "mybatis-plus", Long.class, Collections.emptyList())).build()));
            builder.resultSetType(ms.getResultSetType());
            builder.cache(ms.getCache());
            builder.flushCacheRequired(ms.isFlushCacheRequired());
            builder.useCache(ms.isUseCache());
            return builder.build();
        });
    }

    private boolean canIntercept(MappedStatement ms) {
        boolean isUpdateOrDelete = ms.getSqlCommandType() == SqlCommandType.DELETE || ms.getSqlCommandType() == SqlCommandType.UPDATE;
        boolean hasHolderData = SelfUpdateOrDeleteHolder.get() != null;
        return isUpdateOrDelete && hasHolderData;
    }
}
