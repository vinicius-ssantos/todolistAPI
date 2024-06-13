package com.vinicius_santos.todolist.actuator;

import com.vinicius_santos.todolist.config.CustomHealthIndicator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomHealthIndicatorTest {

    @Test
    public void testDoHealthCheck_databaseConnected() throws Exception {
        // Arrange
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenReturn(null);

        CustomHealthIndicator customHealthIndicator = new CustomHealthIndicator(dataSource);
        Health.Builder builder = new Health.Builder();

        // Act
        customHealthIndicator.doHealthCheck(builder);

        // Assert
        assertEquals(Status.UP, builder.build().getStatus());
        assertEquals("Connected", builder.build().getDetails().get("database"));
    }

    @Test
    public void testDoHealthCheck_databaseNotConnected() throws Exception {
        // Arrange
        DataSource dataSource = mock(DataSource.class);
        when(dataSource.getConnection()).thenThrow(SQLException.class);

        CustomHealthIndicator customHealthIndicator = new CustomHealthIndicator(dataSource);
        Health.Builder builder = new Health.Builder();

        // Act
        customHealthIndicator.doHealthCheck(builder);

        // Assert
        assertEquals(Status.DOWN, builder.build().getStatus());
        assertEquals("Not Connected", builder.build().getDetails().get("database"));
    }
}