![Design do Aplicativo](WePlantCapa.png)

O WePlant é um aplicativo voltado para auxiliar moradores de comunidades na prática da agricultura vertical. A agricultura vertical é uma técnica que permite o cultivo de plantas em espaços reduzidos, aproveitando a verticalidade dos ambientes urbanos. O objetivo do WePlant é fornecer suporte e orientação aos agricultores urbanos, ajudando-os a otimizar o uso de recursos, gerenciar suas plantações e maximizar a produção de alimentos.

<br/>

# <span style="color: #63C71F;">Tecnologias Utilizadas</span>

          
<div align="center">
    <img align="center" alt="weplant-html5" height="40" width="12%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" />
    <img align="center" alt="weplant-java" height="40" width="12%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" />
    <img align="center" alt="weplant-react" height="40" width="12%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original-wordmark.svg" />
    <img align="center" alt="weplant-illustrator" height="40" width="12%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" />
    <img align="center" alt="weplant-nodejs" height="40" width="12%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original-wordmark.svg" />
    <img align="center" alt="weplant-nodejs" height="40" width="12%" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original-wordmark.svg" />


</div>

<br/>

<br/>

# <span style="color: #63C71F;">Configuração e Execução </span>

Para executar o aplicativo em um ambiente de desenvolvimento, siga as instruções abaixo conforme cada tecnologia:

- Instalar o Doker desktop
- Clonar o Projeto
- Rodar o docker-compose que esta na raiz do projeto    
   * docker-compose up -d
- Rodar react-native expo
          * npm install
          * npm start


<br/>

# <span style="color: #63C71F;">Endpoints </span>

Para um detalhamento completo dos endpoints feito pelo swagger, após a execução da aplicação, acesse a interface do swagger da aplicação pelo link : http://localhost:8080/swagger-ui/index.html#/.

## UserController

### Método findById:
Rota: "localhost:8080/users/{**user_id**}" <br>
Descrição: Retorna um usuário com o ID especificado.

### Método insert:
Rota: "localhost:8080/users/add" <br>
Descrição: Insere um novo usuário com base nos dados fornecidos.

```js
Request Body:

{
    "name" : "Tozé",
    "birthday" : "02/02/2002",
    "username" : "tozégamepray",
    "email" : "tozégamepray@gmail",
    "password" : "123456",
    "address" : {
        "CEP" : "55555-555",
        "number": "123456",
        "street": "Example Street",
        "neighborhood": "Example Neighborhood",
        "city": "Example City",
        "state": "Example State",
        "country": "Example Country"
        },
    "phone" : {
        "ddd" : "123",
        "ddi" : "123",
        "number" : "123456789101"
    }
}


```

### Método update:
Rota: "localhost:8080/users/updt/{**user_id**}" <br>
Descrição: Atualiza um usuário existente com base nos dados fornecidos.

```js
Request body
 Irá variar de acordo com as informações a se atualizar, ex. de endereço : 
 
 "address" : {
        "CEP" : "55555-555",
        "number": "123456",
        "street": "Example Street",
        "neighborhood": "Example Neighborhood",
        "city": "Example City",
        "state": "Example State",
        "country": "Example Country"
        }


```

### Método updateUserStatus:
Rota: "localhost:8080/users/{**user_id**}" <br>
Descrição: Atualiza o status de um usuário para "deletado".

### Método addGarden:
Rota: "localhost:8080/users/{**user_id**}/garden/add" <br>
Descrição: Adiciona um novo jardim ao usuário com o ID especificado.

``` js
{
  "name": "Exemplo de nome",
  "status": "PLANTACAO",
  "plant": "Exemplo de planta",
  "type": "V"
}

```

### Método findGardenByUserID:
Rota: "localhost:8080/users/{**user_id**}/gardens" <br>
Descrição: Retorna uma lista de jardins pertencentes ao usuário com o ID especificado.

### Método findAllUserPostsByUserId:
Rota: "localhost:8080/users/{**user_id**}/posts" <br>
Descrição: Retorna uma página paginada de postagens feitas pelo usuário com o ID especificado.

### Método login:
Rota: "localhost:8080/users/login" <br>
Descrição: Realiza o login do usuário com base nas informações fornecidas.

``` js
{
"username" : "name",
"password" : "senha"
}
```

## GardenController

### Método findById:
Rota: "localhost:8080/gardens/{**garden_id**}" <br>
Descrição: Retorna um objeto GardenDTO com as informações do jardim encontrado.

