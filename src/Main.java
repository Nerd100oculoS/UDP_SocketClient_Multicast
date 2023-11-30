import javax.swing.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {

        try {
            // 224.0.0.0 até 239.255.255.255
            InetAddress address = InetAddress.getByName("239.0.0.1");

            InetSocketAddress group = new InetSocketAddress(address, 6666);

            // Qual placa de rede estou usando
            NetworkInterface nif = NetworkInterface.getByName("en0");

            MulticastSocket multi = new MulticastSocket(group.getPort());

            // ouvindo apenas nesse grupo e nessa placa de rede
            multi.joinGroup(group, nif);

            byte[] msg = new byte[256];
            DatagramPacket dp = new DatagramPacket(msg, msg.length);

            // Esperando recebimento
            multi.receive(dp);

            JOptionPane.showMessageDialog(null, "Recebido: " + new String(dp.getData()).trim());

        }catch (Exception e){
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}