package br.com.daciosoftware.tarefa;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	/**
	 * 
	 * @param senha
	 * @return senha criptografa com algoritimo SHA-256
	 */
	public static String criptografaSenha(String senha){
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
			  hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();	
		} catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
			e.printStackTrace();
			return senha;
		}
	}

}
