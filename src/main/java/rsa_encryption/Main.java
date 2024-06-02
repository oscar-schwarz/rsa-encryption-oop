package rsa_encryption;

public class Main
{
    public static void main( String[] args )
    {
        KeyGenerator generatorA = new KeyGenerator(479, 839);
        generatorA.generateKeys();

        KeyGenerator generatorB = new KeyGenerator(547, 919);
        generatorB.generateKeys();

        ChatParticipant alice = new ChatParticipant("Alice", generatorA.getKeyPair());
        ChatParticipant bob = new ChatParticipant("Bob", generatorB.getKeyPair());
        
        ChatParticipant.makePartners(alice, bob);

        CryptoFileString gedicht = new CryptoFileString();
        gedicht.setFilepath("text/Gedicht.txt");

        CryptoFileString hallowelt = new CryptoFileString();
        hallowelt.setFilepath("text/hallowelt.txt");

        CryptoFileString tragedy = new CryptoFileString();
        tragedy.setFilepath("text/tragedy.txt");

        alice.sendMessage(gedicht);
        bob.sendMessage(hallowelt);
        bob.sendMessage(tragedy);
    }
}
