package io.compiler.core.ast;

public class AttribCommand extends Command {
    private String id;   
    private String expr;   

    public AttribCommand(String id, String expr) {
        this.id = id;
        this.expr = expr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    @Override
    public String generateTarget() {
    	return this.id + " = " + this.expr + ";";
    }


    @Override
    public String toString() {
        return "AttribCommand [id=" + this.id + ", expr=" + this.expr + "]";
    }
}
