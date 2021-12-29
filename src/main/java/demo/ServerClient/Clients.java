package demo.ServerClient;

import java.net.InetAddress;
import java.util.ArrayList;

public class Clients {
    public static ArrayList<Clients> clients = new ArrayList<>();
    private final InetAddress address;
    private final int port;

    public Clients(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
