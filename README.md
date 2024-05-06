# Diffie-Hellman Key Exchange with AES Encryption Demo

This Java 21 application demonstrates the Diffie-Hellman key exchange algorithm combined with AES encryption for secure communication between a server and a client.

## How to Run

1. Clone or download this repository.
2. Open the project in your preferred Java IDE.
3. Run the `DiffieHellmanApplication` class as the main entry point.

## Components

### `DiffieHellmanApplication.java`

This class contains the `main` method and demonstrates how to use the `DiffieHellmanAESManager` class for secure communication between a server and a client.

### `DiffieHellmanAESManager.java`

This class implements the Diffie-Hellman key exchange algorithm and provides methods for encryption and decryption using AES.

#### Public Methods

- `setReceiverPublicKey(PublicKey publicKey)`: Sets the public key of the receiver for key exchange.
- `encrypt(String msg)`: Encrypts the given message using AES.
- `decrypt(String encryptedData)`: Decrypts the given encrypted data using AES.
- `getPublicKey()`: Returns the public key generated for key exchange.

## Usage

1. Create two instances of `DiffieHellmanAESManager`, one for the server and one for the client.
2. Exchange public keys between the server and the client using the `setReceiverPublicKey` method.
3. Encrypt data on the server side using the `encrypt` method and decrypt it on the client side using the `decrypt` method.

## Dependencies

This project does not have any external dependencies beyond the Java standard library.

## License

This project is licensed under the MIT License.
