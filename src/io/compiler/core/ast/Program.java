package io.compiler.core.ast;

import io.compiler.types.SymbolTable;
import io.compiler.types.Symbol;
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
        str.append("		 Scanner scanner = new Scanner(System.in);\n");
        
        for (Symbol symbol: symbolTable.getAll()) {
			str.append("		").append(symbol.generateTarget()+"\n");
		}
        
        for (Command command: commandList) {
			str.append("		").append(command.generateTarget()+"\n");
		}
        
        str.append("    }\n");
        str.append("}\n");
        
        return str.toString();
    }
    
}
