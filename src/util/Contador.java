package util;

public abstract class Contador {
	private static int VALOR = 0;

	public static Integer proximoId() {
		VALOR++;
		return VALOR; // Não pode retornar this.VALOR porque VALOR é estatico e nunca vai fazer parte da classe.
	}



}
