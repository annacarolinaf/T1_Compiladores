package src;
public class Token {
    public String lexema;
    public TipoToken tipo;
    public String mensagemErro; // Campo adicional para erros

    public Token(String lexema, TipoToken tipo) {
        this.lexema = lexema;
        this.tipo = tipo;
    }

    public Token(String mensagemErro) {
        this.mensagemErro = mensagemErro;
        this.tipo = TipoToken.ERRO;
        this.lexema = ""; // Não faz sentido para erros, então deixamos em branco
    }

    public String getLexema() {
        return lexema;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    @Override
    public String toString() {
        if (tipo == TipoToken.ERRO) {
            return mensagemErro; // Exibe diretamente a mensagem de erro
        }

        if (tipo == TipoToken.IDENT || tipo == TipoToken.CADEIA || tipo == TipoToken.NUM_INT || tipo == TipoToken.NUM_REAL) {
            return "<'" + lexema + "'," + tipoToString() + ">";
        }
        
        return "<'" + lexema + "','" + tipoToString() + "'>";
    }

    private String tipoToString() {
        if (tipo == TipoToken.IDENT)
            return "IDENT";
        if (tipo == TipoToken.CADEIA)
            return "CADEIA";
        if (tipo == TipoToken.NUM_INT)
            return "NUM_INT";
        if (tipo == TipoToken.NUM_REAL)
            return "NUM_REAL";
        return lexema; // Para palavras-chave e símbolos
    }

    // Método estático para criar tokens de erro
    public static Token erro(String mensagem) {
        return new Token(mensagem);
    }
}
