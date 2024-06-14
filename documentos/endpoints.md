# Endpoints da API Todo List

Este documento fornece detalhes sobre os endpoints disponíveis na API Todo List.

## POST /tasks

Cria uma nova tarefa.

### Request


Corpo da requisição:

```json
{
    "title": "string",
    "description": "string"
} 
```
Response
Código de status HTTP: 201  Corpo da resposta:

## PUT /tasks/{id}
Atualiza uma tarefa existente.  

### Request 

Parâmetro de caminho:  
id: ID da tarefa a ser atualizada.
Corpo da requisição:

```json
{
    "title": "string",
    "description": "string"
} 
```     
Response
Código de status HTTP: 200  Corpo da resposta:

## DELETE /tasks/{id}
Deleta uma tarefa existente.
### Request
Parâmetro de caminho:  
id: ID da tarefa a ser deletada.
Response
Código de status HTTP: 204

## GET /tasks/{id}
Obtém uma tarefa específica pelo ID.  
Request
Parâmetro de caminho:  
id: ID da tarefa a ser obtida.
Response
Código de status HTTP: 200  Corpo da resposta:

```json
{
"id": "long",
"title": "string",
"description": "string",
"completed": "boolean"
}
```

## PATCH /tasks/{id}/complete
Marca uma tarefa como completa.  
## Request
Parâmetro de caminho:  
id: ID da tarefa a ser marcada como completa.
Response
Código de status HTTP: 200  Corpo da resposta:


```json
{
"id": "long",
"title": "string",
"description": "string",
"completed": "boolean"
}
```