/**
 * Classe que representa um token identificado pelo analisador léxico.
 * Cada token possui um lexema (texto correspondente no código-fonte)
 * e um tipo (classificado segundo o enum TipoToken).
 */
public class Token {

 
    // O texto original do token, extraído do código-fonte
    public String lexema;
 
    // O tipo do token, baseado na enumeração TipoToken
    public TipoToken tipo;
 

    public String mensagemErro; // Campo adicional para erros
 
    /**
     * Construtor padrão para tokens válidos.
     *
     * @param lexema o texto do token (ex: "leia", "idade", "42")
     * @param tipo o tipo do token (ex: IDENT, NUM_INT, etc.)
     */
    public Token(String lexema, TipoToken tipo) {
 

        this.lexema = lexema;
 

        this.tipo = tipo;
 

    }
 

     /**
     * Construtor específico para tokens inválidos.
     *
     * @param mensagemErro descrição do erro léxico encontrado
     */
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
 

    /**
     * Retorna uma representação em string do token,
     * com formatação especial para tokens válidos e erros.
     */
    @Override
 

    public String toString() {
 

        if (tipo == TipoToken.ERRO) {
 

            return mensagemErro; // Exibe diretamente a mensagem de erro
 

        }
 


 
          // Formata tokens do tipo IDENT, CADEIA, NUM_INT, NUM_REAL
        if (tipo == TipoToken.IDENT || tipo == TipoToken.CADEIA || tipo == TipoToken.NUM_INT || tipo == TipoToken.NUM_REAL) {
 

            return "<'" + lexema + "'," + tipoToString() + ">";
 

        }
 

        
       // Formata os demais tokens (como palavras reservadas, operadores, símbolos)
        return "<'" + lexema + "','" + tipoToString() + "'>";
 

    }
 


 
    /**
     * Auxiliar para converter o tipo do token para uma string amigável.
     * Para palavras-chave e símbolos, retorna o próprio lexema.
     */
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
