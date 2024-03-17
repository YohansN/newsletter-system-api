# Newsletter System API

Newsletter System API é uma API REST feita com Spring Boot que tem o principal intuido de enviar postagens para assinantes. As publicações são enviadas através do E-mail.

O usuário pode (acesso irrestrito)

* se cadastrar como um assinante para receber as próximas publicações.

* Desativar a assinatura para parar de receber as publicaçõe.

* Reativar a assinatura.

* Acessar uma publicação específica.


O administador pode (acesso exclusivo)

* Criar uma nova publicação.

* Apagar uma publicação existente. (TO DO)

* Ter acesso a lista de usuários cadastrados.

---

## End Points

#### Assinantes:

POST /subscriber - Cadastra um novo assinante.

POST /subscriber/cancel - Desativa o cadastro do assinante.

GET /subscriber (pagination) - Retorna uma lista com todos os cadastrados.

#### Publicações:

POST /post - Cria uma nova publicação.

GET /post (pagination) - Retorna uma lista com o preview de todas as publicações.

GET /post/{id} - Retorna uma publicação por completo.

GET /post/count - Retorna o número total de publicações.