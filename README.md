<h1 align="center">
<br>NTC Health 💊
</h1>

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

### ❗ PS: Lembre sempre do /api antes de cada endpoint

### 😷 Paciente

| Método | Endpoint                      | Descrição                 |
| ------ | ----------------------------- | --------------------------|
| GET    | /api/paciente                 | Listar todos os pacientes |
| GET    | /api/paciente/&lt;id&gt;      | Buscar paciente pelo id   |
| GET    | /api/paciente/pesquisaPorNome | Buscar paciente pelo nome |
| POST   | /api/paciente                 | Cadastrar um paciente     |
| PUT    | /api/paciente/&lt;id&gt;      | Atualizar um paciente     |
| DELETE | /api/paciente/&lt;id&gt;      | Deletar um paciente       |

### 📃 Apólice
| Método | Endpoint                     | Descrição                |
| ------ | ---------------------------- | ------------------------ |
| GET    | /api/apolice                 | Listar todas as apolices |
| GET    | /api/apolice/&lt;id&gt;      | Buscar apolice pelo id   |
| POST   | /api/apolice                 | Cadastrar uma apolice    |
| PUT    | /api/apolice/&lt;id&gt;      | Atualizar uma apolice    |
| DELETE | /api/apolice/&lt;id&gt;      | Deletar uma apolice      |
   
   

