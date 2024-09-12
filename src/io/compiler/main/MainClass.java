package io.compiler.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import io.compiler.core.IsiLanguageLexer;
import io.compiler.core.IsiLanguageParser;
import io.compiler.core.ast.Program;
import io.compiler.types.SymbolTable;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLanguageLexer lexer;
			IsiLanguageParser parser;
			
			//Cria o analisador léxico a partir da leitura de um arquivo
			lexer = new IsiLanguageLexer(CharStreams.fromFileName("input.in"));
			
			//A partir do analisador léxico, obtemos um fluxo de tokens
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			//Cria o parser a partir do tokenStream
			parser = new IsiLanguageParser(tokenStream);
			
			System.out.println("Compilador");
			parser.programa();
			System.out.println("Compilado com sucesso");

			//geração do código do programa
			Program program = parser.getProgram();

			SymbolTable symbolTable = program.getsymbolTable();
            if (symbolTable == null) {
                symbolTable = new SymbolTable(); // Inicializa a SymbolTable se estiver nula
                program.setsymbolTable(symbolTable);
            }
            
            if (program.getCommandList() == null) {
                System.err.println("Erro: A lista de comandos não foi inicializada.");
                return;  // Encerra se a lista de comandos estiver nula
            }

            System.out.println(program.getName());
            System.out.println(program.generateTarget());
			
			System.out.println(program.generateTarget());
			
			try (FileWriter fw = new FileWriter(new File("meuPrograma.java"));
	                 PrintWriter pw = new PrintWriter(fw)) {
	                pw.println(program.generateTarget());
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
			//System.out.println(program.generateTarget());
			
		}
		catch(Exception ex) {
			System.err.println("Error: "+ex.getMessage());
			//ex.printStackTrace();
		}
	}
}
