package demo.ServerClient;

import demo.FX.Coordinates;
import java.io.IOException;
import java.net.*;

public class UdpClient {
    private byte[] buffer = new byte[1024];
    private final DatagramSocket socket;
    private final Coordinates coordinates;

    public UdpClient(int port, Coordinates coordinates) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.coordinates = coordinates;
        run();
    }

    public void run() {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String[] messageReceived = new String(packet.getData(), 0, packet.getLength()).split(",");
                coordinates.addCoordinates(Double.parseDouble(messageReceived[0]), Double.parseDouble(messageReceived[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    public void close() {
        socket.close();
    }
}
