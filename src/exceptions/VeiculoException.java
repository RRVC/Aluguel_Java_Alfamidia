package exceptions;

public class VeiculoException extends RuntimeException {

    public String erro;

    public VeiculoException(String erro) {
        super(erro);
    }


}