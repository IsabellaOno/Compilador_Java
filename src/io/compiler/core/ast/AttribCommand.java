package io.compiler.core.ast;

import io.compiler.types.Symbol;
import io.compiler.types.SymbolTable;
import io.compiler.core.exceptions.IsiLanguageSemanticException;

public class AttribCommand extends Command {
    private String id;   
    private String expr;   
    private SymbolTable symbolTable; 

    public AttribCommand(String id, String expr, SymbolTable symbolTable) {
        this.id = id;
        this.expr = expr;
        this.symbolTable = symbolTable;
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
        Symbol sym = symbolTable.get(id);
        if (sym == null) {
            throw new RuntimeException("Variable not declared: " + id);
        }

        if (!sym.isInitialized()) {
            throw new IsiLanguageSemanticException("Variable " + id + " has not been initialized");
        }

        return id + " = " + expr + ";\n";
    }

    @Override
    public String toString() {
        return "AttribCommand [id=" + id + ", expr=" + expr + "]";
    }
}
