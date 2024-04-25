package org.travis.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @ClassName AuthApplication
 * @Description AuthApplication
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "org.travis.api")
@ComponentScan("org.travis.api")
@ComponentScan(
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {TypeExcludeFilter.class}
        ), @ComponentScan.Filter(
                type = FilterType.CUSTOM,
                classes = {AutoConfigurationExcludeFilter.class}
        )}
)
public class AuthApplication {
    public static void main(String[] args) throws UnknownHostException {
        // Run and get environment variables
        ConfigurableEnvironment environment = SpringApplication.run(AuthApplication.class, args).getEnvironment();
        // Judge protocol
        String protocol = environment.getProperty("server.ssl.key-store") != null ? "https" : "http";

        System.out.println("\n" +
                "  ______                        _____ __                       _____       __                 \n" +
                " /_  __/__  ____ _____ ___     / ___// /_  ____ _________     / ___/____  / /_  ___  ________ \n" +
                "  / / / _ \\/ __ `/ __ `__ \\    \\__ \\/ __ \\/ __ `/ ___/ _ \\    \\__ \\/ __ \\/ __ \\/ _ \\/ ___/ _ \\\n" +
                " / / /  __/ /_/ / / / / / /   ___/ / / / / /_/ / /  /  __/   ___/ / /_/ / / / /  __/ /  /  __/\n" +
                "/_/  \\___/\\__,_/_/ /_/ /_/   /____/_/ /_/\\__,_/_/   \\___/   /____/ .___/_/ /_/\\___/_/   \\___/ \n" +
                "                                                                /_/                           \n"
        );
        System.out.println("(♥◠‿◠)ﾉﾞ  TSS-Auth Run Successfully!   ლ(´ڡ`ლ)ﾞ  ");
        System.out.printf(
                "\n---------------------------------------------------------------------------------------\n\t" +
                        "Application '%s' is running! Access URLs:\n\t" +
                        "Local: \t\t%s://localhost:%s\n\t" +
                        "External: \t%s://%s:%s\n\t" +
                        "Profile(s): \t%s" +
                        "\n---------------------------------------------------------------------------------------\n",
                environment.getProperty("spring.application.name"),
                protocol,
                environment.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                Arrays.toString(environment.getActiveProfiles())
        );
    }
}
