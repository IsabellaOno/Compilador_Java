package io.compiler.core.ast;

import java.util.List;

public class DoWhileCommand extends Command {

  //Atributo que armazena a expressão condicional do laço "while"
	private String expression;

  //Lista de comandos a serem executados
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
	public List<Command> getList() {
		return whileCommands;
	}

  //Definir a lista de comandos
	public void setList(List<Command> whileCommands) {
		this.whileCommands = whileCommands;
	}

  //Recebe a expressão condicional e a lista de comandos
	public DoWhileCommand(String expression, List<Command> whileCommands) {
		this.expression = expression;
		this.whileCommands = whileCommands;
	}

	public DoWhileCommand() {
	}

	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("do {");
		for (Command cmd: whileCommands) {
			str.append(cmd.generateTarget());
		}
		str.append("} while (" + expression + ");\n");
		return str.toString();
	}
	
	@Override
    public String toString() {
        return "DoWhileCommand {expression='" + expression + "', whileCommands=" + whileCommands + "}";
    }
}
