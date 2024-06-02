package rsa_encryption;

import java.math.BigInteger;

public class KeyGenerator {
    private KeyPair keys;

    public KeyGenerator(BigInteger p, BigInteger q) {
        keys = new KeyPair(p, q);
    }

    public KeyGenerator(int p, int q) {
        keys = new KeyPair(BigInteger.valueOf(p), BigInteger.valueOf(q));
    }
    public void generateKeys() {
        keys.generatePublicKey();
        keys.generatePrivateKey();
    }

    public KeyPair getKeyPair() {
        return keys;
    }

    public String toString() {
        String g = keys.getPublicKey().getGeneratorNumber().toString();
        String e = keys.getPublicKey().getKey().toString();
        String d = keys.getPrivateKey().getKey().toString();

        return ("PublicKey: {" + e + "," + g + "}; PrivateKey: {" + d + "," + g + "}");
    }
}
