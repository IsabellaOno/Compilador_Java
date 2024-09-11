# 🖥️ Compiladores UFABC 2024.2

## 👥 Integrantes do Grupo
- **Isabella Bueno Ono** - RA: 11202020040
- **Leticia do Carmo Melo** - RA: 11201921202
- **Ingrid Maciel** - RA: 11201932563

## 📚 Tema do Projeto
Este projeto tem como objetivo desenvolver um compilador que converta uma linguagem especificada em uma linguagem alvo (C, Java, ou outras). O projeto também visa garantir a compilação correta do programa-objeto gerado.

## ✅ Itens Obrigatórios
1. **Possui 2 tipos de variáveis** 📝
2. **Possui a estrutura If..else** 🔄
3. **Possui estrutura de controle while/do while** 🔁
4. **Operações Aritméticas executadas corretamente** ➗
5. **Atribuições realizadas corretamente** ✍️
6. **Possui operações de Entrada e Saída** 📥📤
7. **Aceita números decimais** 🔢
8. **Verifica se a variável já foi previamente declarada** ⚠️
9. **Verifica se a variável foi declarada e não foi usada** 🛠️
10. **Verifica se uma variável está sendo usada sem valor inicial** 🚫

### Linguagem escolhida: Java 

## 🛠️ Execução

1. Você precisa baixar o **ANTLR** no [site oficial](https://www.antlr.org/download/antlr-4.13.2-complete.jar).
   
2. Referencie este JAR como biblioteca do seu projeto.

3. Para gerar os arquivos Java a partir do arquivo G4, use o seguinte comando:
   ```bash
   java -cp antlr-4.13.2-complete.jar org.antlr.v4.Tool IsiLanguage.g4 -o src/io/compiler/core -package io.compiler.core
