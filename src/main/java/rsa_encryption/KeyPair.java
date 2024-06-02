package rsa_encryption;

import java.math.BigInteger;

/**
 * The {@code KeyPair} class represents a pair of RSA keys, consisting of a public key and a private key.
 */
public class KeyPair {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    /**
     * Constructs a {@code KeyPair} with specified prime numbers {@code p} and {@code q}.
     *
     * @param p the first prime number.
     * @param q the second prime number.
     */
    public KeyPair(BigInteger p, BigInteger q){
        publicKey = new PublicKey(p,q);
        privateKey = new PrivateKey(p,q);
    }
    /**
     * Generates the private key.
     */
    public void generatePrivateKey() {
        privateKey.generateKey();
    }
    /**
     * Generates the public key.
     */
    public void generatePublicKey() {
        publicKey.generateKey();
    }
    /**
     * Retrieves the public key.
     *
     * @return the public key as a {@code PublicKey}.
     */
    public PublicKey getPublicKey(){
        return publicKey;
    }
    /**
     * Retrieves the private key.
     *
     * @return the private key as a {@code PrivateKey}.
     */
    public PrivateKey getPrivateKey(){
        return privateKey;
    }
}