### Método deleteById:
Rota: "localhost:8080/gardens/{**garden_id**}" <br>
Descrição: Remove um jardim com o ID especificado.

### Método update:
Rota: "localhost:8080/gardens/{**garden_id**}" <br>
Descrição: Atualiza um jardim existente com base nos dados fornecidos.

``` js
{
   "status" : "CRESCIMENTO"
}

```

### Método insertNote:
Rota: "localhost:8080/gardens/{**garden_id**}/notes/add" <br>
Descrição: Insere uma nova nota para o jardim com o ID especificado.

``` js
  {
      "body" : "Plantação com resultados. Colher em duas seman.
  }
```

### Método deleteNote:
Rota: "localhost:8080/gardens/notes/{**note_id**}" <br>
Descrição: Remove uma nota com o ID especificado.

### Método updateNote:
Rota: "localhost:8080/gardens/notes/{**note_id**}" <br>
Descrição: Atualiza uma nota existente com base nos dados fornecidos.


``` js
  {
      "body" : "Plantação com resultados. Colher em duas semanas.
  }
```

### Método findAllNotes:
Rota: "localhost:8080/gardens/{**garden_id**}/notes" <br>
Descrição: Retorna uma lista de objetos NoteDTO com as informações das notas encontradas.

### Método findNoteById:
Rota: "localhost:8080/gardens/notes/{**note_id**}" <br>
Descrição: Retorna um objeto NoteDTO com as informações da nota encontrada.


## PostController

#### Método insert:
Rota: "localhost:8080/posts/add/user/{**user_id**}"
Descrição: Insere um novo post com base nos dados fornecidos e no ID do usuário especificado.


``` js
  {
     {
  "description": "Descrição do post",
  "date": "2023-06-07",
  "userName": "Nome do usuário",
  "username": "Nome de usuário"
}
  }
```

### Método findById:
Rota: "localhost:8080/posts/{**post_id**}"
Descrição: Retorna um objeto PostReducedDTO com as informações do post encontrado.

### Método findAllCommentsByPostId:
Rota: "localhost:8080/posts/{**post_id**}/comments/all"
Descrição: Retorna uma página paginada de objetos CommentDTO com os comentários do post com o ID especificado.

### Método addNewComment:
Rota: "localhost:8080/posts/{**post_id**}/comments/add"
Descrição: Adiciona um novo comentário ao post com o ID especificado, com base nos dados fornecidos.


``` js
  {
      "userId" : 1,
      "body" : "Que plantação top!"
  }
```

### Método deleteById:
Rota: "localhost:8080/posts/{**post_id**}/del"
Descrição: Remove um post com o ID especificado.

### Método deleteCommentById:
Rota: "localhost:8080/posts/{**post_id**}/comments/{**comment_Id**}/del"
Descrição: Remove um comentário com o ID especificado, pertencente ao post com o ID especificado.

# <span style="color: #63C71F;">Equipe</span>

     A equipe responsável por esse projeto é composta por:

- <span style="color: #63C71F;">RM: 93915 - Jaelson dos Santos </span> 
- <span style="color: #63C71F;">RM: 94311 - Marcos Bilobram </span> 
- <span style="color: #63C71F;">RM: 96320 - Nathália Maia </span> 
- <span style="color: #63C71F;">RM: 94972 - Rafaela da Silva </span> 

        Cada membro da equipe desempenha um papel fundamental no desenvolvimento e no sucesso do projeto, contribuindo com suas habilidades e conhecimentos na área de tecnologia.

<br/>

# <span style="color: #63C71F;">Pitch</span>

[Assista ao video Pitch](https://youtu.be/0_QOPCaIbMc)

# <span style="color: #63C71F;">Considerações Finais</span>

        O desenvolvimento do WePlant é um projeto realizado como parte da prova semestral da faculdade FIAP, com o tema "Combate à fome através da agricultura sustentável".

        A equipe se empenhou para criar uma solução eficiente e inovadora, que visa promover a segurança alimentar, incentivar a agricultura sustentável e combater a fome nas comunidades.

O projeto WePlant está disponível na íntegra, incluindo todos os códigos-fonte e entregas, na organização do GitHub: 
[https://github.com/WePlant-GS].

<br/>

# <span style="color: #63C71F;">Licença</span>
Este projeto está licenciado sob a MIT License.
