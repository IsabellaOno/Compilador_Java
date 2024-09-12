package io.compiler.core.ast;

public class WriteCommand extends Command {
    private String content; // Pode ser uma variável ou texto
    private boolean isLiteral; // Define se o conteúdo é texto literal ou identificador (variável)

    // Construtor para conteúdo literal (texto)
    public WriteCommand(String content, boolean isLiteral) {
        this.content = content;
        this.isLiteral = isLiteral;
        System.out.println("CHEGUEI");
        System.out.println(content);
    	System.out.println(isLiteral);
        
    }
    
    // Construtor para conteúdo que é uma variável
    public WriteCommand(String content) {
        this(content, false); // Por padrão, assume-se que é um identificador
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content, boolean isLiteral) {
        this.content = content;
        this.isLiteral = isLiteral;
    }
    
    // Método que gera o código Java para o comando de escrita
    @Override
    public String generateTarget() {
    	
    	System.out.println(content);
    	System.out.println(isLiteral);
        if (content == null) {
            System.out.println("Error: Content is null.");
            throw new IllegalStateException("Content cannot be null.");
        }
        System.out.println("Generating code for content: " + content + " with isLiteral: " + isLiteral);
        if (isLiteral) {
            return "System.out.println(" + content + ");\n"; // Para texto literal
        } else {
            return "System.out.println(" + content + ");\n"; // Para identificador (variável)
        }
    }

    // Método de depuração para ver o estado do comando
    @Override
    public String toString() {
        return "EscritaCommand [content=" + content + ", isLiteral=" + isLiteral + "]";
    }

    // Método de utilidade para validar o conteúdo
    public boolean isValid() {
        return content != null && !content.trim().isEmpty();
    }
}
