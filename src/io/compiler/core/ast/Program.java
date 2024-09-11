package io.compiler.core.ast;

import io.compiler.types.Var;
import java.util.HashMap;
import java.util.List;

public class Program {
    private String name;
    private HashMap<String, Var> symbolTable;
    private List<Command> commandList;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public HashMap<String, Var> getSymbolTable() {
        return symbolTable;
    }
    
    public void setSymbolTable(HashMap<String, Var> symbolTable) {
        this.symbolTable = symbolTable;
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
        str.append("public class ").append(name).append(" {\n");
        str.append("    public static void main(String[] args) {\n");
        str.append("        Scanner _scTrx = new Scanner(System.in);\n");
        
        for (Var var : symbolTable.values()) {
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
