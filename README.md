Projeto CRUD Loja de Games - Turma 79 Java do Bootcamp Generation

Backend para uma Loja de Games com a capacidade de manipular os dados dos Produtos da loja

Descrição Geral:
O projeto foi criado para cuidar da organização do estoque de produtos da loja

Descrição da Entidade/Model foi criada e seus atributos:
Criamos as Model’s Categorias e Produtos.
(tb_categorias) com os atributos id, categorias, descricao_categorias
(tb_produtos) com os atributos id, nome, descricao_produto, preco, quantidade e FK id categorias

Funcionalidades Principais (CRUD) implementadas:
findAll()
findById()
findAllByNome()
post()
put()
delete()

Tecnologias Utilizadas (banco de dados e backend):
Java 17+
MySql Driver
Spring Boot Dev Tools
SpringWeb
Validation
Spring Data JPA
