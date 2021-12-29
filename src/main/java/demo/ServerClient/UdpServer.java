package demo.ServerClient;

import demo.FX.Coordinates;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;

public class UdpServer implements Runnable {

    private byte[] receiveData = new byte[1024];
    private final DatagramSocket socket = new DatagramSocket(12634);
    private final Coordinates coordinates;

    public UdpServer(Coordinates coordinates) throws SocketException {
        this.coordinates = coordinates;
        Thread t1 = new Thread(this);
        t1.start();
        checkIncomingClient();
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Map.Entry<Double, Double> entry : coordinates.getCurrent().entrySet()) {
                    String message = entry.getKey() + "," + entry.getValue();
                    for (Clients client : Clients.clients) {
                        socket.send(new DatagramPacket(message.getBytes(), message.length(), client.getAddress(), client.getPort()));
                    }
                }
            }
        } catch (IllegalAccessError | IOException e) {
            e.printStackTrace();
            closeSocket();
        }
    }

    public void checkIncomingClient() {
        try (DatagramSocket socket = new DatagramSocket(7859)) {
            while (true) {
                DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(packet);
                if (new String(packet.getData(), 0, packet.getLength()).equals("Hello")) {
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    byte[] sendData = "Welcome to the server".getBytes();
                    socket.send(new DatagramPacket(sendData, sendData.length, address, port));
                    Clients.clients.add(new Clients(address, port));
                }
                if (new String(packet.getData(), 0, packet.getLength()).equals("exit")) {
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    Clients.clients.remove(new Clients(address, port));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            closeSocket();
        }
    }

    public void closeSocket() {
        socket.close();
    }
}
