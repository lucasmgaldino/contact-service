# Spring Boot - Serviço de Contato

Projeto criado para exemplificar a integração de uma aplicação construída com o Spring Boot com alguns serviços de envio de email, como por exemplo:

* [Sendinblue](https://www.sendinblue.com/)
* [SendGrid](https://sendgrid.com/)

## Descrição

A aplicação desenvolvida tem como ponto de partida, um serviço muito comum para a maioria das empresas que interagem com seus clientes e/ou usuários, e que ficou ainda mais em evidência durante a pandemia: o SAC (Serviço de Atendimento ao Cliente).

Inicialmente, a aplicação fornecerá um serviço para que os clientes/usuários possam entrar em contato com a empresa. Esse serviço será compost por um único  _endpoint_, responsável por tratar requisições/solicitações de contato.

Esse  _endpoint_  deverá responder à requisições [HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP) através do método POST, e deverá receber os dados para contato no formato [JSON](https://www.json.org/json-en.html) (_JavaScript Object Notation_).

Os dados para contato são:

| Propriedade | Descrição                       | Restrições  |
|-------------|---------------------------------|-------------|
| nome        | nome do contatante              | obrigatório, mínimo 3 e máximo 100 caracteres |
| email       | email do contatante             | obrigatório, formato válido de email e máximo 100 caracteres |
| assunto     | assunto do contato              | obrigatório, mínimo 3 e máximo 100 caracteres |
| mensagem    | conteúdo da mensagem de contato | obrigatório, mínimo 10 e máximo 1000 caracteres |

Uma vez recebida uma solicitação de contato, a aplicação enviará um email para a suposta empresa. A aplicação não precisa implementar a lógica relacionada com o envio de email, ao invés disso, deverá delegar essa responsabilidade para um serviço especializado (externo à aplicação).



