package com.product.productreviewservice.connection;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class AppConfig {

    @Value("${spring.data.cassandra.cloud.secure-connect-bundle}")
    private String secureConnectBundlePath;

    @Value("${astra.db.keyspace}")
    private String astraKeySpace;

    @Value("${astra.db.application.token}")
    private String astraToken;

    /*
     * Use the standard Cassandra driver API to create a com.datastax.oss.driver.api.core.CqlSession instance.
     */
    public @Bean CqlSession session() {
        CqlSessionBuilder builder = CqlSession.builder();
        builder.withCloudSecureConnectBundle(Paths.get(secureConnectBundlePath));
        builder.withAuthCredentials("token", astraToken);
        builder.withKeyspace(astraKeySpace);
        return builder.build();
    }
}