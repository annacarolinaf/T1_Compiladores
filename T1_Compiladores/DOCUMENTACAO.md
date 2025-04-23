# Documentação Externa - Trabalho 1 de Construção de Compiladores

## Requisitos

- Java JDK 17 ou superior
- Ferramentas de linha de comando (`javac`, `jar`, `java`)
- [Opcional] IDE como VSCode ou IntelliJ para facilitar edição

### Sistema Operacional testado:
- Ubuntu 22.04 LTS
  
## Para compilar o projeto, use:

javac src/*.java -d bin

## Depois de compilar, gere o .jar executável com o comando:

jar cfe analisador.jar Main -C bin .

## Execução com o corretor automático:

java -jar /home/anna/Downloads/compiladores-corretor-automatico/target/compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -jar /home/anna/Downloads/T1_Compiladores/analisador.jar" gcc /home/anna/temp/ /home/anna/Downloads/casos-de-teste/casos-de-teste "811448" t1

Observação: O caminho deve ser alterado para o de cada computador.
