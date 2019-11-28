package by.dorozhko.poputka.services.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Formatter;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
//TODO REMOVE THIS CLASS, NOT USED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! now is for generation salt and hash to users from BD
public class HashingPBKDF2 {

    private static HashingPBKDF2 instance;

    private static ReentrantLock lock = new ReentrantLock();

    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    private SecureRandom random;
    private byte[] salt;
    private KeySpec spec;
    private SecretKeyFactory secretKeyFactory;
    private byte[] hash;

    private HashingPBKDF2() {
        random = new SecureRandom();
        salt = new byte[16];
        try {
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static HashingPBKDF2 getInstance() {

        if (!isCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new HashingPBKDF2();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public String generateSalt() {
        System.out.println("salt - " + Arrays.toString(salt));
        random.nextBytes(salt);
        System.out.println("salt after - " + Arrays.toString(salt));

        Formatter formatter = new Formatter();
        for (int i = 0; i < salt.length; i++) {
            formatter.format("%02X", salt[i]);
        }
        String hashSalt = formatter.toString();
        System.out.println("salt hash -> " + hashSalt);
        return hashSalt;
    }

    public String generatePwdHash(String pwd) {
        spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
        System.out.println(pwd + " -> " + Arrays.toString(hash));

        try {
            hash = secretKeyFactory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println(pwd + " -> " + Arrays.toString(hash));

        Formatter formatter = new Formatter();
        for (int i = 0; i < hash.length; i++) {
            formatter.format("%02X", hash[i]);
        }
        String hashPBKDF2 = formatter.toString();
        System.out.println(pwd + " hash -> " + hashPBKDF2);
        return hashPBKDF2;
    }

    public void setSalt(String newSalt) {
        byte[] val = new byte[newSalt.length() / 2];
        for (int i = 0; i < val.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(newSalt.substring(index, index + 2), 16);
            val[i] = (byte) j;
        }

        salt = val;
    }

}
