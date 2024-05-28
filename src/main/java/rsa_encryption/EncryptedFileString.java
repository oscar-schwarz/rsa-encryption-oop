package rsa_encryption;

/**
 * Wrapper for {@code CryptoFileString} to manage encrypted files (or just text).
 */
public class EncryptedFileString extends CryptoFileString{

    public EncryptedFileString(KeyPair keyPair) {
        super(keyPair);
    }

    /**
     * Wrapper for the {@code transform} method to decrypt the content.
     * @return
     */
    public DecryptedFileString encrypt() {
        return (DecryptedFileString)this.transform(this.getKeyPair().getPublicKey());
    }
}
