package edu.uade.api.tpo.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encriptacion {
	
	public static String cifrarBase64(String pass) {
    	Base64.Encoder encoder = Base64.getEncoder();
        String password = encoder.encodeToString(pass.getBytes(StandardCharsets.UTF_8) );        
        return password;
	}
	public static String descifrarBase64(String pass){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(pass);
 
        String password = new String(decodedByteArray);        
        return password;
    }
}
