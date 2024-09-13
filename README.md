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

## Exemplos de códigos do input.in utilizados para conferir itens obrigatórios:
Item 1 - Declaração de Variáveis:
```bash
programa
	declare inteiro a,b,c,d.
	declare texto l.
	declare real t.
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

	se (a<b) entao
	{
	c := a + b.
	}senao
	{
	c := a - b.
	}

	enquanto (b>a)
	{
	c := a * b.
	}
	
	faca
	{
 	c := a + b.
	}
	enquanto (a<b)
	
fimprog.
```
Item 5 - Atribuições realizadas corretamente.
```bash
programa
	declare inteiro a,b.
	declare texto l.
	
	a:=2.
	b:=6.3.
	
	t:=a+b.
	
fimprog.
````

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
Error: Símbolo f não inicializado.

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
Error: A variável c foi usada antes de ser atribuída.

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
Error: A variável d foi usada antes de ser atribuída.

## :memo: Gramática

Prog -> **programa** Declara Bloco **fimprog.**  
Declara -> **declare** Id (, Id)* .  
Bloco -> (Cmd. )+  
Comando -> CmdAttrib | CmdLeitura | CmdEscrita | CmdSe | CmdEnquanto | cmdFacaEnquanto  
CmdLeitura -> **leia**( Id )  
CmdEscrita -> **escreva**( Texto | Id )  
CmdIf -> “**se**” ‘(‘ Expr Op_rel Expr ‘)’ **entao** ‘{‘ Cmd+ ‘}’ (“**senao**” ‘{‘ Cmd+ ‘}’ )?  
expr -> Id := Expr  
OpRel -> ‘<’ | ‘>’ | “<=” | “>=” | “!=” | “==”  
expr -> Expr + Termo | Expr – Termo | Termo  
Termo -> Termo * Fator | Termo / Fator | Fator  
Fator -> Id | Numero | NumeroReal | ( Expr ) | Texto  
Texto -> “(0..9 | a..z | A..Z | ‘ ‘ | )+ ”  
Numero -> (0..9)+  
NumeroReal -> (0-9)+ ('.' (0-9)+)  
Id -> (a..z | A..Z) (a..z | A..Z | 0..9)*  
Texto -> '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-')* '"'  

## 🛠️ Execução
   
1. Referencie o JAR do ANTLR presente no projeto como biblioteca do seu projeto.

2. Para transformar um código de IsiLanguage para java digite-o no arquivo **input.in** e salve-o.

3. Execute a MainClass do compilador.

4. Seu código será exibido no console e um arquivo **meuPrograma.java** com seu código em java será gerado.

### ▶️ Link do vídeo no Youtube: [Link](https://youtu.be/3afily28Dkg)
