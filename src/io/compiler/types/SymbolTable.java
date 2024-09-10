package io.compiler.types;

import io.compiler.core.exceptions.IsiLanguageSemanticException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolTable {
    private Map<String, Var> map;
    
    public SymbolTable() {
        map = new HashMap<>();
    }
    
    public void add(Var var) {
        map.put(var.getId(), var);
    }
    
    public boolean exists(String id) {
        return map.containsKey(id);
    }
    
    public Var get(String id) {
        Var var = map.get(id);
        if (var != null) {
            var.setUsed(true);
        }
        return var;
    }

    public List<Var> getNotUsedSymbols() {
        return map.values().stream()
                .filter(var -> !var.isUsed())
                .collect(Collectors.toList());
    }    

    public void assertStringType(String id) throws IsiLanguageSemanticException {
        Var variable = this.get(id);
        if (variable.getType() == Types.TEXT) {
            return;
        }
        throw new IsiLanguageSemanticException("Variável " + variable.getId() + " é do tipo " + variable.getTypeText() + " não pode receber um texto");
    }

    public void setHasValue(String id) {
        Var variable = this.get(id);
        if (variable != null) {
            variable.setInitialized(true);
        }
    }

    public String getTypeById(String id) {
        Var variable = this.get(id);
        if (variable != null) {
            return variable.getTypeText();
        }
        return "Desconhecido";
    }

    public void verificaAtribuida(String id) throws IsiLanguageSemanticException {
        Var var = this.get(id);
        if (var == null || !var.isInitialized()) {
            throw new IsiLanguageSemanticException("A variável " + (var != null ? var.getId() : id) + " é utilizada antes de ser atribuída");
        }
    }
    
    public List<Var> getAll() {
        return new ArrayList<>(map.values());
    }
}
