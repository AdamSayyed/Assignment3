import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.crypto.Cipher;

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple �substitution cipher� where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
	
	/*
	 * Class: CMSC203 
	 * Instructor:Dr.Grinberg
	 * Description: (Give a brief description for each Class)
	 * Due: 3/13/2023
	 * Platform/compiler:
	 * I pledge that I have completed the programming 
	 * assignment independently. I have not copied the code 
	 * from a student or any source. I have not given my code 
	 * to any student.
	   Print your Name here: Adam Sayyed
	*/

	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
for(int i =0; i< plainText.length();i++) {
	
	
	
	
	if(plainText.charAt(i)<LOWER_RANGE  || plainText.charAt(i)> UPPER_RANGE) {
		
		return false;
		
	}
	
}





return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ !?#$%&'()*+`-./0123456789:;<=>?";
		String input = plainText ;
		String encrypt = "";
		for(int i =0; i < input.length();i++) 
		
		{
			
			
			
			int position = alphabet.indexOf(input.charAt(i));
			int encryptposition = (key+position) % 58;
			char encrypted = alphabet.charAt(encryptposition);
			encrypt += encrypted; 
		}
		
	
		
		
		
		return encrypt;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		
		
	
		
		
		
		
		
		 HashMap<Character,Integer> ascii = new HashMap<>();

		
		 HashMap<Integer,Character> asciikeys = new HashMap<>();
		
		
		







 ascii.put(' ',32);
 ascii.put('!', 33 );
 ascii.put('"', 34);
 ascii.put( '#',35);
 asciikeys.put(32 ,' ');
 asciikeys.put(33, '!' );
 asciikeys.put(34, '"');
 asciikeys.put( 35,'#');
  
 
 
 
 
 
 String number = "$%&'()*+`-./";
 for(int i =0; i<number.length();i++) {
	 
	 
	 
	 
	 
	 
	 
	 ascii.put(number.charAt(i), 36+i);
	 asciikeys.put(36+i, number.charAt(i));
	 
	 
	 
 }
 String numbers = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[";
for(int i =0; i<numbers.length();i++) {
	 
	 
	  
	 
	 
	 ascii.put(numbers.charAt(i), 48+i);
	 asciikeys.put(48+i, numbers.charAt(i));
	 
	 
	 
 }



  





 
 		String  characters ="]^_";
 		
 		 
 		for(int i =0; i<characters.length();i++) {
 			 
 			  
 			 
 			 ascii.put(characters.charAt(i), 93+i);
 			asciikeys.put(93+i, characters.charAt(i));
 			 
 			 
 		 }
 		 
 		 
 		 
 		for (int i = 0; i<bellasoStr.length(); i++)
 		{
 			if (bellasoStr.length() == i)
 				i = 0;
 			if (plainText.length() == bellasoStr.length())
 				break;
 			bellasoStr+=(bellasoStr.charAt(i));
 		}
 		 
 		String encrypt="";
 		for(int m =0; m < plainText.length();m++) {
 			 
 			int count =0;
 			count += ascii.get(plainText.charAt(m));
 			
 			
 			
 			
 			
 			
 			
 			
 			
 		 
 			 
 			count += ascii.get(bellasoStr.charAt(m));
 		 
 			count -= 64;
 			  if(count>95) {count -=64;}
 			System.out.println( asciikeys.get(plainText.charAt(m)) );
 			encrypt+=asciikeys.get(count);
 			 
 			 
 		}
 		
 		
 		return encrypt;
	}
	
	 

	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ !?#$%&'()*+`-./0123456789:;<=>?";
		String input = encryptedText ;
		String dencrypt = "";
		for(int i =0; i < input.length();i++) 
			
		{
			
			
			
			int position = alphabet.indexOf(input.charAt(i));
			int dencryptposition = ( position - key) % 58;
			if(dencryptposition<0) {dencryptposition = alphabet.length()+dencryptposition;}
			char dencrypted = alphabet.charAt(dencryptposition);
			dencrypt += dencrypted; 
		}
		return dencrypt;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
		HashMap<Character,Integer> ascii = new HashMap<>();
		HashMap<Integer,Character> asciikeys = new HashMap<>();
		 ascii.put(' ',32);
		 ascii.put('!', 33 );
		 ascii.put('"', 34);
		 ascii.put( '#',35);
		 asciikeys.put(32 ,' ');
		 asciikeys.put(33, '!' );
		 asciikeys.put(34, '"');
		 asciikeys.put( 35,'#');
		  
		 
		 
		 
		 
		 
		 String number = "$%&'()*+`-./";
		 for(int i =0; i<number.length();i++) {
			 
			 
			 
			 
			 
			 
			 
			 ascii.put(number.charAt(i), 36+i);
			 asciikeys.put(36+i, number.charAt(i));
			 
			 
			 
		 }
		 String numbers = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[";
		for(int i =0; i<numbers.length();i++) {
			 
			 
			  
			 
			 
			 ascii.put(numbers.charAt(i), 48+i);
			 asciikeys.put(48+i, numbers.charAt(i));
			 
			 
			 
		 }



		  





		 
		 		String  characters ="]^_";
		 		
		 		 
		 		for(int i =0; i<characters.length();i++) {
		 			 
		 			  
		 			 
		 			 ascii.put(characters.charAt(i), 93+i);
		 			asciikeys.put(93+i, characters.charAt(i));
		 			 
		 			 
		 		 }
		 		 
		 		 
		 		 
		 		 
		 		for (int i = 0; i<bellasoStr.length(); i++)
		 		{
		 			if (bellasoStr.length() == i)
		 				i = 0;
		 			if (encryptedText.length() == bellasoStr.length())
		 				break;
		 			bellasoStr+=(bellasoStr.charAt(i));
		 		} 
		 		
		 		
		 		
		 		
		 		
		 		
		 		String decrypt="";
		 		
		 		for(int i =0; i< bellasoStr.length();i++) {
		 			
		 			
		 			int counte =64;
		 			
		 			
		 			counte += ascii.get(encryptedText.charAt(i));
		 			counte  -= ascii.get(bellasoStr.charAt(i));
		 			System.out.println(asciikeys.get(counte) );
		 			decrypt+= asciikeys.get(counte);
		 		}
		 		
		 		return decrypt;
		 		
	}
}
