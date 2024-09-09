package io.compiler.types;

public class Var {
	private String id;
	private Types type;
	private boolean initialized;
	private boolean used; // Nova flag para verificar se a variável foi usada
	
	public Var(String id, Types type) {
		super();
		this.id = id;
		this.type = type;
		this.initialized = false; // Inicialmente, a variável não está inicializada. NÃO SEI SE ESTÁ CERTO
		this.used = false; // Inicialmente, a variável não está usada. NÃO SEI SE ESTÁ CERTO
	}
	public Var(String id) {
		super();
		this.id = id;
		this.initialized = false;
        this.used = false;
	}
	public Var() {
		super();
		this.initialized = false;
        this.used = false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	public boolean isInitialized() {
		return initialized;
	}
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	public void setUsed(boolean used) {
		this.used = used;
	}
	@Override
	public String toString() {
		return "Var [id=" + id + ", type=" + type + ", initialized=" + initialized + ", used=" + used + "]";
	}
}
