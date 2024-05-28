package rsa_encryption;

/**
 * Wrapper for {@code CryptoFileString} to manage decrypted files (or just text).
 */
public class DecryptedFileString extends CryptoFileString {
    
    public DecryptedFileString(KeyPair keyPair) {
        super(keyPair);
    }

    /**
     * Wrapper for the {@code transform} method to encrypt the content.
     * @return
     */
    public EncryptedFileString encrypt() {
        return (EncryptedFileString)this.transform(this.getKeyPair().getPublicKey());
    }
}
