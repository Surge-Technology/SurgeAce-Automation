package utils;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;  

public class Util {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private final KeySpec ks;
    private final SecretKeyFactory skf;
    private final Cipher cipher;
    byte[] arrayBytes;
    private final String myEncryptionKey;
    private final String myEncryptionScheme;
    SecretKey key;
    
    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random random = new Random();

    public Util() throws Exception {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }


    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }


    public static void main(String[] args) throws Exception
    {
        Util td= new Util();
        String target="Surgeace123@";
        String encrypted=td.encrypt(target);
        String decrypted=td.decrypt(encrypted);
        String twoDays = td.datePlus();
        
        System.out.println("String To Encrypt: "+ target);
        System.out.println("Encrypted String:" + encrypted);
        System.out.println("Decrypted String:" + decrypted);
        System.out.println(twoDays);
        
        
    }

    public String dateTimeSDF() throws Exception{
    	SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HHmmss");  
    	Date date = new Date();  
    	return formatter.format(date);  
    }
    
    public String dateTimeDTF() throws Exception{
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	return dtf.format(now); 
    }
    
    public String dateSDF() throws Exception{
    	SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");  
    	Date date = new Date();  
    	return formatter.format(date);  
    }
   
    public String datePlus() throws Exception{
    	String formattedDate="";
    	try {
	    	Thread.sleep(500);
	    	LocalDate today = LocalDate.now();
	        LocalDate futureDate = today.plusDays(3);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	        formattedDate = futureDate.format(formatter);
	        System.out.println("Date after 2 or two days: " + formattedDate);
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
        return formattedDate;
    }   
    public String generateRandomString(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
   
 }