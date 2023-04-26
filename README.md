# Selenium / Webdriver - Teste com Selenium

### Programa Santander Coders 2022
___
Trilha - Automação de Testes

Módulo VII: Selenium / Webdriver
___
### Atividade avaliativa utilizando o Selenium

**Aplicação testada:** Schedule (agenda)

**Definição:**

A empresa "Agenda ada" esta lançando um novo aplicativo para o mercado. Esse aplicativo é uma agenda para o controle de atividade de seus usuários. Nessa agenda teremos a possibilidade de cadastrar atividades a serem executadas e destinar essas atividades à um usuário em especifico. E, essa atividade cadastrada, deve ser concluída e manter a data de encerramento.
Para termos um lançamos tranquilo no mercado vamos automatizar os testes, visando um aumento de qualidade. Para essa automação devemos utilizar Selenium na parte de páginas da web e Cucumber + RestAssured no parte da API.

**Regras**

Usuário

Nas páginas Web teremos o cadastro/lista de usuários na url http://localhost:8080/app/users onde é possível criar, listar e atualizar os usuário.

- cadastrar usuário (nome, username, senha)
- só nome e username na lista, botão de editar
- ñ exibir senha antiga
- permitir atualizar nome e senha
- ñ permitir atualizar username
- ñ permitir cadastrar dois usuários com mesmo username
- ̶ñ̶ ̶p̶e̶r̶m̶i̶t̶i̶r̶ ̶a̶p̶a̶g̶a̶r̶ ̶u̶s̶u̶á̶r̶i̶o̶ ̶c̶a̶d̶a̶s̶t̶r̶a̶d̶o̶
- listar usuários cadastrados

**Repositório de referência da aplicação testada:** https://github.com/WilliamCesarSantos/schedule