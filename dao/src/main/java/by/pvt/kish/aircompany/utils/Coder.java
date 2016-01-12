package by.pvt.kish.aircompany.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author  Kish Alexey
 */
public class Coder {

	static Logger logger = Logger.getLogger(Coder.class.getName());

	private static String ALGORITHM = "MD5";
	private static int POSITIVE = 1;

	public static String getHashCode(String password) {
		try {
			MessageDigest dig = MessageDigest.getInstance(ALGORITHM);
			BigInteger hash = new BigInteger(POSITIVE, dig.digest(password.getBytes()));
			return String.format("%032x", hash);

		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithm " + ALGORITHM + "; " + e);
		}
		throw new RuntimeException("Exception at Coder: NoSuchAlgorithm");
	}
}
