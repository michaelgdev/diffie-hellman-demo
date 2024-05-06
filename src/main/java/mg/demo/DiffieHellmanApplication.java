package mg.demo;

import static java.lang.System.out;

public class DiffieHellmanApplication {

    public static void main(String[] args) {
        DiffieHellmanAESManager server = new DiffieHellmanAESManager();
        DiffieHellmanAESManager client = new DiffieHellmanAESManager();

        server.setReceiverPublicKey(client.getPublicKey());
        client.setReceiverPublicKey(server.getPublicKey());

        String data = "secret string for transferring 123";
        String encryptedString = server.encrypt(data);

        out.println("Server Public Key: " + server.getPublicKey() + "\n");
        out.println("Client Public Key: " + client.getPublicKey() + "\n");
        out.println("'" + data + "' is encrypted to " + encryptedString);
        out.println(encryptedString + " is decrypted to '" + client.decrypt(encryptedString) + "'");
    }

}
