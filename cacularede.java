import java.net.InetAddress;

public class SubnetCalculator {
  public static void main(String[] args) throws Exception {
    // Ler o endereço IP e a máscara de rede do usuário
    String ipAddress = "192.168.1.100";
    String subnetMask = "255.255.255.0";

    // Converter o endereço IP e a máscara de rede em objetos InetAddress
    InetAddress ipAddr = InetAddress.getByName(ipAddress);
    InetAddress subnetAddr = InetAddress.getByName(subnetMask);

    // Converter o endereço IP e a máscara de rede em arrays de bytes
    byte[] ip = ipAddr.getAddress();
    byte[] subnet = subnetAddr.getAddress();

    // Calcular o endereço de rede
    byte[] network = new byte[4];
    for (int i = 0; i < 4; i++) {
      network[i] = (byte)(ip[i] & subnet[i]);
    }

    // Calcular o endereço de broadcast
    byte[] broadcast = new byte[4];
    for (int i = 0; i < 4; i++) {
      broadcast[i] = (byte)(network[i] | ~subnet[i]);
    }

    // Gerar uma lista de todos os endereços IP possíveis na subrede
    byte[] firstIP = new byte[4];
    byte[] lastIP = new byte[4];
    for (int i = 0; i < 4; i++) {
      firstIP[i] = (byte)(network[i] & subnet[i]);
      lastIP[i] = (byte)(broadcast[i] & subnet[i]);
    }
    firstIP[3]++;
    lastIP[3]--;

    // Exibir o resultado para o usuário
    System.out.println("Endereço de rede: " + InetAddress.getByAddress(network).toString().substring(1));
    System.out.println("Endereço de broadcast: " + InetAddress.getByAddress(broadcast).toString().substring(1));
    System.out.println("Endereços IP possíveis: " + InetAddress.getByAddress(firstIP).toString().substring(1) + " até " + InetAddress.getByAddress(lastIP).toString().substring(1));
  }
}
