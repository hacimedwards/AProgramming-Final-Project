package Client;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;


public class PasswordHashGen {
	
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH_BITS = 256;
	
	private static KeySpec spec = null;
	private SecretKeyFactory factory = null;
	private SecretKey secretKey = null;
	
	public static byte[] genPasswordHash(String password, byte[] salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException{
		spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH_BITS);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        
        SecretKey secretKey = factory.generateSecret(spec);
		return secretKey.getEncoded();
		
	}
	
	public static byte[] generateSalt() {
		SecureRandom rand = new SecureRandom();
		byte[] salt = new byte[16];
		rand.nextBytes(salt);
		return salt;
		
	}
	
	public static ArrayList<byte[]> encryptPassword(String password) {
		byte[] salt = generateSalt();
		ArrayList<byte[]> result = new ArrayList<>();
		try {
			byte[] passwordHash = genPasswordHash(password, salt);
			 result.add(passwordHash);
			 result.add(salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}

