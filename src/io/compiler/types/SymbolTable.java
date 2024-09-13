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
    	return map.get(id) != null;
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

    public void setHasValue(String id) {
        Var variable = (Var) this.get(id);
        if (variable != null) {
            variable.setInitialized(true);
        }
    }

    public String getTypeById(String id) {
		Var var = (Var) this.get(id);
		if (var.getType() == Var.NUMBER) {
			return "NUMBER";}
		else if (var.getType() == Var.REALNUMBER) {
			return "REALNUMBER";}
		else {
			return "TEXT";
		}
	}

    public void verificaAtribuicao(String id) throws IsiLanguageSemanticException {
        Symbol sym = this.get(id);
        if (sym == null) {
            throw new IsiLanguageSemanticException("A variável com ID " + id + " não foi declarada.");
        }
        if (!sym.isInitialized()) {
            throw new IsiLanguageSemanticException("A variável " + sym.getId() + " foi usada antes de ser atribuída");
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
        
    public void stringType(String id) throws IsiLanguageSemanticException {
    	Var var = (Var) this.get(id);
    	if (var.getType() == Var.TEXT) {
    		return;
    	}
    	throw new IsiLanguageSemanticException("Variável " + var.getId() + " é do tipo " + var.getTypeText() + " não pode ser receber texto.");
    	}   
}
