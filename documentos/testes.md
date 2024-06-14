# Testes

Este projeto utiliza o JUnit e o Mockito para a realização de testes unitários e de integração. Os testes são uma parte crucial do desenvolvimento de software, pois garantem que o código funciona como esperado e facilitam a detecção de bugs e problemas.

## Executando os Testes

Para executar os testes, você pode usar o comando `mvn test` no diretório raiz do projeto. Este comando irá executar todos os testes presentes no projeto e fornecerá um relatório sobre quais testes passaram e quais falharam.

## Estrutura dos Testes

Os testes estão organizados em classes de teste, cada uma correspondendo a uma classe específica no código fonte. Cada classe de teste contém vários métodos de teste, cada um testando um aspecto específico da classe correspondente.

Por exemplo, se tivermos uma classe `TaskService`, teremos uma classe de teste correspondente chamada `TaskServiceTest`. Esta classe de teste pode conter métodos como `testCreateTask()`, `testUpdateTask()`, etc.

## Testes Unitários

Os testes unitários testam a menor unidade de código, que geralmente é um método. Eles são usados para garantir que cada parte do código funcione corretamente de forma isolada. No contexto deste projeto, os testes unitários são usados para testar os métodos dos serviços e dos repositórios.

## Testes de Integração

Os testes de integração testam a interação entre várias partes do código para garantir que elas funcionem corretamente juntas. No contexto deste projeto, os testes de integração são usados para testar os controladores, simulando as requisições HTTP e verificando as respostas.

## Mocking

O projeto utiliza a biblioteca Mockito para "mockar" dependências nas classes de teste. Isso significa que podemos simular o comportamento de outras classes que a classe que estamos testando depende. Isso é útil porque nos permite isolar a classe que estamos testando e garantir que os testes não falhem devido a problemas nas classes dependentes.

## Cobertura de Testes

A cobertura de testes é uma medida de quanto do nosso código é testado pelos testes. Embora uma cobertura de teste de 100% seja ideal, na prática, é importante garantir que as partes mais críticas do código sejam testadas. Você pode verificar a cobertura de testes do projeto usando ferramentas como o JaCoCo.

Lembre-se, o objetivo dos testes não é apenas alcançar uma alta cobertura de testes, mas garantir que o código funcione corretamente e facilitar a detecção de bugs e problemas.