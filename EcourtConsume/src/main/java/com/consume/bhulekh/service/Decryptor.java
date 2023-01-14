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


import org.apache.commons.lang.StringEscapeUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.consume.bhulekh.entity.EncryptedData;

@Service
public class Decryptor {

	private static final String HASH_ALGORITHM = "HmacSHA256";
	private static Formatter formatter;

	public  String decrypt(byte[] cipherText)
	{
		byte[] iv ="abcdef987654".getBytes(StandardCharsets.UTF_8);// change with your IV
		byte[] key = "604CiivsrCkI".getBytes(StandardCharsets.UTF_8);// change with your Key
                byte[] ivBytes = new byte[16];
		byte[] keyBytes = new byte[16];
		System.arraycopy(iv, 0, ivBytes, 0, iv.length);
		System.arraycopy(key, 0, keyBytes, 0, key.length);
		try {
	        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes,"AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
	   
			  byte[] encrypted =cipher.doFinal(Base64.decodeBase64(cipherText)); 
                   
			  String h= new String(encrypted,StandardCharsets.UTF_8);
			  byte[] charset = h.getBytes("UTF-8");
			  String result = new String(charset, "UTF-8");
			String dun=  StringEscapeUtils.unescapeJava(result);
			
                          return dun;
			 
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        throw new RuntimeException(ex);
	    }
	}


}
