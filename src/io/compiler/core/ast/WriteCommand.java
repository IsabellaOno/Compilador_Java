package io.compiler.core.ast;

public class WriteCommand extends Command {
    private String content; // Pode ser uma variável ou texto
    private boolean isLiteral; // Define se o conteúdo é texto literal ou identificador (variável)

    // Construtor para conteúdo literal (texto)
    public WriteCommand(String content, boolean isLiteral) {
        this.content = content;
        this.isLiteral = isLiteral;
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
        if (content == null) {
            throw new IllegalStateException("Content cannot be null.");
        }
        if (isLiteral) {
            return "System.out.println(" + content + ");"; // Para texto literal
        } else {
            return "System.out.println(" + content + ");"; // Para identificador (variável)
        }
    }

    @Override
    public String toString() {
        return "WriteCommand [content=" + content + ", isLiteral=" + isLiteral + "]";
    }

    // Método de utilidade para validar o conteúdo
    public boolean isValid() {
        return content != null && !content.trim().isEmpty();
    }
}
