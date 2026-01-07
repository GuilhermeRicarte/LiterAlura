# Book Catalog - Gutendex API Integration

Um catálogo de livros desenvolvido em **Java com Spring Boot**, que consome dados da **API Gutendex** (mais de 70 mil livros gratuitos) e persiste os dados em um banco de dados **PostgreSQL**.

## 📋 Requisitos

- **Java 17+**
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Spring Boot 3.2.0+**

## 🚀 Configuração e Instalação

### 1. Configurar o Banco de Dados PostgreSQL

```sql
-- Criar o banco de dados
CREATE DATABASE gutendex_db;

-- Conectar ao banco
\c gutendex_db

-- O Hibernate criará as tabelas automaticamente ao executar a aplicação
```

### 2. Atualizar as Credenciais do Banco de Dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gutendex_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Compilar e Executar o Projeto

```bash
# Compilar o projeto
mvn clean compile

# Executar testes (se houver)
mvn test

# Empacotar a aplicação
mvn package

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080/api`

## 📚 Endpoints da API

### Livros

- **GET** `/api/books` - Listar todos os livros
- **GET** `/api/books/{id}` - Obter um livro pelo ID do banco
- **GET** `/api/books/gutendex/{gutendexId}` - Obter um livro pelo ID do Gutendex
- **GET** `/api/books/stats` - Obter estatísticas (total de livros e autores)

### Autores

- **GET** `/api/authors` - Listar todos os autores
- **GET** `/api/authors/{id}` - Obter um autor pelo ID

### Importação (Gutendex)

- **POST** `/api/import/page/{page}` - Importar livros da página especificada da API Gutendex
- **GET** `/api/import/health` - Verificar status da API

## 📖 Exemplo de Uso

### 1. Importar Livros da API Gutendex

```bash
curl -X POST http://localhost:8080/api/import/page/1
```

Resposta:
```json
{
  "status": "success",
  "message": "Books imported from page 1"
}
```

### 2. Listar Todos os Livros

```bash
curl http://localhost:8080/api/books
```

### 3. Verificar Estatísticas

```bash
curl http://localhost:8080/api/books/stats
```

Resposta:
```json
{
  "totalBooks": 250,
  "totalAuthors": 150
}
```

## 🏗️ Estrutura do Projeto

```
src/main/java/com/alura/bookcatalog/
├── BookCatalogApplication.java       # Main da aplicação
├── config/
│   └── RestTemplateConfig.java       # Configuração do RestTemplate
├── controller/
│   ├── BookController.java           # Endpoints de livros
│   ├── AuthorController.java         # Endpoints de autores
│   └── ImportController.java         # Endpoints de importação
├── dto/
│   ├── BookDTO.java                  # DTO para livros da API
│   ├── AuthorDTO.java                # DTO para autores da API
│   └── GutendexResponseDTO.java      # DTO para resposta da API
├── model/
│   ├── Book.java                     # Entidade JPA - Livro
│   └── Author.java                   # Entidade JPA - Autor
├── repository/
│   ├── BookRepository.java           # Repositório de livros
│   └── AuthorRepository.java         # Repositório de autores
└── service/
    ├── BookService.java              # Serviço de livros
    └── GutendexService.java          # Serviço de integração com Gutendex
```

## 🔗 API Gutendex

A **Gutendex API** é uma API RESTful gratuita que fornece dados de livros do **Project Gutenberg**.

- **URL Base**: https://gutendex.com/books
- **Documentação**: https://gutendex.com/
- **Total de Livros**: 70.000+
- **Sem autenticação necessária**

### Exemplo de Resposta da API Gutendex

```json
{
  "count": 70099,
  "next": "https://gutendex.com/books?page=2",
  "previous": null,
  "results": [
    {
      "id": 1,
      "title": "The Scarlet Letter",
      "authors": [
        {
          "name": "Nathaniel Hawthorne",
          "birth_year": 1804,
          "death_year": 1864
        }
      ],
      "languages": ["en"],
      "download_count": 12345,
      "cover_image": "https://..."
    }
  ]
}
```

## 🗄️ Estrutura do Banco de Dados

### Tabela: authors
- `id` (BIGINT) - Chave primária
- `name` (VARCHAR) - Nome do autor
- `birth_year` (INT) - Ano de nascimento
- `death_year` (INT) - Ano de morte

### Tabela: books
- `id` (BIGINT) - Chave primária
- `gutendex_id` (BIGINT UNIQUE) - ID do livro no Gutendex
- `title` (VARCHAR) - Título do livro
- `download_count` (INT) - Número de downloads
- `cover_image_url` (TEXT) - URL da capa
- `author_id` (BIGINT) - FK para authors

### Tabela: book_languages
- `book_id` (BIGINT) - FK para books
- `language` (VARCHAR) - Código do idioma

## 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.2.0** - Framework web
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL 42.7.1** - Banco de dados
- **Jackson** - Serialização JSON
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

## 📝 Logs

Os logs são configurados em `src/main/resources/application.properties`:

```properties
logging.level.root=INFO
logging.level.com.alura=DEBUG
```

Isso permite visualizar detalhes de execução da aplicação e do consumo da API.

## 🐛 Troubleshooting

### Erro de Conexão com PostgreSQL
- Verifique se o PostgreSQL está rodando
- Confirme as credenciais em `application.properties`
- Verifique se o banco `gutendex_db` foi criado

### Erro ao Consumir a API Gutendex
- Verifique sua conexão de internet
- A API Gutendex pode estar temporariamente indisponível
- Verifique os logs para mais detalhes

### Erro de Compilação
- Execute `mvn clean` antes de recompilar
- Certifique-se que Java 17+ está instalado: `java -version`

## 📄 Licença

Este projeto é fornecido como exemplo educacional para praticar integração com APIs e persistência de dados com Spring Boot e PostgreSQL.


---

**Desenvolvido como parte do aprendizado na Alura** 📚
