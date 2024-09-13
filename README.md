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

## Exemplos do input:
Item 1 - Declaração de Variáveis:
```bash
programa
   declare inteiro a, b, c.
   declare real d.
fimprog.
```
Itens 2, 3, 4, 5, 6 e 7 - Operações Diversas e Estruturas de Controle:
```bash
programa
	declare inteiro a,b,c.
 	declare real d.

  	d := 2.5.
    	escreva("Programa Teste").
     
	escreva("Digite A").
	leia(a).
	escreva ("Digite B").
	leia (b).


	se (a<b)
	{
	c := a + b.
	}entao (a>b)
	{
 	c := a - b.
	}senao
	{
	c := a *b.
	}
	
	faca
	{
 	c := a + b.
	}
	enquanto (a<b)
	
fimprog.
```

Item 8 - Confere se variável já foi previamente declarada.
```bash
programa
	declare inteiro a,b,c,d.
   escreva("Programa Calculando Soma").
    
   a:= 3.
	b:= 7.
	c:= 2.
	f:=a+b+c.
	
fimprog.
```
ERRO: Error: Símbolo f não inicializado.

Item 9 - Confere se variável foi declarada e não foi usada.
```bash
programa
	declare inteiro a,b,c.
    escreva("Programa Testando Item 9").
     
	escreva("Digite A").
	leia(a).
	escreva ("Digite B").
	leia (b).

	faca
	{
 	a := b + c.
	}
	enquanto (a<b)
	
fimprog.
```
ERRO: Error: A variável c foi usada antes de ser atribuída.

Item 10 - Confere se variável está sendo usada sem valor inicial.
```bash
programa
	declare inteiro a,b,c,d.
	declare inteiro f.
    escreva("Programa Calculando Soma").
    
    a:= 3.
	b:= 7.
	c:= 2.
	f:=a+b+c+d.
	
fimprog.

```
ERROR: Error: A variável d foi usada antes de ser atribuída.


## 🛠️ Execução

1. Você precisa baixar o **ANTLR** no [site oficial](https://www.antlr.org/download/antlr-4.13.2-complete.jar).
   
2. Referencie este JAR como biblioteca do seu projeto.

3. Para gerar os arquivos Java a partir do arquivo G4, use o seguinte comando:
   ```bash
   java -cp antlr-4.13.2-complete.jar org.antlr.v4.Tool IsiLanguage.g4 -o src/io/compiler/core -package io.compiler.core
