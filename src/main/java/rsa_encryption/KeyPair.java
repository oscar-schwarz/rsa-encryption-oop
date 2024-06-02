package rsa_encryption;

import java.math.BigInteger;

public class KeyPair {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public KeyPair(BigInteger p, BigInteger q){
        publicKey = new PublicKey(p,q);
        privateKey = new PrivateKey(p,q);
    }

    public void generatePrivateKey() {
        privateKey.generateKey();
    }

    public void generatePublicKey() {
        publicKey.generateKey();
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    public PrivateKey getPrivateKey(){
        return privateKey;
    }
}
