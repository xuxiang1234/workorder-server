package com.flyhigh.common.utils;

import lombok.Data;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;

import java.util.Map;
import java.util.Properties;

/**
 * 参数解析
 */
public class BracesTemplateParser {

    public static String parse(String template, Map<String, String> map) {
        VariableTokenHandler tokenHandler = new VariableTokenHandler(toProperties(map));
        GenericTokenParser parser = new GenericTokenParser("${", "}", tokenHandler);
        return parser.parse(template);
    }

    @Data
    public static class VariableTokenHandler implements TokenHandler {
        private Properties properties;

        public VariableTokenHandler(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String handleToken(String content) {
            return properties.getProperty(content);
        }
    }

    /**
     * 将map转换成 properties
     *
     * @param map
     * @return
     */
    protected static Properties toProperties(Map<String, String> map) {
        Properties properties = new Properties();
        map.forEach((k, v) -> {
            properties.setProperty(k, v);
        });
        return properties;
    }


}
