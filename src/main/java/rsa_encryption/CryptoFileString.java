package rsa_encryption;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

public class CryptoFileString {

    /**
     * An absolute path to the file containing the text
     */
    private String filepath;

    /**
     * An instance of the key pair to be used to encrypt/decrypt
     */
    private KeyPair keyPair;

    public CryptoFileString(String filepath, KeyPair keyPair) {
        this.filepath = filepath;
        this.keyPair = keyPair;
    }

    /**
     * A second constructor to initialize the file with a certain content String.
     * 
     * @param filepath Path to the new file.
     * @param keyPair The keypair to be used
     * @param content The content of the new file.
     * @throws FileAlreadyExistsException 
     */
    public CryptoFileString(String filepath, KeyPair keyPair, String content) throws FileAlreadyExistsException {
        this.filepath = filepath;
        this.keyPair = keyPair;

        File file = new File(this.filepath);

        // Create new file if it doesnt exist yet
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileAlreadyExistsException("Die angegebene Datei '" + filepath + "' existiert bereits.");
        }

        // Try writing content to the file
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setter for the key pair instance.
     * @param keyPair
     */
    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    /**
     * Getter for the key pair instance
     * @return the key pair
     */
    public KeyPair getKeyPair() {
        return keyPair;
    }

    /**
     * Gets the content of the file as a String.
     * @return content of the file
     */
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        try {
            File file = new File(filepath);
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                // Add a newline character to preserve lines
                content.append(line + "\n");
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Die angegebene Datei '" + filepath + "' existiert nicht.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
