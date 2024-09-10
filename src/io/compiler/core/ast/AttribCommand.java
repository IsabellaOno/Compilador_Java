package io.compiler.core.ast;

import io.compiler.types.Var;
import io.compiler.types.SymbolTable;
import io.compiler.core.exceptions.IsiLanguageSemanticException;

public class AttribCommand extends Command {
    private String id;     // Identificador da variável
    private String expr;   // Expressão a ser atribuída
    private SymbolTable symbolTable; // Referên'cia à tabela de símbolos

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
        // Verifica se a variável foi declarada e inicializada
        Var var = symbolTable.get(id);
        if (var == null) {
            throw new RuntimeException("Variable not declared: " + id);
        }
        
        // Verifica se a variável foi inicializada
        if (!var.isInitialized()) {
            throw new IsiLanguageSemanticException("Variable " + id + " has not been initialized");
        }

        // Gera o código de atribuição considerando o tipo da variável
        return id + " = " + expr + ";\n";
    }

    @Override
    public String toString() {
        return "AttribCommand [id=" + id + ", expr=" + expr + "]";
    }
}
