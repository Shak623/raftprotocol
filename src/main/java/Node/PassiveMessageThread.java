package Node;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import misc.*;

/**
 * Thread that handles accepting incoming messages and processing of data.
 */
public class PassiveMessageThread extends Thread {

    private RaftNode node;
    private int port;

    PassiveMessageThread(RaftNode node, int port) {
        this.node = node;
        this.port = port;
    }

    @Override
    public void run() {
        // Process incoming communications.
        while (true) {
            Message message = null;
            String senderAddress = null;
            // 1. Socket opens
            try (
                ServerSocket listener = new ServerSocket(port);
                Socket socket = listener.accept();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
            ) {
                // 2. Read message from input
                message = (Message) in.readUnshared();
                senderAddress = socket.getInetAddress().getHostAddress();

                // 3. Close socket
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            // 4. Process message
            new MessageHandlerThread(node, message, senderAddress).start();
        }
    }
}