package mg.demo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class DiffieHellmanAESManager {

    // For 'AES/CBC/PKCS5Padding', a need to generate a secure IV for both the client and server sides
    private static final String CIPHER_ALGO = "AES";
    private PublicKey publicKey;
    private byte[] sharedSecret;
    private KeyAgreement keyAgreement;

    DiffieHellmanAESManager() {
        makeKeyExchangeParams();
    }

    private void makeKeyExchangeParams() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
            kpg.initialize(256, new SecureRandom());
            KeyPair kp = kpg.generateKeyPair();
            publicKey = kp.getPublic();
            keyAgreement = KeyAgreement.getInstance("ECDH");
            keyAgreement.init(kp.getPrivate(), new SecureRandom());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public void setReceiverPublicKey(PublicKey publicKey) {
        try {
            keyAgreement.doPhase(publicKey, true);
            sharedSecret = keyAgreement.generateSecret();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String msg) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(CIPHER_ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(msg.getBytes());
            return new String(Base64.getEncoder().encode(encVal));
        } catch (BadPaddingException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
                 | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String decrypt(String encryptedData) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(CIPHER_ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decodedValue);
            return new String(decValue);
        } catch (BadPaddingException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException
                 | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    private Key generateKey() {
        return new SecretKeySpec(sharedSecret, CIPHER_ALGO);
    }
}