# checkFlagCard — Fullstack (Spring Boot + Angular)

Este repositório contém uma aplicação fullstack para validação de bandeiras de cartões.
O projeto é dividido em dois módulos principais:

- `validabandeiraBackend/` — Backend em Spring Boot (Java/Maven).
- `validaCartaoFrontEnd/` — Frontend em Angular.

## Visão geral

Aplicação que expõe uma API REST (Spring Boot) para validar informações de cartões e uma interface web (Angular) que consome essa API.

Principais objetivos do README:

- Descrever a estrutura do projeto.
- Mostrar como rodar o backend e o frontend em desenvolvimento (Windows / PowerShell).
- Explicar como empacotar para produção.

## Tecnologias

- Backend: Java, Spring Boot, Maven
- Frontend: Angular, TypeScript, Node.js, npm
- Build/Run: Maven, Angular CLI

## Estrutura do repositório

- `validabandeiraBackend/` — projeto Spring Boot
  - `pom.xml` — configurações do Maven
  - `src/main/java/...` — código-fonte
- `validaCartaoFrontEnd/` — aplicativo Angular
  - `package.json` — dependências e scripts
  - `src/` — código-fonte Angular

## Pré-requisitos

- Java 11+ (ou versão compatível com o projeto)
- Maven 3.6+
- Node.js 14+ e npm
- Angular CLI (opcional para desenvolvimento local):

  ```powershell
  npm install -g @angular/cli
  ```

Observação: no Windows, abra o PowerShell (`pwsh.exe`) como terminal.

## Rodando em desenvolvimento

Será necessário iniciar o backend e o frontend separadamente.

### 1) Backend (Spring Boot)

Abra um terminal no diretório `validabandeiraBackend` e execute:

```powershell
cd validabandeiraBackend
./mvnw.cmd spring-boot:run
# ou, se preferir usar maven instalado globalmente:
mvn spring-boot:run
```

Isso roda a API no endereço padrão (geralmente http://localhost:8080). Verifique as propriedades em `validabandeiraBackend/src/main/resources/application.properties` caso seja necessário mudar a porta.

### 2) Frontend (Angular)

Abra outro terminal no diretório `validaCartaoFrontEnd` e instale dependências, depois rode a aplicação:

```powershell
cd validaCartaoFrontEnd
npm install
npm start
# ou, caso prefira usar o Angular CLI diretamente:
ng serve --open
```

Por padrão, o Angular serve em `http://localhost:4200`.

Se houver problemas de CORS, o backend já contém uma configuração (`CorsRules.java`) — verifique as regras ou ajuste `application.properties` conforme necessário.

## Build para produção

### Backend

No diretório `validabandeiraBackend`:

```powershell
cd validabandeiraBackend
./mvnw.cmd clean package
# o JAR empacotado ficará em target/ (ex.: target/validabandeira-0.0.1-SNAPSHOT.jar)
```

### Frontend

No diretório `validaCartaoFrontEnd`:

```powershell
cd validaCartaoFrontEnd
npm run build -- --prod
# os artefatos serão gerados em dist/ ; copie-os para o servidor web ou integrelos no backend conforme necessário
```

Opcionalmente, você pode servir os arquivos estáticos gerados pelo Angular a partir do Spring Boot (copiando `dist/` para `src/main/resources/static` ou configurando um pipeline de CI/CD que faça isso).

## Testes

### Backend

```powershell
cd validabandeiraBackend
./mvnw.cmd test
```

### Frontend

```powershell
cd validaCartaoFrontEnd
npm test
```

## Endpoints principais (exemplos)

Consulte o código em `validabandeiraBackend/src/main/java/com/leandro/validarbandeira/controller/CartaoController.java` para detalhes reais. Exemplo hipotético:

- GET /api/cartao/validate?number=4111111111111111 — valida o número do cartão
- POST /api/cartao — cria/valida um cartão via payload JSON

Use ferramentas como Postman ou curl para testar a API diretamente.

## Variáveis de ambiente / configuração

As configurações estão em `validabandeiraBackend/src/main/resources/application.properties`. Para produção, prefira usar variáveis de ambiente ou profiles Spring (`application-prod.properties`).

## Contribuindo

1. Abra uma issue descrevendo a mudança.
2. Crie um branch para sua feature: `git checkout -b feat/minha-feature`.
3. Envie um pull request com descrição clara.

## Licença

Coloque aqui a licença do projeto (ex.: MIT). Se ainda não decidir, adicione um arquivo `LICENSE` com a licença desejada.

## Contato

Se tiver dúvidas, abra uma issue ou contate o mantenedor do repositório.

---

README gerado automaticamente — ajuste conforme necessidades do seu time.
