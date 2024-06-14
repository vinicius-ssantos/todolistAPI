package com.vinicius_santos.todolist.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;


// A classe CustomHealthIndicator é usada para personalizar o indicador de saúde da aplicação.
// Ela é anotada com @Component, indicando que é um bean gerenciado pelo Spring.
// Ela estende a classe AbstractHealthIndicator, que é uma implementação abstrata de um indicador de saúde.
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    // A classe tem uma dependência do DataSource, que é usada para verificar a conexão com o banco de dados.
    private final DataSource dataSource;

    // O construtor da classe recebe o DataSource como parâmetro e o atribui à variável de instância dataSource.
    public CustomHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // O método doHealthCheck é usado para verificar a saúde da aplicação.
    // Ele verifica se a aplicação está conectada ao banco de dados e configura o estado de saúde de acordo.
    @Override
    public void doHealthCheck(Health.Builder builder) throws Exception {
        if (isDatabaseConnected()) {
            builder.up() // Se a aplicação está conectada ao banco de dados, o estado de saúde é configurado como "up".
                    .withDetail("app", "Tudo OK") // Informação adicional sobre a saúde da aplicação.
                    .withDetail("database", "Connected"); // Informação adicional sobre a saúde do banco de dados.
        } else {
            builder.down() // Se a aplicação não está conectada ao banco de dados, o estado de saúde é configurado como "down".
                    .withDetail("app", "Tudo OK") // Informação adicional sobre a saúde da aplicação.
                    .withDetail("database", "Not Connected"); // Informação adicional sobre a saúde do banco de dados.
        }
    }

    // O método isDatabaseConnected é usado para verificar se a aplicação está conectada ao banco de dados.
    private boolean isDatabaseConnected() {
        try (var connection = dataSource.getConnection()) {
            return true; // Se a conexão com o banco de dados for bem-sucedida, o método retorna true.
        } catch (SQLException ex) {
            return false; // Se a conexão com o banco de dados falhar, o método retorna false.
        }
    }

    // O método getHealth é usado para obter o estado de saúde atual da aplicação.
    @Override
    public Health getHealth(boolean includeDetails) {
        return super.getHealth(includeDetails);
    }
}