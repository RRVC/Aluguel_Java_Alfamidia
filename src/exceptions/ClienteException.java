package exceptions;

public class ClienteException extends RuntimeException {

    public String erro;

    public ClienteException(String erro) {
        super(erro);
    }
}