package rsa_encryption;

import java.math.BigInteger;

/**
 * The {@code Key} interface provides methods for generating and retrieving keys.
 *
 */
public interface Key {

    /**
     * Generates the  key.
     */
    public void generateKey();
    /**
     * Retrieves the generated key.
     */
    public BigInteger getKey();

    /**
     * Retrieves the generator number associated with the key.
     */
    public BigInteger getGeneratorNumber();


}
