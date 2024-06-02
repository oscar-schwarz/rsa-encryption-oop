package rsa_encryption;

public class ChatParticipant {

    static void makePartners(ChatParticipant a, ChatParticipant b) {
        a.chatPartner = b;
        b.chatPartner = a;
    }

    private KeyPair keyPair;

    private String name;

    private ChatParticipant chatPartner;

    public ChatParticipant(String name, KeyPair keyPair) {
        this.name = name;
        this.keyPair = keyPair;
    }

    public String getName() {
        return name;
    }

    public ChatParticipant getChatPartner() {
        return chatPartner;
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublicKey();
    }

    public void sendMessage(CryptoFileString messageFileString) {
        this.consoleLog(
            "Sende Nachricht an " + this.chatPartner.getName(), 
            "\"" + messageFileString.toString() + "\""
        );
        this.consoleLog(
            "Verschluesselung mit Public Key von " + this.chatPartner.getName(),
            this.chatPartner.getPublicKey().getKey().toString()
        );

        CryptoFileString encrypted = messageFileString.transform(this.chatPartner.getPublicKey());

        this.consoleLog("Nachricht verschluesselt", "\"" + encrypted.toString() + "\"");

        this.chatPartner.receiveMessage(encrypted);
    }

    public void receiveMessage(CryptoFileString encryptedFileString) {
        this.consoleLog(
            "Erhalte verschluesselte Nachricht von " + this.chatPartner.getName(),
            "\"" + encryptedFileString.toString() + "\""
        );

        this.consoleLog(
            "Entschluesselung mit eigenem Private Key", 
            this.keyPair.getPrivateKey().getKey().toString()
        );

        CryptoFileString decrypted = encryptedFileString.transform(this.keyPair.getPrivateKey());

        this.consoleLog("Nachricht entschluesselt",  "\"" + decrypted.toString() + "\"");
    }

    private void consoleLog(String title, String body) {
        System.out.println(
            String.format(
                "[%s] %s: %s",
                this.name, title, body
            )
        );
    }
}