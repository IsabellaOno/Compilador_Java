package io.compiler.core.ast;

import io.compiler.types.Var;

public class ReadCommand extends Command {

	private Var var;
	private String ident;
	String methodCall;

	public ReadCommand(String ident, Var var) {
		this.ident = ident;
		this.var = var;
	}
		
	@Override
	public String generateTarget() {
	    String vari = this.ident;

	    switch (this.var.getType()) {
	        case Var.NUMBER:
	            methodCall = "nextInt()";
	            break;
	        case Var.REALNUMBER:
	            methodCall = "nextDouble()";
	            break;
	        case Var.TEXT:
	            methodCall = "nextLine()";
	            break;
	    }
	    return vari + " = _scTrx." + methodCall + ";";
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + this.ident + "]";
	}

}
