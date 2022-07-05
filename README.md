# Spring Boot - Serviço de Contato

Projeto criado para exemplificar a integração entre uma aplicação construída com o Spring Boot, com o serviço de envio
de email [SendGrid](https://sendgrid.com/).

## Descrição

A aplicação desenvolvida tem como ponto de partida, um serviço muito comum para a maioria das empresas que interagem com
seus clientes e/ou usuários, e que ficou ainda mais em evidência durante a pandemia: o SAC (Serviço de Atendimento ao
Cliente).

Inicialmente, a aplicação fornecerá um serviço para que os clientes/usuários possam entrar em contato com a empresa.
Esse serviço será composto por um único _endpoint_, responsável por tratar requisições/solicitações de contato.

Esse  _endpoint_  deverá responder à requisições [HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP) através do
método POST, e deverá receber os dados para contato no formato [JSON](https://www.json.org/json-en.html) (_JavaScript
Object Notation_).

Os dados para contato são:

| Propriedade | Descrição                       | Restrições  |
|-------------|---------------------------------|-------------|
| nome        | nome do contatante              | obrigatório, mínimo 3 e máximo 100 caracteres |
| email       | email do contatante             | obrigatório, formato válido de email e máximo 100 caracteres |
| assunto     | assunto do contato              | obrigatório, mínimo 3 e máximo 100 caracteres |
| mensagem    | conteúdo da mensagem de contato | obrigatório, mínimo 10 e máximo 1000 caracteres |

Uma vez recebida uma solicitação de contato, a aplicação enviará um email para a suposta empresa. A aplicação não
precisa implementar a lógica relacionada com o envio de email, ao invés disso, deverá delegar essa responsabilidade para
um serviço especializado (externo à aplicação).

## O que está sendo utilizado

* [Spring Boot (2.7.1)](https://docs.spring.io/spring-boot/docs/2.6.1/reference/html/getting-started.html)
  * starter-web
  * starter-validation
* [Lombok (1.18.22)](https://projectlombok.org/setup/maven)
* [Mustache Java (0.9.10)](https://github.com/spullara/mustache.java)

## Documentação da API

Foi utilizado o [Swagger](https://swagger.io/) para documentar a API. Após colocar o projeto em execução, será 
possível acessar a interface gráfica do Swagger no seguinte endereço: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Pré-requisitos

Primeiramente, é necessário ter o Java Development Kit instalado, no mínimo na versão 11. 
* [Veja aqui como instalar o Java 11 no MacOS](https://lucasmgaldino.medium.com/instalando-o-java-jdk-e-jre-no-mac-os-high-sierra-dff85d8d61d5)
* [Veja aqui como instalar o Java 11 no Windows 10.](https://lucasmgaldino.medium.com/instalando-o-java-jre-e-jdk-no-windows-10-4a657d1222d)

Antes de executar a aplicação, é necessário criar uma variável de ambiente em seu sistema operacional, chamada __SEND_GRID_API_KEY__ cujo o valor deve ser a __API KEY__ criada em sua conta do [SendGrid](https://sendgrid.com/).

Também devem ser criadas as variáveis __MAIL_SERVICE_FROM__ e __MAIL_SERVICE_TO__ para definir, respectivamente, o email por onde a mensagem será enviada e o email que deverá receber a mensagem. Possívelmente esses dois emails serão da empresa ou órgão que esteja fornecendo o serviço de contato.


## Como Executar a Aplicação

Após obter o código do projeto, com o auxílio de um prompt ou terminal de comandos, acesse o diretório onde encontra-se o código fonte e execute o comando abaixo:

```
mvn spring-boot:run
```

__Exemplo:__

Supondo que o código fonte está localizado no diretório __/Users/lucas/Desenvolvimento/GitHub/contact-service/__ , utilizando um terminal de comandos, acesse esse diretório, como mostra a imagem abaixo:

![](https://user-images.githubusercontent.com/33494156/177231120-d51d3e42-fe81-4b44-a411-d5d702a8c484.jpeg)

Uma vez no diretório, basta executar o comando __mvn spring-boot:run__ , como mostra a imagem abaixo:

![](https://user-images.githubusercontent.com/33494156/177231118-b94b3d71-0ae8-4971-afbb-63963e7aefc4.jpeg)

Ao final, se tudo correu bem, você verá um resultado similar ao exibido na tela abaixo. Observe que a última linha exibe uma mensagem informando que a aplicação foi inicializada com sucesso: "*Started ContactServiceApplication in 1.207 seconds (JVM running for 1.364)*":

![](https://user-images.githubusercontent.com/33494156/177231117-3f386f97-a096-4ed3-880d-7ceab847231e.png)
