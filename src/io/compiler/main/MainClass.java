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
			
			System.out.println(program.generateTarget());
			try {
				File f = new File(program.getName()+".java");
				FileWriter fr = new FileWriter(f);
				PrintWriter pr = new PrintWriter(fr);
				pr.println(program.generateTarget());
				pr.close();
			}
			catch (IOException ex) {
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
