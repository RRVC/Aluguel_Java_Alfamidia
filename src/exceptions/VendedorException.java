package exceptions;

public class VendedorException extends RuntimeException {

    public String erro;

    public VendedorException(String erro) {
        super(erro);
    }

}