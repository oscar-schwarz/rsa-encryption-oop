package rsa_encryption;

public class KeyGenerator {
    private KeyPair keys;

    public KeyGenerator(int p, int q) {
        keys = new KeyPair(p, q);
    }

    public void generateKeys() {
        keys.generatePublicKey();
        keys.generatePrivateKey();
    }

    public KeyPair getKeyPair() {
        return keys;
    }

    public String toString() {
        int g = keys.getPublicKey().getGeneratorNumber();
        int e = keys.getPublicKey().getKey();
        int d = keys.getPrivateKey().getKey();

        return ("PublicKey: {" + e + "," + g + "}; PrivateKey: {" + d + "," + g + "}");
    }
}
