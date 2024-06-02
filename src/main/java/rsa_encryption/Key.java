package rsa_encryption;

import java.math.BigInteger;

public interface Key {

    public void generateKey();
    public BigInteger getKey();
    public BigInteger getGeneratorNumber();


}
