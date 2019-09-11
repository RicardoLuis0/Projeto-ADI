package com.ricardoluis.db;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class DBHelper {
	public static byte[] generateSalt() {
		return new SecureRandom().generateSeed(32);
	}
	
	public static String generateSaltedHash(String data) {
		final int iter=65535;
		byte[] salt = generateSalt();
		byte[] hash = generateSaltedHash(iter,salt,data);
		return Integer.toHexString(iter)+":"+DatatypeConverter.printHexBinary(hash)+":"+DatatypeConverter.printHexBinary(salt);
	}
	
	public static byte[] generateSaltedHash(int iter,byte[] salt,String data) {
		try {
			return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(data.toCharArray(),salt,iter,64 * 8)).getEncoded();
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean verifySaltedHash(String hash,String data) {
		String[] values=hash.split(":");
		int iter=Integer.parseInt(values[0],16);
		byte[] hash_bytes=DatatypeConverter.parseHexBinary(values[1]);
		byte[] salt=DatatypeConverter.parseHexBinary(values[2]);
		return verifySaltedHash(iter,hash_bytes,salt,data);
	}
	
	public static boolean verifySaltedHash(int iter,byte[] hash,byte[] salt,String data) {
		return generateSaltedHash(iter,salt,data).equals(hash);
	}

}
