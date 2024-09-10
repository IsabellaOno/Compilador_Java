package io.compiler.core.ast;

import java.util.List;

public class WhileCommand extends Command {

  //Atributo que armazena a expressão condicional do laço "while"
	private String expression;
  //Lista de comandos a serem executados dentro do bloco "while".
	private List<Command> whileCommands;

  //Obter a expressão condicional
	public String getExpression() {
		return expression;
	}

  //Definir a expressão condicional
	public void setExpression(String expression) {
		this.expression = expression;
	}

  //Obter a lista de comandos
	public List<Command> getCommands() {
		return whileCommands;
	}

  //Definir a lista de comandos
	public void setList(List<Command> whileCommands) {
		this.whileCommands = whileCommands;
	}

  //Recebe a expressão condicional e a lista de comandos.
	public WhileCommand(String expression, List<Command> whileCommands) {
		this.expression = expression;
		this.whileCommands = whileCommands;
	}

	public WhileCommand() {
	}

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while (" + expression + "){");
		for (Command cmd: whileCommands) {
			str.append(cmd.generateTarget());
		}
    
		str.append("};\n");
		return str.toString();
	}	
}
