package rsa_encryption;

public class PublicKey implements Key{

    private int  p;
    private int q;
    private int keyNumber;
    private int generatorKey;


    public PublicKey(
            int p,
            int q) {
        this.p = p;
        this.q = q;
        generatorKey = this.p * this.q;
    }
    private int ggt(int a, int b) {
        if (b==0) return a;
        return ggt(b,a%b);
    }

    public void generateKey() {
        int f = (p - 1) * (q - 1);

        int e = 2;
        while (ggt(e, f) != 1) {
            e++;
        }

        this.keyNumber = e;
    }

    public int getGeneratorNumber() {
        return generatorKey;
    }

    @Override
    public int getKey() {
        return keyNumber;
    }
}
