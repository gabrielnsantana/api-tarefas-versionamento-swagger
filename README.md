# Documentação Técnica da API: Gerenciador de Tarefas Pessoais
## Atividade Autoral — Etapa 3: Padrões Arquiteturais, Versionamento, Tratamento de Exceções e Documentação

Este repositório apresenta a evolução técnica da API RESTful desenvolvida para o gerenciamento de tarefas pessoais. O objetivo desta etapa consistiu em refatorar e complementar a aplicação preexistente, alinhando-a às melhores práticas de engenharia de software, segurança, robustez no tratamento de falhas e governança de APIs.

---

## 1. Estruturação do Projeto e Arquitetura em Camadas

A fim de cumprir os requisitos de manutenibilidade, testabilidade e separação clara de responsabilidades (Separation of Concerns), o código-fonte foi reestruturado logicamente em pacotes bem definidos:

* **`com.example.Tarefa`**: Camada de domínio que centraliza a entidade de persistência principal (`Tarefa`).
* **`com.example.Tarefa.controllers`**: Camada de infraestrutura de exposição que centraliza a lógica de controle de requisições HTTP, validação de payloads e barramento de tratamento de exceções.
  * `tarefaController.java`: Controlador REST que gerencia o ciclo de vida dos recursos.
  * `TarefaRequest.java`: Registro (Record) que atua como DTO (Data Transfer Object) para input de dados, encapsulando regras de validação declarativas da especificação Jakarta Validation (`@NotBlank`, `@Size`).

---

## 2. Estratégia de Versionamento da API

Visando assegurar a compatibilidade retroativa (backward compatibility) e permitir a evolução contínua dos recursos do sistema sem causar impactos ou quebras nos clientes integrados, foi adotada a estratégia de **Versionamento por URL (URI Versioning)**.

Todos os endpoints expostos pelo controlador de tarefas foram mapeados sob o seguinte prefixo semântico de versão:
* **URI Base dos Endpoints:** `/api/v1/tarefas`
* **Endereço Local de Acesso:** `http://localhost:8080/api/v1/tarefas`

---

## 3. Tratamento Global e Refinado de Erros e Exceções

A aplicação implementa um mecanismo centralizado de interceptação de exceções por meio da anotação `@RestControllerAdvice` (`RestExceptionHandler`). Esta abordagem blinda a infraestrutura interna da aplicação, impedindo o vazamento de stack traces e padronizando as respostas de erro enviadas ao cliente em um objeto JSON consistente (`ErrorMessage.java`).

As respostas de erro estruturadas incluem obrigatoriamente o código de status HTTP correspondente, a identificação do erro, uma mensagem descritiva orientando o usuário e o carimbo de data/hora exato da ocorrência (`timestamp`).

### Modelo de Resposta de Erro Padronizada (HTTP 404 - Not Found):
```json
{
  "status": 404,
  "error": "Recurso Não Encontrado",
  "message": "Tarefa com o ID solicitado não foi encontrada no sistema.",
  "timestamp": "2026-06-08T23:15:42.104"
}
