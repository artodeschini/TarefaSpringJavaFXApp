# Sistema de cadastro de tarefas Spring x JavaFX

## requisitos

* maven
* java 17
* lombok
* JavaFX

## spring

Esté sistema foi criaod utilizando [spring](https://start.spring.io/) com Java 17
A versão do spring utilizada foi 3.1.4 com maven
Para maiores detalhes e ajuda com spring com [spring](HELP.md)

## UI com JavaFX

A UI foi desenvolvido utilizando Java FX com integração com Spring que realiza a persistencia dos dados usando
Spring Data e jpa

## Para detalhes da integracao JavaFX com Spring

Está é uma aplicacao que integra JavaFx com Spring
foi adicionado ao pom as libs de org.openjfx que permite injetar outros controladores no caso dessa app Component do spring

Foi alterado a [classe Application](src/main/java/org/todeschini/tarefas/TarefasApplication.java) que implementa o metodo main com spring
Na [class Application do JavaFX](src/main/java/org/todeschini/tarefas/ui/component/SistemaTarefaFX.java) foi carregado o context do Spring para dentro do Loader do JavaFX vide metodos `init` e `start`.
Por fim o [controlador do JavaFX](src/main/java/org/todeschini/tarefas/controller/IndexController.java) foi anotado como um Compponet do spring para permitir que seja carregado toda a factory do spring para dentro do javafx

## Base de dados

Foi utilizada [h2 database](https://www.h2database.com/html/main.html), porém pode ser substituido por qualquer banco de dados relacional que tenha suporte a jdbc4

## boilerplate

A fim de evitar a `boilerplate` codigo repetitivo foi utilizado [lombok](https://projectlombok.org/) afim de remover codigo como get e set construtores e escrita do metodo toString

[implementado](src/main/java/org/todeschini/tarefas/model/Tarefa.java)
