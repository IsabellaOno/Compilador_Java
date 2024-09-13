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
		String vari = this.ident;
		return vari + "= _key." + (this.var.getType() == 0 ? "nextDouble();" : "nextLine();");
	}
	
	@Override
	public String toString() {
		return "CommandLeitura [id=" + this.ident + "]";
	}

}
