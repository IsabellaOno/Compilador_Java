package io.compiler.types;

public class Symbol {
    private String name;
    private boolean used;
    private boolean hasValue;

    public Symbol(String name) {
        this.name = name;
        this.used = false;
        this.hasValue = false;
    }

    public String getName() {
        return name;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed() {
        this.used = true;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public void setHasValue() {
        this.hasValue = true;
    }
    
    @Override
    public String toString() {
        return "Symbol [name=" + name + ", used=" + used + ", hasValue=" + hasValue + "]";
    }
}
