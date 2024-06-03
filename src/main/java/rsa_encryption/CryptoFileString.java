package rsa_encryption;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class to manage decrypted/encrypted files (or just text).
 */
public class CryptoFileString {

    /**
     * An absolute path to the file containing the text
     */
    private String filepath = "";

    /**
     * The content of the file
     */
    private String content = "";

    public CryptoFileString() {}

    /**
     * Getter for the filepath
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Setter for the filepath
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Gets the content of the file as a String. If no filepath was set just return the {@code content} attribute
     * @return content of the file
     */
    public String getContent() {
        // If no file was set just return content
        if (this.filepath.length() == 0) {
            return this.content;
        }

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
        } catch (FileNotFoundException e) { // Just returned cached content if the file doesn't exist
            return this.content;
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.content = content.toString();

        return this.content;
    }

    /**
     * Update the files content. If no filepath was set just update the {@code content} attribute.
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;

        // Do not try to save the file if no filepath was specified
        if (this.filepath.length() == 0) {
            return;
        }

        File file = new File(this.filepath);

        // Create new file if it doesnt exist yet
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Try writing content to the file
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(this.content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transform the content using a certain key with the RSA algorithm.
     * 
     * @param key public or private key
     * @return new file instance with transformed content
     */
    public CryptoFileString transform(Key key) {
        String content = this.getContent();
        String transformContent = "";

        BigInteger e = key.getKey();
        BigInteger g = key.getGeneratorNumber();
        int gLength = g.toString().length();
        // encrypt
        if (key instanceof PublicKey) {
            for(int i=0;i<content.length();i++) {
                char ch = content.charAt(i);
                int code = (int)ch;
                BigInteger k = BigInteger.valueOf(code);
                transformContent += String.format("%0" + gLength + "d", k.modPow(e, g).intValue());
            }
        }
        // decrypt
        else {
            for (int i=0;i<content.length();i += gLength) {
                BigInteger v = BigInteger.valueOf(Integer.parseInt(content.substring(i,i+gLength)));
                transformContent += (char)v.modPow(e, g).intValue();
            }
        }


        CryptoFileString transformFile = new CryptoFileString();
        transformFile.setContent(transformContent);

        return transformFile;
    }

    /**
     * Alias for {@code getContent} for comfort.
     */
    @Override
    public String toString() {
        return this.getContent();
    }
}
