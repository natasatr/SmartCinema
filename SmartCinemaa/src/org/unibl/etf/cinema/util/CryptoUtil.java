package org.unibl.etf.cinema.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoUtil {
	
	public static String hash(String string) {
		int workload = 12;
		String salt = BCrypt.gensalt(workload);
		String passHash = BCrypt.hashpw(string, salt);
		return passHash;
	}

	public static boolean verify(String string, String hash) {
		boolean verified = false;
		if (hash == null || !hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Heš otisak nije validan.");
		verified = BCrypt.checkpw(string, hash);
		return verified;
	}
}
