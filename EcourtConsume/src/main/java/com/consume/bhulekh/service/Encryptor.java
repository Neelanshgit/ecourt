package com.consume.bhulekh.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.consume.bhulekh.entity.EncryptedData;

@Service
public class Encryptor {

	private static final String HASH_ALGORITHM = "HmacSHA256";
	private static Formatter formatter;

	public EncryptedData encryptresponse(String auth_token,String dist_code ,String taluka_code,String village,String survey_no) {
		String originalString = "state_code=9|dist_code=156|consume_date=2020-02-20|lg_census_flag=C";
		//  state_code=’value’|dist_code=’value’|taluka_code=’value’|village_code=’value’|survey_no=’value’|lg_census_flag=’value’
		String WorkingString = "state_code=9|"+"dist_code="+dist_code+"|taluka_code="+taluka_code+"|village_code="+village+"|survey_no="+survey_no+"|lg_census_flag=C";
		
		//System.out.println(WorkingString);
		byte[] payload = WorkingString.getBytes();
		byte[] iv = "abcdef987654".getBytes(StandardCharsets.UTF_8); // change with your IV
		byte[] key = "604CiivsrCkI".getBytes(StandardCharsets.UTF_8);// change with your Key
		byte[] ivBytes = new byte[16];
		byte[] keyBytes = new byte[16];
		System.arraycopy(iv, 0, ivBytes, 0, iv.length);
		System.arraycopy(key, 0, keyBytes, 0, key.length);
		try {
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
			byte[] encrypted = cipher.doFinal(payload);
			//System.out.println(Base64.encodeBase64String(encrypted));
			String requestStr1 = Base64.encodeBase64String(encrypted);

			String encodeURL = URLEncoder.encode(requestStr1, "UTF-8");
			//System.out.println("requestStr--" + encodeURL);

			String secretKey = "15081947";// use your own password
			String encodedString_hash = hashMac(WorkingString, secretKey);

			//System.out.println("requestToken--" + encodedString_hash);
		//	System.out.println("accesstToken--" + auth_token);

			String result = encodeURL + "$#$" + encodedString_hash;
			return new EncryptedData(requestStr1, encodedString_hash);
		} catch (Exception e) {
			return null;
		}

	}

	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		formatter = new Formatter(sb);
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return sb.toString();
	}

	public static String hashMac(String WorkingString, String secretKey) throws SignatureException {

		try {
			Key sk = new SecretKeySpec(secretKey.getBytes(), HASH_ALGORITHM);
			Mac mac = Mac.getInstance(sk.getAlgorithm());
			mac.init(sk);
			final byte[] hmac = mac.doFinal(WorkingString.getBytes());
			return toHexString(hmac);
		} catch (NoSuchAlgorithmException e1) {
			// throw an exception or pick a different encryption method
			throw new SignatureException(" No Such Algorithm in device " + HASH_ALGORITHM);

		} catch (InvalidKeyException e) {
			throw new SignatureException("Invalid Key " + HASH_ALGORITHM);

		}
	}
	
	
	//cino encryptor
	public EncryptedData encryptresponse(String auth_token,String cino) {
		String originalString ="cino="+cino;
		byte[] payload = originalString.getBytes();
		byte[] iv = "abcdef987654".getBytes(StandardCharsets.UTF_8); // change with your IV
		byte[] key = "604CiivsrCkI".getBytes(StandardCharsets.UTF_8);// change with your Key
		byte[] ivBytes = new byte[16];
		byte[] keyBytes = new byte[16];
		System.arraycopy(iv, 0, ivBytes, 0, iv.length);
		System.arraycopy(key, 0, keyBytes, 0, key.length);
		try {
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
			byte[] encrypted = cipher.doFinal(payload);
			//System.out.println(Base64.encodeBase64String(encrypted));
			String requestStr1 = Base64.encodeBase64String(encrypted);

			String encodeURL = URLEncoder.encode(requestStr1, "UTF-8");
			//System.out.println("requestStr--" + encodeURL);

			String secretKey = "15081947";// use your own password
			String encodedString_hash = hashMac(originalString, secretKey);

			//System.out.println("requestToken--" + encodedString_hash);
		//	System.out.println("accesstToken--" + auth_token);

			String result = encodeURL + "$#$" + encodedString_hash;
			return new EncryptedData(requestStr1, encodedString_hash);
		} catch (Exception e) {
			return null;
		}

	}
	
	
	// orginal method hai ye 
	public EncryptedData encryptresponse(String auth_token) {
		String originalString = "state_code=9|dist_code=156|consume_date=2020-02-20|lg_census_flag=C";
		byte[] payload = originalString.getBytes();
		byte[] iv = "abcdef987654".getBytes(StandardCharsets.UTF_8); // change with your IV
		byte[] key = "604CiivsrCkI".getBytes(StandardCharsets.UTF_8);// change with your Key
		byte[] ivBytes = new byte[16];
		byte[] keyBytes = new byte[16];
		System.arraycopy(iv, 0, ivBytes, 0, iv.length);
		System.arraycopy(key, 0, keyBytes, 0, key.length);
		try {
			IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
			byte[] encrypted = cipher.doFinal(payload);
			//System.out.println(Base64.encodeBase64String(encrypted));
			String requestStr1 = Base64.encodeBase64String(encrypted);

			String encodeURL = URLEncoder.encode(requestStr1, "UTF-8");
			//System.out.println("requestStr--" + encodeURL);

			String secretKey = "15081947";// use your own password
			String encodedString_hash = hashMac(originalString, secretKey);

			//System.out.println("requestToken--" + encodedString_hash);
		//	System.out.println("accesstToken--" + auth_token);

			String result = encodeURL + "$#$" + encodedString_hash;
			return new EncryptedData(requestStr1, encodedString_hash);
		} catch (Exception e) {
			return null;
		}

	}
	
	
	

}
