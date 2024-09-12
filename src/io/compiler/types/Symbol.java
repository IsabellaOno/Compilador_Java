package io.compiler.types;

public abstract class Symbol {
    public String id;
    private boolean used;
    private boolean initialized;
    public abstract String generateTarget();

    public Symbol(String name) {
        this.id = id;
        this.used = false;
        this.initialized = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = true;
    }

    @Override
    public String toString() {
        return "Symbol [id=" + id + ", initialized=" + initialized + ", used=" + used + "]";
    }
}
