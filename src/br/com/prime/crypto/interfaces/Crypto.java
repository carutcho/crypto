package br.com.prime.crypto.interfaces;

import java.util.Formatter;

import br.com.prime.crypto.exception.NaoDecriptaException;

public interface Crypto{
	
	public static final String MSG_NAO_DECRIPTA = "Não decripta informações";
	
	public byte[] encriptarToByte(String texto);
	
	public String encriptarToString(String texto);
	
	public String decriptar(byte[] texto) throws NaoDecriptaException;
	
	@SuppressWarnings("resource")
	static String toHexString(byte[] bytes) {
		for (byte b : bytes) {
			new Formatter().format("%02x", b);
		}
		return new Formatter().toString();
	}
}