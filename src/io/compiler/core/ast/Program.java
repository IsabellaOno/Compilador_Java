package io.compiler.core.ast;

import io.compiler.types.SymbolTable;
import io.compiler.types.Var;
import java.util.HashMap;
import java.util.List;

public class Program {
    private SymbolTable symbolTable;
    private List<Command> commandList;
    
    public SymbolTable getsymbolTable() {
        return symbolTable;
    }
    
    public void setsymbolTable(SymbolTable symbolTable2) {
        this.symbolTable = symbolTable2;
    }
    
    public List<Command> getCommandList() {
        return commandList;
    }
    
    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }
    
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("import java.util.Scanner;\n");
        str.append("public class MainClass{ \n");
        str.append("	public static void main(String args[]){\n ");
        str.append("        Scanner _scTrx = new Scanner(System.in);\n");
        
        for (Var var : symbolTable.getAll()) {
            if (null == var.getType()) {
                str.append("        String ");
            } else switch (var.getType()) {
                case NUMBER -> str.append("        int ");
                case REALNUMBER -> str.append("        double ");
                default -> str.append("        String ");
            }
            str.append(var.getId()).append(";\n");
        }
        
        for (Command cmd : commandList) {
            str.append(cmd.generateTarget()).append("\n");
        }
        
        str.append("    }\n");
        str.append("}\n");
        
        return str.toString();
    }
    
}
