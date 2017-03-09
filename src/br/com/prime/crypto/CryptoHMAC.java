package br.com.prime.crypto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import br.com.prime.crypto.enums.TipoCryptoEnum;
import br.com.prime.crypto.exception.NaoDecriptaException;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoHMAC implements Crypto{
	
	private byte[] chave;
	private TipoCryptoEnum tipoHmac;
	
	public CryptoHMAC(byte[] chave, TipoCryptoEnum tipoHmac){
		this.tipoHmac = tipoHmac;
		this.chave = chave;
	}
	
	public byte[] encriptarToByte(String mensagem){
		try{
			SecretKeySpec signingKey = new SecretKeySpec(chave, tipoHmac.getLabel());
			Mac mac = Mac.getInstance(tipoHmac.getLabel());
			mac.init(signingKey);
			return mac.doFinal(mensagem.getBytes());
		}catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}

	public String encriptarToString(String mensagem){
		return Crypto.toHexString(encriptarToByte(mensagem));
	}
	
	public String decriptar(byte[] mensagem) throws NaoDecriptaException{
		throw new NaoDecriptaException(Crypto.MSG_NAO_DECRIPTA);
	}
}
