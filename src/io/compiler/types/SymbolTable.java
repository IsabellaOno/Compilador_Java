package io.compiler.types;

import io.compiler.core.exceptions.IsiLanguageSemanticException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolTable {
    private Map<String, Symbol> map;
    
    public SymbolTable() {
        map = new HashMap<String, Symbol>();
    }
    
    public void add(Symbol sym) {
        map.put(sym.getId(), sym);
    }
    
    public boolean exists(String id) {
        return map.containsKey(id);
    }
    
    public Symbol get(String id) {
        Symbol symb = map.get(id);
        if (symb != null) {
        	symb.setUsed(true);
        }
        return symb;
    }
    
    public void markAsUsed(String id) {
    	Symbol symb = this.get(id);
        if (symb != null) {
        	symb.setUsed(true); 
        }
    }

    public List<Symbol> getNotUsedSymbols() {
        return map.values().stream()
                .filter(var -> !var.isUsed())
                .collect(Collectors.toList());
    }    

    public void assertStringType(String id) throws IsiLanguageSemanticException {
        Var variable = (Var) this.get(id);
        if (variable != null && variable.getType() == Var.TEXT) {
            return;
        }
        throw new IsiLanguageSemanticException("Variável " + (variable != null ? variable.getId() : id) + " não é do tipo texto");
    }

    public void setHasValue(String id) {
        Var variable = (Var) this.get(id);
        if (variable != null) {
            variable.setInitialized(true);
        }
    }

    public String getTypeById(String id) {
        Var variable = (Var) this.get(id);
        if (variable != null) {
            int type = variable.getType();
            switch (type) {
                case Var.NUMBER:
                    return "NUMBER";
                case Var.REALNUMBER:
                    return "REALNUMBER";
                case Var.TEXT:
                    return "TEXT";
                default:
                    return "Desconhecido";
            }
        }
        return "Desconhecido";
    }

    public void verificaAtribuicao(String id) throws IsiLanguageSemanticException {
        Symbol sym = this.get(id);
        if (sym == null || !sym.isInitialized()) {
            throw new IsiLanguageSemanticException("A variável " + sym.getId() + " foi usada antes de ser atribuida");
        }
    }
  
    public ArrayList<Symbol> getAll(){
		ArrayList<Symbol> lista = new ArrayList<Symbol>();
		for (Symbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}
    
    public boolean checkInitialized(String id) {
        Symbol symb = this.get(id);
        return symb != null && symb.isInitialized();
    }
}
