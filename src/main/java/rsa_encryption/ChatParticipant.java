package rsa_encryption;

/**
 * A participant in a chat that can receive and send messages to a chat partner.
 */
public class ChatParticipant {

    /**
     * Static method to cleanly pair two partners
     * @param a partner A
     * @param b partner B
     */
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

    /**
     * Getter for the name of the participant.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the chat partner
     * @return chat partner
     */
    public ChatParticipant getChatPartner() {
        return chatPartner;
    }

    /**
     * Getter for the public key (accessible publicly)
     * @return
     */
    public PublicKey getPublicKey() {
        return this.keyPair.getPublicKey();
    }

    /**
     * Send an encrypted message (encrypted with public key of partner) to the chat partner
     * @param messageFileString contains a plain message
     */
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

    /**
     * Receive an encrypted message. (decrypted using own private key)
     * @param encryptedFileString contains encrypted message 
     */
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

    /**
     * Helper method to beautifully log a message to the console so that it is clear from which chat participant it came from.
     * In the format: [{@code name}] {@code title}: {@code body}
     * @param title 
     * @param body
     */
    private void consoleLog(String title, String body) {
        System.out.println(
            String.format(
                "[%s] %s: %s",
                this.name, title, body
            )
        );
    }
}