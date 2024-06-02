package rsa_encryption;

import java.math.BigInteger;

public class PublicKey implements Key{

    private BigInteger p;
    private BigInteger q;
    private BigInteger keyNumber;
    private BigInteger generatorKey;


    public PublicKey(
            BigInteger p,
            BigInteger q) {
        this.p = p;
        this.q = q;
        generatorKey = this.p.multiply(this.q);
    }
    private BigInteger ggt(BigInteger a, BigInteger b) {
        if (b.signum() == 0) return a;
        return ggt(b,a.mod(b));
    }

    public void generateKey() {
        BigInteger f = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));

        BigInteger e = BigInteger.valueOf(2);
        while (!(ggt(e, f).equals(BigInteger.valueOf(1)))) {
            e = e.add(BigInteger.valueOf(1));
        }

        this.keyNumber = e;
    }

    public BigInteger getGeneratorNumber() {
        return generatorKey;
    }

    @Override
    public BigInteger getKey() {
        return keyNumber;
    }
}
