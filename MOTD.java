import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;

/**
 * MOTD - Message of the Day Class decrypts the encrypted message
 * 
 * @author Mae Hedmann-Highmore
 * @version 1.0
 */

public class MOTD {

	static String code;
	static String strURL;
	static String strOut = "";

	/**
	 * Constructs MOTD Class instanace
	 */
	public MOTD() {

	}

	/**
	 * @return Encrypted message retrieved from http address
	 */
	public static String getMOTD() {
		try {

			URL url = new URL("http://cswebcat.swansea.ac.uk/puzzle");
			Scanner scan = new Scanner(url.openStream());
			code = scan.next();
			scan.close();

			strURL = "http://cswebcat.swansea.ac.uk/message?solution=" + solve(code);
			URL url2 = new URL(strURL);
			Scanner scan2 = new Scanner(url2.openStream());
			strOut = scan2.nextLine();
			scan2.close();

		} catch (Exception e) {

		}
		return strOut;
	}

	/**
	 * Method to decipher the encrypted message using cypher
	 * 
	 * @param code Encrypted message
	 * @return Decrypted message
	 */
	private static String solve(String code) {
		char[] charOut = new char[code.length()];
		String solved = "";
		for (int i = 0; i < code.length(); i++) {
			if (i % 2 == 0) {
				charOut[i] = cypher(-i - 1, code.charAt(i));
			} else {
				charOut[i] = cypher(i + 1, code.charAt(i));
			}
		}

		solved = "CS-230" + String.valueOf(charOut);
		return solved + solved.length();
	}

	/**
	 * A form of substitution cipher
	 * 
	 * @param index Position of the character
	 * @param C     Character in the message
	 * @return Deciphered character
	 */
	private static Character cypher(int index, Character C) {
		int ascii = (int) C;
		int out;
		if (ascii + index > 90) {
			out = 64 + (index + ascii - 90);
		} else if (ascii + index < 65) {
			out = 91 + (index + ascii - 65);
		} else {
			out = ascii + index;
		}

		return (char) out;
	}
}
