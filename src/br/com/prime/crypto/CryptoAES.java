package br.com.prime.crypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import br.com.prime.crypto.enums.TipoCryptoEnum;
import br.com.prime.crypto.interfaces.Crypto;

public class CryptoAES implements Crypto{

	private byte[] key;
	private static String AES = TipoCryptoEnum.AES.getLabel();
	
	public CryptoAES(byte[] key){
		this.key = key;
	}
	
	public String decriptar(byte[] mensagemEncriptada){

		try {
			Cipher cipher = Cipher.getInstance(TipoCryptoEnum.AES.getLabel());
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, AES));
			return new String(cipher.doFinal(mensagemEncriptada));
		} catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}
	
	public byte[] encriptarToByte(String mensagem){
		try{
			Cipher cipher = Cipher.getInstance(TipoCryptoEnum.AES.getLabel());
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, AES));
			return cipher.doFinal(mensagem.getBytes());
		}catch (Exception e) {
			return null;
			//logar erro de criptografia
		}
	}

	public String encriptarToString(String mensagem){
		return Crypto.toHexString(encriptarToByte(mensagem));
	}
	
	public static byte[] gerarChave() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(AES);
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();
	}
}
