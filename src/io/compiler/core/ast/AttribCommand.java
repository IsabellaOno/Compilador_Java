package io.compiler.core.ast;

import io.compiler.types.Var;
import io.compiler.types.Types;

public class AttribCommand extends Command {
    private String id;   
    private String expr;  

    public AttribCommand(String id, String expr) {
        this.id = id;
        this.expr = expr;
    }

    public AttribCommand(String id) {
        this.id = id;
    }

    public AttribCommand() {
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
        return this.getId() + "=" + this.getContent() + ";\n";
    }

    @Override
    public String toString() {
        return "AttribCommand [id=" + id + ", expr=" + expr + "]";
    }
}
