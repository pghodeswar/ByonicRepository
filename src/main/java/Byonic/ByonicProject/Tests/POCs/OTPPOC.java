package Byonic.ByonicProject.Tests.POCs;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class OTPPOC {
	
	
	public static void main(String[] args)
    {
		System.out.println(getTOTPCode("44LEQOGSUHM4XBHMUJDK3T4SSCDHJENS"));
    }
	public static String getTOTPCode(String secretKey) {
		Base32 base32 = new Base32();
		byte[] bytes = base32.decode(secretKey);
		String hexKey = Hex.encodeHexString(bytes);
		return TOTP.getOTP(hexKey);
		}
	
		
}
