# 📝 Task Manager API - Etapa 2: Modelos Arquiteturais e Tratamento de Erros

Esta é a evolução da API RESTful para gerenciamento de tarefas pessoais, aprimorada para seguir padrões arquiteturais rígidos, com validação de dados persistidos e tratamento avançado de requisições e respostas.

---

## 🛠️ Evolução Arquitetural e Estrutura de Pastas

O projeto foi refatorado e dividido em pacotes baseados em responsabilidades (Modelos Arquiteturais):

* `controllers`: Camada de exposição dos endpoints RESTful (`TarefaController`).
* `domain`: Entidades de mapeamento objeto-relacional com validações a nível de banco de dados (`Tarefa`).
* `dto`: Objetos de Transferência de Dados (`TarefaRequest`) blindando a entrada de dados da API.
* `infra`: Camada de infraestrutura contendo o Handler Global de Exceções.

---

## 🛡️ Tratamento Avançado de Requisições e Respostas (Novidade)

A API agora conta com um mecanismo global (`@RestControllerAdvice`) que intercepta falhas e retorna mensagens amigáveis e estruturadas em formato JSON, evitando a exposição de erros internos do servidor.

### Exemplos de Respostas de Erro Customizadas:

1. **400 Bad Request (Validação de Dados)**
   Caso tente enviar uma tarefa sem título ou menor que 3 caracteres:
   ```json
   {
     "titulo": "O título deve ter entre 3 e 100 caracteres."
   }