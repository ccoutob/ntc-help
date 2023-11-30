<h1 align="center">
<br>NTC Health 💊
</h1>

<div align="center">

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/ccoutob/GlobalSolutionFiap/blob/main/LICENSE)
 
</div>

> Repositório dedicado ao projeto NTC Health (Java) - desenvolvido para atender as demandas da Global Solution com a empresa parceira Hapvida NotreDame Intermédica pelo primeiro ano em ADS na FIAP!

<h2>📝Desafio</h2>
<li>Inovação e Tecnologia Moldando o Futuro da Saúde: Prevenção, Automação e Precisão”</li>

<h2 name="objetivo">🎯 Objetivos do Projeto</h2>
<li>Facilidade ao acesso de dados do paciente</li>
<li>Melhorar eficiência do acompanhamento médico</li>
<li>Evitar progressões maléficas dos tratamentos</li>
<li>Auxiliar pacientes a tomarem suas mediações no momento definido pelo profissional da saúde</li>

<h2>🤖 Funcionalidades do Projeto</h2>
 <li>Criação de cadastro de paciente e armazenamento de dados</li> 
 <li>Criação de agenda para acompanhamento do paciente e armazenamento de dados</li> 
 <li>Integração com o banco de dados, seguindo o padrão factory</li> 
 <li>Dados armazenados enviados ao POSTMAN para realizar requisição na Web e salvar no banco de dados</li> 
   
<h2>📖 Sobre a Global Solution</h2>

| _Challenge 3_ |                                       |
| ------------- | ------------------------------------- |
| _Curso_       | Análise e Desenvolvimento de Sistemas |
| _Disciplina_  | Domain Driven Design                  |
| _Professor_   | Thiago Toshiyuki Izumi Yamamoto       |
| _Turma_       | 1TDSS                                 |

<h2 name="endpoints">🌐 Endpoints</h2>

### ❗ PS: Lembre sempre do /NTC-Health antes de cada endpoint

### 😷 Paciente

| Método | Endpoint                      | Descrição                 |
| ------ | ----------------------------- | --------------------------|
| GET    | /api/paciente                 | Listar todos os pacientes |
| GET    | /api/paciente/&lt;id&gt;      | Buscar paciente pelo id   |
| GET    | /api/paciente/pesquisaPorNome | Buscar paciente pelo nome |
| POST   | /api/paciente                 | Cadastrar um paciente     |
| PUT    | /api/paciente/&lt;id&gt;      | Atualizar um paciente     |
| DELETE | /api/paciente/&lt;id&gt;      | Deletar um paciente       |

### 📃 Agenda de Acompanhamento
| Método | Endpoint                                        | Descrição               |
| ------ | ------------------------------------------------| ------------------------|
| GET    | /api/agenda                                     | Listar todas as agendas |
| GET    | /api/agenda/pesquisaPorMedico/{nomeMedico}      | Buscar agenda pelo id   |
| GET    | /api/agenda                                     | Listar todas as agendas |
| POST   | /api/agenda                                     | Cadastrar uma agenda    |
| PUT    | /api/agenda/&lt;id&gt;                          | Atualizar uma agenda    |
| DELETE | /api/agenda/&lt;id&gt;                          | Deletar uma agenda      |
   
### ❗️Dados adicionais para a funcionalidade do projeto
<li>O NTC Health foi desenvolvido com o servidor tomcat na versão 10.1</li> 
<li>Os dados devem ser passados ao POSTMAN para realizar a requisição na Web e Banco de dados   </li>
 
### 📋 Documentação do projeto
Acesse a documentação do NTC Health <a href="https://github.com/ccoutob/GlobalSolutionFiap/blob/main/NTC%20HEALTH%20-%20Documenta%C3%A7%C3%A3o.pdf">AQUI</a> 

### 🧑🏻‍💻 Autor 
> Cauã Couto Basques - Turma 1TDSS
