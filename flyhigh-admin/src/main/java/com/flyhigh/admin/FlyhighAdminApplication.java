package com.flyhigh.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 启动程序
 *
 * @author flyhigh
 */
@EnableAsync
@ComponentScan(basePackages = "com.flyhigh")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
@EnableScheduling
public class FlyhighAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlyhighAdminApplication.class, args);
        System.out.println("    ___  __           __        _         __       \n" +
                "  .' ..][  |         [  |      (_)       [  |      \n" +
                " _| |_   | |   _   __ | |--.   __   .--./)| |--.   \n" +
                "'-| |-'  | |  [ \\ [  ]| .-. | [  | / /'`\\;| .-. |  \n" +
                "  | |    | |   \\ '/ / | | | |  | | \\ \\._//| | | |  \n" +
                " [___]  [___][\\_:  / [___]|__][___].',__`[___]|__] \n" +
                "              \\__.'               ( ( __))         \n");
    }
}
