package com.vinicius_santos.todolist.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    private final DataSource dataSource;

    public CustomHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void doHealthCheck(Health.Builder builder) throws Exception {
        if (isDatabaseConnected()) {
            builder.up()
                    .withDetail("app", "Tudo OK")
                    .withDetail("database", "Connected");
        } else {
            builder.down()
                    .withDetail("app", "Tudo OK")
                    .withDetail("database", "Not Connected");
        }
    }

    private boolean isDatabaseConnected() {
        try (var connection = dataSource.getConnection()) {
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Health getHealth(boolean includeDetails) {
        return super.getHealth(includeDetails);
    }
}