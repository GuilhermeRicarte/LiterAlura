# 📝 Exemplos de Uso da API

Este arquivo contém exemplos práticos de como usar a API do Book Catalog.

## 🚀 Iniciar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080/api**

---

## 📚 Exemplos de Endpoints

### 1️⃣ Verificar Saúde da API

```bash
curl -X GET http://localhost:8080/api/import/health
```

**Resposta**:
```json
{
  "status": "healthy",
  "api": "Gutendex API is ready to use"
}
```

---

### 2️⃣ Importar Livros da API Gutendex

#### Importar primeira página
```bash
curl -X POST http://localhost:8080/api/import/page/1
```

**Resposta**:
```json
{
  "status": "success",
  "message": "Books imported from page 1"
}
```

#### Importar várias páginas (exemplo em shell script)
```bash
#!/bin/bash
for page in {1..5}; do
    echo "Importando página $page..."
    curl -X POST http://localhost:8080/api/import/page/$page
    echo ""
    sleep 2  # Aguardar 2 segundos entre requisições
done
```

#### Importar várias páginas (PowerShell - Windows)
```powershell
for ($page = 1; $page -le 5; $page++) {
    Write-Host "Importando página $page..."
    Invoke-WebRequest -Uri "http://localhost:8080/api/import/page/$page" -Method POST
    Start-Sleep -Seconds 2
}
```

---

### 3️⃣ Listar Todos os Livros

```bash
curl -X GET http://localhost:8080/api/books
```

**Resposta** (abreviada):
```json
[
  {
    "id": 1,
    "gutendexId": 1,
    "title": "The Scarlet Letter",
    "languages": ["en"],
    "downloadCount": 12345,
    "coverImageUrl": "https://www.gutenberg.org/cache/epub/1/pg1.jpg",
    "author": {
      "id": 1,
      "name": "Nathaniel Hawthorne",
      "birthYear": 1804,
      "deathYear": 1864,
      "books": []
    }
  },
  // ... mais livros
]
```

---

### 4️⃣ Buscar Livro por ID

#### Buscar pela chave primária do banco
```bash
curl -X GET http://localhost:8080/api/books/1
```

#### Buscar pelo ID do Gutendex
```bash
curl -X GET http://localhost:8080/api/books/gutendex/1
```

**Resposta**:
```json
{
  "id": 1,
  "gutendexId": 1,
  "title": "The Scarlet Letter",
  "languages": ["en"],
  "downloadCount": 12345,
  "coverImageUrl": "https://www.gutenberg.org/cache/epub/1/pg1.jpg",
  "author": {
    "id": 1,
    "name": "Nathaniel Hawthorne",
    "birthYear": 1804,
    "deathYear": 1864
  }
}
```

---

### 5️⃣ Obter Estatísticas

```bash
curl -X GET http://localhost:8080/api/books/stats
```

**Resposta**:
```json
{
  "totalBooks": 250,
  "totalAuthors": 150
}
```

---

### 6️⃣ Listar Todos os Autores

```bash
curl -X GET http://localhost:8080/api/authors
```

**Resposta** (abreviada):
```json
[
  {
    "id": 1,
    "name": "Nathaniel Hawthorne",
    "birthYear": 1804,
    "deathYear": 1864,
    "books": [
      {
        "id": 1,
        "gutendexId": 1,
        "title": "The Scarlet Letter",
        "languages": ["en"],
        "downloadCount": 12345
      }
    ]
  },
  // ... mais autores
]
```

---

### 7️⃣ Buscar Autor por ID

```bash
curl -X GET http://localhost:8080/api/authors/1
```

**Resposta**:
```json
{
  "id": 1,
  "name": "Nathaniel Hawthorne",
  "birthYear": 1804,
  "deathYear": 1864,
  "books": [
    {
      "id": 1,
      "gutendexId": 1,
      "title": "The Scarlet Letter",
      "languages": ["en"],
      "downloadCount": 12345
    }
  ]
}
```

