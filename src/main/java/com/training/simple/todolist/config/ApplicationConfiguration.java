package com.training.simple.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("America/Sao_Paulo"));
    }
}
