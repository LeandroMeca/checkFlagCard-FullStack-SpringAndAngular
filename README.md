<h1>checkFlagCard — Fullstack (Spring Boot + Angular)</h1>

<p>Este repositório contém uma aplicação fullstack para validação de bandeiras de cartões. O projeto está dividido em dois módulos principais:</p>

<ul>
        <li><code>validarbandeira/</code> — Backend em Spring Boot (Java/Maven).</li>
        <li><code>validaCartaoFrontEnd/</code> — Frontend em Angular.</li>
</ul>

<h2>Atualizações recentes (17/12/2025)</h2>

<ul>
    <li>Corrigido o nome do diretório do backend para <code>validarbandeira/</code> (documentação anterior apontava para outro nome).</li>
    <li>Melhorias nas instruções para execução em Windows PowerShell.</li>
    <li>Observação sobre CORS: a aplicação backend inclui uma classe <code>CorsRules.java</code> para configuração; ajuste conforme necessário.</li>
    <li>Instruções para empacotar frontend e servir os arquivos gerados pelo Spring Boot.</li>
</ul>

<h2>Visão geral</h2>

<p>Aplicação que expõe uma API REST (Spring Boot) para validar informações de cartões e uma interface web (Angular) que consome essa API.</p>

<p>Principais objetivos do README:</p>
<ul>
    <li>Descrever a estrutura do projeto.</li>
    <li>Mostrar como rodar o backend e o frontend em desenvolvimento (Windows / PowerShell).</li>
    <li>Explicar como empacotar para produção.</li>
</ul>

<h2>Tecnologias</h2>

<ul>
    <li>Backend: Java, Spring Boot, Maven</li>
    <li>Frontend: Angular, TypeScript, Node.js, npm</li>
    <li>Build/Run: Maven, Angular CLI</li>
</ul>

<h2>Estrutura do repositório</h2>

<ul>
    <li><code>validarbandeira/</code> — projeto Spring Boot
        <ul>
            <li><code>pom.xml</code> — configurações do Maven</li>
            <li><code>src/main/java/...</code> — código-fonte</li>
            <li><code>src/main/resources/application.properties</code> — propriedades</li>
        </ul>
    </li>
    <li><code>validaCartaoFrontEnd/</code> — aplicativo Angular
        <ul>
            <li><code>package.json</code> — dependências e scripts</li>
            <li><code>src/</code> — código-fonte Angular</li>
        </ul>
    </li>
</ul>

<h2>Pré-requisitos</h2>

<ul>
        <li>Java 11+ (ou versão compatível com o projeto)</li>
        <li>Maven 3.6+ (ou use o wrapper incluído)</li>
        <li>Node.js 14+ e npm</li>
        <li>Angular CLI (opcional para desenvolvimento local):</li>
</ul>

<pre><code>npm install -g @angular/cli</code></pre>

<p>Observação: no Windows, abra o PowerShell (<code>pwsh.exe</code>) como terminal.</p>

<h2>Detalhes do frontend (validaCartaoFrontEnd)</h2>

<p>A pasta <code>validaCartaoFrontEnd</code> contém o aplicativo Angular. Abaixo um resumo das informações úteis encontradas no projeto:</p>

<ul>
    <li>Nome do pacote: <code>valida-cartao-front-end</code> (veja <code>package.json</code>).</li>
    <li>Versão do Angular: ~20.x (dependências em <code>package.json</code>).</li>
    <li>Scripts úteis (em PowerShell):
        <ul>
            <li><code>npm start</code> — inicia o servidor de desenvolvimento (equivalente a <code>ng serve</code>).</li>
            <li><code>npm run build -- --prod</code> — gera a build de produção (output em <code>dist/</code>).</li>
            <li><code>npm test</code> — executa os testes (Karma).</li>
        </ul>
    </li>
    <li>Porta de desenvolvimento: <code>http://localhost:4200</code> (padrão do Angular CLI).</li>
    <li>Build artifacts: gerados em <code>dist/</code> (padrão). No arquivo <code>angular.json</code> o projeto usa a configuração de build com output hashing em produção.</li>
    <li>Endpoint do backend usado pelo frontend: no arquivo <code>validaCartaoFrontEnd/src/app/app.ts</code> há a constante
        <pre><code>private readonly BACKEND_URL = 'http://localhost:8080/cartao';</code></pre>
        Atualize esse valor se seu backend rodar em outra porta ou caminho.</li>
    <li>Principais dependências: Angular 20, Bootstrap 5 e RxJS (veja <code>package.json</code>).</li>
    <li>Para servir o frontend a partir do backend Spring Boot, copie o conteúdo de <code>validaCartaoFrontEnd/dist/</code> para <code>validarbandeira/src/main/resources/static</code> antes de empacotar o JAR, ou automatize no CI/CD.</li>
