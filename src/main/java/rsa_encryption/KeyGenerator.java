package rsa_encryption;

import java.math.BigInteger;

/**
 * The {@code KeyGenerator} class is responsible for generating RSA key pairs.
 */
public class KeyGenerator {
    private KeyPair keys;

    /**
     * Constructs a {@code KeyGenerator} with specified prime numbers {@code p} and {@code q}.
     *
     * @param p the first prime number.
     * @param q the second prime number.
     */
    public KeyGenerator(BigInteger p, BigInteger q) {
        if (p.isProbablePrime(100) && q.isProbablePrime(100)) {
            keys = new KeyPair(p, q);
        } else {
            throw new IllegalArgumentException("Keine Primzahl!");
        }
    }

    /**
     * Constructs a {@code KeyGenerator} with specified prime numbers {@code p} and {@code q}.
     *
     * @param p the first prime number.
     * @param q the second prime number.
     */
    public KeyGenerator(int p, int q) {
        if (BigInteger.valueOf(p).isProbablePrime(100) && BigInteger.valueOf(q).isProbablePrime(100)) {
            keys = new KeyPair(BigInteger.valueOf(p), BigInteger.valueOf(q));
        } else {
            throw new IllegalArgumentException("Keine Primzahl!");
        }

    }

    /**
     * Generates the RSA public and private keys.
     */
    public void generateKeys() {
        keys.generatePublicKey();
        keys.generatePrivateKey();
    }

    /**
     * Retrieves the generated key pair.
     *
     * @return the key pair as a {@code KeyPair}.
     */
    public KeyPair getKeyPair() {
        return keys;
    }

    /**
     * Returns a string representation of the key pair.
     *
     * @return a string representation of the public and private keys.
     */
    public String toString() {
        String g = keys.getPublicKey().getGeneratorNumber().toString();
        String e = keys.getPublicKey().getKey().toString();
        String d = keys.getPrivateKey().getKey().toString();

        return ("PublicKey: {" + e + "," + g + "}; PrivateKey: {" + d + "," + g + "}");
    }
}
