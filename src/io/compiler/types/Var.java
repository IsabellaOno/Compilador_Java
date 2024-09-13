package io.compiler.types;

public class Var extends Symbol {
	
	public static final int NUMBER = 0;
	public static final int REALNUMBER = 1;
	public static final int TEXT  = 2;
	
	 private int type;  
	 private String value;

	 public Var(String id, String value, int type) {
		super(id);
        this.value = value;
        this.type = type; 
    }
    
	 public Var(String id, int type) {
	        this(id, null, type);
	    }
	    
	    public Var() {
	        this(null, null, NUMBER); 
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public int getType() {
	        return type;
	    }

	    public void setType(int type) {
	        this.type = type;
	    }

	    public Object getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;}
	    
	    public String getTypeText() {
	        switch (type) {
	            case NUMBER:
	                return "int";
	            case REALNUMBER:
	                return "double";
	            case TEXT:
	                return "String";
	            default:
	                return "Desconhecido";
	        }
	    }

	    @Override
	    public String toString() {
	        return "Var [id=" + id + ", type=" + getTypeText() + ", value=" + value +  "]";
	    }
	    
	    @Override
	    public String generateTarget() {
	        String str;
	        if (type == NUMBER) {
	        	str = "int ";
	        }
	        else if (type == REALNUMBER) {
		     	str = "double ";
		        }
	        else if (type == TEXT) {
	        	str = "String ";
	        }
	        else {
	        	str = "Object ";
	        }
	        return (str + " "+super.id+";");
	 	}
	}