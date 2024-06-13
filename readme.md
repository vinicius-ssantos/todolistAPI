# Todo List API

Este é um projeto de uma API de gerenciamento de tarefas, desenvolvido com Java, Spring Boot e Maven.

## Pré-requisitos

- Java 11
- Maven
- MySQL

## Configuração do ambiente

1. Instale o Java 11. Você pode baixar o Java 11 [aqui](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. Instale o Maven. Você pode baixar o Maven [aqui](https://maven.apache.org/download.cgi).

3. Instale o MySQL. Você pode baixar o MySQL [aqui](https://dev.mysql.com/downloads/installer/).

## Como executar o projeto

1. Clone o repositório para a sua máquina local usando `git clone https://github.com/vinicius-ssantos/todolist.git`.

2. Navegue até o diretório do projeto usando `cd todolist`.

3. Execute o comando `mvn clean install` para construir o projeto e instalar as dependências.

4. Antes de iniciar a aplicação, certifique-se de que o MySQL está em execução.

5. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints

A API possui os seguintes endpoints:

- `POST /tasks`: Cria uma nova tarefa.
- `PUT /tasks/{id}`: Atualiza uma tarefa existente.
- `DELETE /tasks/{id}`: Deleta uma tarefa existente.
- `GET /tasks/{id}`: Obtém uma tarefa específica pelo ID.
- `PATCH /tasks/{id}/complete`: Marca uma tarefa como completa.

### Para mais detalhes sobre os endPoints [Clique aqui](documentos/endpoints.md)

## Documentação da API

A documentação da API está disponível em `http://localhost:8080/swagger-ui.html`.

## Testes

Para executar os testes, use o comando `mvn test`.

## Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.