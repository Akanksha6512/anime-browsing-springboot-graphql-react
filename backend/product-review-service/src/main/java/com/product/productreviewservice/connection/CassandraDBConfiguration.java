package com.product.productreviewservice.connection;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.lang.NonNull;

public class CassandraDBConfiguration extends AbstractCassandraConfiguration {
    private final CassandraProperties properties;

    public CassandraDBConfiguration(CassandraProperties properties) {
        this.properties = properties;

    }

    @Override
    protected String getKeyspaceName() {
        return properties.getKeyspaceName();
    }

    @Bean
    @NonNull
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        final CqlSessionFactoryBean cqlSessionFactoryBean = new CqlSessionFactoryBean();
        cqlSessionFactoryBean.setContactPoints(String.join(",", properties.getContactPoints()));
        cqlSessionFactoryBean.setKeyspaceName(properties.getKeyspaceName());
        cqlSessionFactoryBean.setLocalDatacenter(properties.getLocalDatacenter());
        cqlSessionFactoryBean.setPort(properties.getPort());
        cqlSessionFactoryBean.setUsername(properties.getUsername());
        cqlSessionFactoryBean.setPassword(properties.getPassword());
        return cqlSessionFactoryBean;
    }
}
