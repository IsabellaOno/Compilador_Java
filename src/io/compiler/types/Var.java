package io.compiler.types;

public class Var {
    private String id;
    private Types type;
    private boolean initialized;
    private boolean used;

    public Var(String id, Types type) {
        this.id = id;
        this.type = type;
        this.initialized = false; // Inicialmente, a variável não está inicializada.
        this.used = false; // Inicialmente, a variável não está usada.
    }
    
    public Var(String id) {
        this(id, null);
    }
    
    public Var() {
        this(null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
    public String getTypeText() {
        if (type != null) {
            return type.name();
        }
        return "Desconhecido";
    }

    @Override
    public String toString() {
        return "Var [id=" + id + ", type=" + type + ", initialized=" + initialized + ", used=" + used + "]";
    }
    
    public String generateTarget() {
        String str;
        switch (this.type) {
            case NUMBER:
                str = "int "; 
                break;
            case REALNUMBER:
                str = "double ";
                break;
            case TEXT:
                str = "String ";
                break;
            default:
                throw new IllegalArgumentException("Tipo desconhecido: " + this.type);
        }
        return str + this.id + ";";
    }
}
