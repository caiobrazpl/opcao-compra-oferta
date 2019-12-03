# Opcao-compra-oferta

* Para executar a api java utilize os comandos a partir da raiz do projeto;


```
mvn package

java -jar tagert/opcao-compra-oferta-0.0.1-SNAPSHOT.jar
```

* Para levantar o app do front em Angular, a partir da pasta app/;

```
npm install

npm start
```


## Banco de dados
Por padrão está sendo utilizado banco em memória(h2) para ambiente de desenv
 
 Uma outra opção é usar uma base postgres, alterando  arquivo 
 src/main/resources/application.properties para:
 ```
spring.profiles.active=test
 ```
Alterando as configuralções de conexão no arquivo src/main/resources/application.properties,
as seguintes propriedades:

 ```
spring.datasource.url=jdbc:postgresql://localhost:5432/databasename
spring.datasource.username=user
spring.datasource.password=password
 ```
