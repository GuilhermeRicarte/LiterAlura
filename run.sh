#!/bin/bash

# Script para inicializar e executar o projeto Book Catalog

echo "======================================"
echo "Book Catalog - Gutendex API"
echo "======================================"
echo ""

# Verificar se Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven não está instalado. Por favor, instale Maven primeiro."
    echo "Visite: https://maven.apache.org/install.html"
    exit 1
fi

# Verificar se Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java não está instalado. Por favor, instale Java 17+ primeiro."
    echo "Visite: https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

echo "✅ Java e Maven encontrados"
echo ""

# Ofercer opções
echo "Escolha uma opção:"
echo "1) Compilar o projeto"
echo "2) Executar testes"
echo "3) Executar a aplicação"
echo "4) Limpar e compilar"
echo "5) Compilar, testar e executar"
echo ""
read -p "Digite o número da opção (1-5): " option

case $option in
    1)
        echo "Compilando o projeto..."
        mvn clean compile
        ;;
    2)
        echo "Executando testes..."
        mvn test
        ;;
    3)
        echo "Executando a aplicação..."
        echo "Acessar em: http://localhost:8080/api"
        mvn spring-boot:run
        ;;
    4)
        echo "Limpando e compilando..."
        mvn clean compile
        ;;
    5)
        echo "Compilando, testando e executando..."
        mvn clean compile
        mvn test
        echo ""
        echo "Executando a aplicação..."
        echo "Acessar em: http://localhost:8080/api"
        mvn spring-boot:run
        ;;
    *)
        echo "Opção inválida!"
        exit 1
        ;;
esac
