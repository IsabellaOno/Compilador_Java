package io.compiler.core.ast;

import java.util.List;

public class ForCommand extends Command {

  	//int i = 0
	private String initialization;
	
	//Condição de continuação do laço
	private String condition;
	
	//Armazena o incremento/decremento do laço
	private String increment;
	
	//Lista de comandos a serem executados
	private List<Command> forCommands;

	public ForCommand() {
		super();
	}

	public ForCommand(String initialization, String condition, String increment, List<Command> forCommands) {
		super();
		this.initialization = initialization;
		this.condition = condition;
		this.increment = increment;
		this.forCommands = forCommands;
	}

	//Obter a inicialização do laço
	public String getInitialization() {
		return initialization;
	}

	//Definir a inicialização do laço
	public void setInitialization(String initialization) {
		this.initialization = initialization;
	}

	//Obter a condição do laço
	public String getCondition() {
		return condition;
	}

	//Definir a condição do laço
	public void setCondition(String condition) {
		this.condition = condition;
	}
  
	//Obter o incremento/decremento do laço
	public String getIncrement() {
		return increment;
	}

	//Definir o incremento/decremento do laço
	public void setIncrement(String increment) {
		this.increment = increment;
	}

	//Obter a lista de comandos
	public List<Command> getForCommands() {
		return forCommands;
	}

	//Definir a lista de comandos
	public void setForCommands(List<Command> forCommands) {
		this.forCommands = forCommands;
	}

	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("for(" + initialization + "; " + condition + "; " + increment + "){");
		for (Command cmd : forCommands) {
			str.append(cmd.generateTarget());
		}
		
		str.append("};\n");
		return str.toString();
	  }
}
