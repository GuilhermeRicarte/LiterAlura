@echo off
REM Script para inicializar e executar o projeto Book Catalog

echo ======================================
echo Book Catalog - Gutendex API
echo ======================================
echo.

REM Verificar se Maven está instalado
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Erro: Maven nao esta instalado. Por favor, instale Maven primeiro.
    echo Visite: https://maven.apache.org/install.html
    pause
    exit /b 1
)

REM Verificar se Java está instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Erro: Java nao esta instalado. Por favor, instale Java 17+ primeiro.
    echo Visite: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo Sucesso: Java e Maven encontrados
echo.

echo Escolha uma opcao:
echo 1) Compilar o projeto
echo 2) Executar testes
echo 3) Executar a aplicacao
echo 4) Limpar e compilar
echo 5) Compilar, testar e executar
echo.

set /p option="Digite o numero da opcao (1-5): "

if "%option%"=="1" (
    echo Compilando o projeto...
    call mvn clean compile
) else if "%option%"=="2" (
    echo Executando testes...
    call mvn test
) else if "%option%"=="3" (
    echo Executando a aplicacao...
    echo Acessar em: http://localhost:8080/api
    call mvn spring-boot:run
) else if "%option%"=="4" (
    echo Limpando e compilando...
    call mvn clean compile
) else if "%option%"=="5" (
    echo Compilando, testando e executando...
    call mvn clean compile
    call mvn test
    echo.
    echo Executando a aplicacao...
    echo Acessar em: http://localhost:8080/api
    call mvn spring-boot:run
) else (
    echo Opcao invalida!
    pause
    exit /b 1
)

pause
