# Documentação da API

A documentação completa da API do projeto Todo List está disponível através da interface do Swagger UI, que pode ser acessada em `http://localhost:8080/swagger-ui.html` quando o aplicativo está em execução localmente.

## Como acessar a documentação da API

1. Inicie o aplicativo executando o comando `mvn spring-boot:run` no diretório raiz do projeto.
2. Abra um navegador web e navegue até `http://localhost:8080/swagger-ui.html`.

## Navegando pela documentação da API

A interface do Swagger UI apresenta uma lista de todos os endpoints disponíveis na API. Cada endpoint é listado com seu método HTTP (GET, POST, PUT, DELETE, etc.) e o caminho do endpoint.

Ao clicar em um endpoint, você verá detalhes adicionais sobre esse endpoint, incluindo:

- Uma breve descrição do que o endpoint faz.
- O formato do corpo da solicitação (se aplicável).
- Os códigos de status HTTP que o endpoint pode retornar e o que cada um significa.
- O formato do corpo da resposta para cada código de status HTTP (se aplicável).

Você também pode experimentar cada endpoint diretamente na interface do Swagger UI. Para fazer isso, clique no botão "Try it out" (Experimentar), preencha quaisquer parâmetros necessários e clique em "Execute" (Executar). Você verá a solicitação HTTP que o Swagger UI enviou e a resposta que recebeu do servidor.

## Atualizando a documentação da API

A documentação da API é gerada automaticamente com base no código da aplicação. As anotações do Swagger no código (como `@Operation` e `@ApiResponses`) são usadas para gerar a descrição e os detalhes de cada endpoint.

Para atualizar a documentação da API, você deve atualizar essas anotações no código. Por exemplo, para alterar a descrição de um endpoint, você pode alterar o valor da anotação `@Operation` nesse endpoint.

Depois de atualizar as anotações, a documentação da API será atualizada automaticamente na próxima vez que você iniciar o aplicativo.