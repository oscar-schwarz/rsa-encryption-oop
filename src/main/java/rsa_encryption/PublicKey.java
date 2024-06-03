package rsa_encryption;

import java.math.BigInteger;

/**
 * The {@code PublicKey} class implements the {@code Key} interface and represents
 * an RSA public key.
 *
 * @version 1.0
 * @since 2023
 */
public class PublicKey implements Key{

    private BigInteger p;
    private BigInteger q;
    private BigInteger keyNumber;
    private BigInteger generatorKey;


    /**
     * Constructs a {@code PublicKey} with specified prime numbers {@code p} and {@code q}.
     *
     * @param p the first prime number.
     * @param q the second prime number.
     */
    public PublicKey(
            BigInteger p,
            BigInteger q) {
        if (p.isProbablePrime(100) && q.isProbablePrime(100)) {
            this.p = p;
            this.q = q;
            generatorKey = this.p.multiply(this.q);
        } else {
            throw new IllegalArgumentException("Keine Primzahl!");
        }
    }

    private BigInteger ggt(BigInteger a, BigInteger b) {
        if (b.signum() == 0) return a;
        return ggt(b,a.mod(b));
    }

    /**
     * Generates the RSA public key.
     */
    public void generateKey() {
        BigInteger f = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));

        BigInteger e = BigInteger.valueOf(2);
        while (!(ggt(e, f).equals(BigInteger.valueOf(1)))) {
            e = e.add(BigInteger.valueOf(1));
        }

        this.keyNumber = e;
    }

    /**
     * Retrieves the generator number associated with the key.
     *
     * @return the generator number as a {@code BigInteger}.
     */
    public BigInteger getGeneratorNumber() {
        return generatorKey;
    }

    /**
     * Retrieves the generated cryptographic key.
     *
     * @return the cryptographic key as a {@code BigInteger}.
     */
    public BigInteger getKey() {
        return keyNumber;
    }
}
