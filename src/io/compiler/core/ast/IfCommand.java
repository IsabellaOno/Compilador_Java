package io.compiler.core.ast;

import java.util.List;

public class IfCommand extends Command{

	private String expression;
	private String comand;
	private List<Command> trueList;
	private List<Command> falseList;
	
	public IfCommand() {
		super();
	}

	public IfCommand(String comand, String expression, List<Command> trueList, List<Command> falseList) {
		super();
		this.comand = comand;
		this.expression = expression;
		this.trueList = trueList;
		this.falseList = falseList;
	}

	//Obter a expressão condicional
	public String getExpression() {
		return expression;
	}

	//Definir a expressão condicional
	public void setExpression(String expression) {
		this.expression = expression;
	}

	//Obter a lista de comandos
	public List<Command> getTrueList() {
		return trueList;
	}

	//Definir a lista de comandos
	public void setTrueList(List<Command> trueList) {
		this.trueList = trueList;
	}

	public List<Command> getFalseList() {
		return falseList;
	}

	public void setFalseList(List<Command> falseList) {
		this.falseList = falseList;
	}

	@Override
	public String generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("\n		if ("+expression+"){\n");
		for (Command cmd: trueList) {
			str.append("		  ").append(cmd.generateTarget());
		}
		str.append("\n");
		
		System.out.println(this.comand);
		if (!falseList.isEmpty()) {
			if (this.comand == "entao") {
				str.append(" 		}\n		else if ("+expression+") {\n");
				for (Command cmd: falseList) {
					str.append("		  ").append(cmd.generateTarget());}
			}
			else {
			str.append(" 		}\n		else {\n");
			for (Command cmd: falseList) {
				str.append("		  ").append(cmd.generateTarget());
			}}
		}
		
		str.append("\n		}\n");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandDecisao [expression=" + expression + ", trueList=" + trueList + ", falseList=" + falseList+ "]";
	}
}