</ul>

<h2>Rodando em desenvolvimento</h2>

<p>Será necessário iniciar o backend e o frontend separadamente.</p>

<h3>1) Backend (Spring Boot)</h3>

<p>Abra um terminal no diretório <code>validarbandeira</code> e execute (PowerShell):</p>

<pre><code>cd validarbandeira
>.\mvnw.cmd spring-boot:run
# ou, se preferir usar maven instalado globalmente:
mvn spring-boot:run</code></pre>

<p>Isso roda a API no endereço padrão (geralmente http://localhost:8080). Verifique as propriedades em <code>validarbandeira/src/main/resources/application.properties</code> caso seja necessário mudar a porta.</p>

<h3>2) Frontend (Angular)</h3>

<p>Abra outro terminal no diretório <code>validaCartaoFrontEnd</code> e instale dependências, depois rode a aplicação:</p>

<pre><code>cd validaCartaoFrontEnd
npm install
npm start
# ou, caso prefira usar o Angular CLI diretamente:
ng serve --open</code></pre>

<p>Por padrão, o Angular serve em <code>http://localhost:4200</code>.</p>

<p>Se houver problemas de CORS, o backend já contém uma configuração (<code>CorsRules.java</code>) — verifique as regras ou ajuste <code>application.properties</code> conforme necessário.</p>

<h2>Build para produção</h2>

<h3>Backend</h3>

<p>No diretório <code>validarbandeira</code> (PowerShell):</p>

<pre><code>cd validarbandeira
>.\mvnw.cmd clean package
# o JAR empacotado ficará em target/ (ex.: target/validarbandeira-0.0.1-SNAPSHOT.jar)</code></pre>

<h3>Frontend</h3>

<p>No diretório <code>validaCartaoFrontEnd</code>:</p>

<pre><code>cd validaCartaoFrontEnd
>.\mvnw.cmd test</code></pre>

# os artefatos serão gerados em dist/ ; copie-os para o servidor web ou integrelos no backend conforme necessário</code></pre>

<p>Opcionalmente, você pode servir os arquivos estáticos gerados pelo Angular a partir do Spring Boot (copiando <code>dist/</code> para <code>src/main/resources/static</code> ou configurando um pipeline de CI/CD que faça isso).</p>

<h2>Testes</h2>

<h3>Backend</h3>

<pre><code>cd validabandeiraBackend
./mvnw.cmd test</code></pre>

<h3>Frontend</h3>

<pre><code>cd validaCartaoFrontEnd
npm test</code></pre>

<h2>Endpoints principais (exemplos)</h2>

<p>Consulte o código em <code>validabandeiraBackend/src/main/java/com/leandro/validarbandeira/controller/CartaoController.java</code> para detalhes reais. Exemplo hipotético:</p>

<ul>
    <li>GET /api/cartao/validate?number=4111111111111111 — valida o número do cartão</li>
    <li>POST /api/cartao — cria/valida um cartão via payload JSON</li>
</ul>

<p>Use ferramentas como Postman ou curl para testar a API diretamente.</p>

<h2>Variáveis de ambiente / configuração</h2>

<p>As configurações estão em <code>validabandeiraBackend/src/main/resources/application.properties</code>. Para produção, prefira usar variáveis de ambiente ou profiles Spring (<code>application-prod.properties</code>).</p>

<h2>Contribuindo</h2>

<ol>
    <li>Abra uma issue descrevendo a mudança.</li>
    <li>Crie um branch para sua feature: <code>git checkout -b feat/minha-feature</code>.</li>
    <li>Envie um pull request com descrição clara.</li>
</ol>

<h2>Licença</h2>

<p>Coloque aqui a licença do projeto (ex.: MIT). Se ainda não decidir, adicione um arquivo <code>LICENSE</code> com a licença desejada.</p>

<h2>Contato</h2>

<p>Se tiver dúvidas, abra uma issue ou contate o mantenedor do repositório.</p>

<hr>

<p>README gerado automaticamente — ajuste conforme necessidades do seu time.</p>
