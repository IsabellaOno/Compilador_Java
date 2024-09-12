package io.compiler.core.ast;

import io.compiler.types.Var;

public class ReadCommand extends Command {

	private Var var;
	private String ident;

	public ReadCommand(String ident, Var var) {
		this.ident = ident;
		this.var = var;
	}
		
	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		return var.getId() + " = " + ((var.getType()==Var.NUMBER)?"_scTrx.nextInt();":"_scTrx.nextLine();")+"\n";
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + ident + "]";
	}

}