---

## 🔧 Exemplos com JSON (POST/PUT)

### Importar com Página Específica

```bash
# Windows CMD
curl -X POST "http://localhost:8080/api/import/page/2" -H "Content-Type: application/json"
```

```bash
# Linux/macOS
curl -X POST "http://localhost:8080/api/import/page/2" \
  -H "Content-Type: application/json"
```

---

## 🧪 Testar com Postman

### Configuração no Postman

1. **Importar Livros**
   - Method: `POST`
   - URL: `http://localhost:8080/api/import/page/1`
   - Headers: `Content-Type: application/json`
   - Clique **Send**

2. **Listar Livros**
   - Method: `GET`
   - URL: `http://localhost:8080/api/books`
   - Clique **Send**

3. **Buscar Estatísticas**
   - Method: `GET`
   - URL: `http://localhost:8080/api/books/stats`
   - Clique **Send**

---

## 📊 Fluxo Completo de Uso

```
1. Verificar saúde da API
   GET /api/import/health
   
2. Importar livros da Gutendex (página 1)
   POST /api/import/page/1
   
3. Aguardar alguns segundos
   
4. Verificar estatísticas
   GET /api/books/stats
   
5. Listar os livros importados
   GET /api/books
   
6. Buscar um livro específico
   GET /api/books/1
   
7. Listar autores
   GET /api/authors
   
8. Buscar um autor específico
   GET /api/authors/1
```

---

## 🐍 Exemplo com Python

```python
import requests
import json

BASE_URL = "http://localhost:8080/api"

# 1. Verificar saúde
response = requests.get(f"{BASE_URL}/import/health")
print("Status:", response.json())

# 2. Importar livros
response = requests.post(f"{BASE_URL}/import/page/1")
print("Import:", response.json())

# 3. Listar livros
response = requests.get(f"{BASE_URL}/books")
books = response.json()
print(f"Total de livros: {len(books)}")

# 4. Listar autores
response = requests.get(f"{BASE_URL}/authors")
authors = response.json()
print(f"Total de autores: {len(authors)}")

# 5. Obter estatísticas
response = requests.get(f"{BASE_URL}/books/stats")
stats = response.json()
print(f"Estatísticas: {stats}")
```

---

## 🎯 JavaScript/Node.js

```javascript
const BASE_URL = "http://localhost:8080/api";

async function testAPI() {
  try {
    // 1. Verificar saúde
    let response = await fetch(`${BASE_URL}/import/health`);
    console.log("Status:", await response.json());
    
    // 2. Importar livros
    response = await fetch(`${BASE_URL}/import/page/1`, { method: "POST" });
    console.log("Import:", await response.json());
    
    // 3. Listar livros
    response = await fetch(`${BASE_URL}/books`);
    const books = await response.json();
    console.log(`Total de livros: ${books.length}`);
    
    // 4. Listar autores
    response = await fetch(`${BASE_URL}/authors`);
    const authors = await response.json();
    console.log(`Total de autores: ${authors.length}`);
    
    // 5. Estatísticas
    response = await fetch(`${BASE_URL}/books/stats`);
    const stats = await response.json();
    console.log("Estatísticas:", stats);
  } catch (error) {
    console.error("Erro:", error);
  }
}

testAPI();
```

---

## ⏱️ Performance e Considerações

- **Cada página contém 32 livros** (limite padrão do Gutendex)
- **Importação de uma página**: ~2-5 segundos
- **Total de páginas**: ~2200+ (70.000+ livros)
- **Recomendação**: Importe em lotes e aguarde entre as requisições

---

## 📋 Checklist

- [ ] PostgreSQL rodando
- [ ] Aplicação iniciada com `mvn spring-boot:run`
- [ ] Testou `/api/import/health`
- [ ] Importou primeira página
- [ ] Verificou estatísticas
- [ ] Listou livros e autores
- [ ] Testou busca por ID

---

**Bom uso! 📚**
