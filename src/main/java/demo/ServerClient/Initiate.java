package demo.ServerClient;
import demo.FX.Coordinates;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Initiate implements Runnable {

        private final InetAddress address = InetAddress.getLoopbackAddress();
        private final Coordinates coordinates;
        public boolean isClient;

    public Initiate(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

        public void run() {
            try (DatagramSocket socket = new DatagramSocket()) {
                byte[] buf;
                buf = "Hello".getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address,7859);
                socket.send(packet);
                socket.setSoTimeout(1000);
                try {
                    buf = new byte[1024];
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    if (received.equals("Welcome to the server")) {
                        isClient = true;
                        int port = socket.getLocalPort();
                        socket.close();
                        new UdpClient(port, coordinates);
                    }
                }
                catch (IOException e) {
                    new UdpServer(coordinates);
                    isClient = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public boolean isClient() {
        return isClient;
    }
}
