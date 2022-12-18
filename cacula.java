import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SubnetCalculator extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Criar os elementos da interface gráfica
    Label ipLabel = new Label("Endereço IP:");
    TextField ipField = new TextField();
    Label subnetLabel = new Label("Máscara de subrede:");
    TextField subnetField = new TextField();
    Button calculateButton = new Button("Calcular");
    Label networkLabel = new Label("Endereço de rede:");
    Label networkField = new Label();
    Label broadcastLabel = new Label("Endereço de broadcast:");
    Label broadcastField = new Label();
    Label ipRangeLabel = new Label("Endereços IP possíveis:");
    Label ipRangeField = new Label();

    // Adicionar os elementos a um layout container
    VBox root = new VBox();
    root.setAlignment(Pos.CENTER);
    root.setSpacing(10);
    root.getChildren().addAll(ipLabel, ipField, subnetLabel, subnetField, calculateButton, networkLabel, networkField, broadcastLabel, broadcastField, ipRangeLabel, ipRangeField);

    // Definir o tamanho e a posição dos elementos
    ipField.setPrefSize(200, 20);
    subnetField.setPrefSize(200, 20);
    calculateButton.setPrefSize(200, 30);
    networkField.setPrefSize(200, 20);
    broadcastField.setPrefSize(200, 20);
    ipRangeField.setPrefSize(200, 20);

    // Adicionar um event handler para o botão "Calcular"
    calculateButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        try {
          // Capturar o endereço IP e a máscara de rede do usuário
          String ipAddress = ipField.getText();
          String subnetMask = subnetField.getText();

          // Converter o endereço IP e a máscara de rede em objetos InetAddress
          InetAddress ipAddr = InetAddress.getByName(ipAddress);
          InetAddress subnetAddr = InetAddress.getByName(subnetMask);

          // Calcular o endereço de rede e o endereço de broadcast
          byte[] ip = ipAddr.getAddress();
          byte[] subnet = subnetAddr.getAddress();
          byte[] network = new byte[4];
          byte[] broadcast = new byte[4];
          for (int i = 0; i < 4; i++) {
            network[i] = (byte)(ip[i] & subnet[i]);
            broadcast[i] = (byte)(ip[i] | ~subnet[i]);
          }

          // Exibir o endereço de rede e o endereço de broadcast
          networkField.setText(InetAddress.getByAddress(network).getHostAddress());
          broadcastField.setText(InetAddress.getByAddress(broadcast).getHostAddress());

          // Calcular o intervalo de endereços IP possíveis
         // Calcular o intervalo de endereços IP possíveis
         ByteBuffer buffer = ByteBuffer.wrap(network);
         long minIP = buffer.getInt() & 0xFFFFFFFFL;
         ByteBuffer buffer2 = ByteBuffer.wrap(broadcast);
         long maxIP = buffer2.getInt() & 0xFFFFFFFFL;
         StringBuilder ipRange = new StringBuilder();
         ArrayList<String> ipsValidos = new ArrayList<>();
         for (long i = minIP + 1; i < maxIP; i++) {
            System.out.println(InetAddress.getByName(Long.toString(i & 0xFFFFFFFFL)).getHostAddress());
            ipsValidos.add(InetAddress.getByName(Long.toString(i & 0xFFFFFFFFL)).getHostAddress()+"\n");
            ipRange.append(InetAddress.getByName(Long.toString(i & 0xFFFFFFFFL)).getHostAddress()).append("\n");
        }

          ipRangeField.setText(ipsValidos.toString());
        } catch (Exception e) {
          // Exibir uma mensagem de erro se alguma coisa der errado
          e.printStackTrace();
          networkField.setText("ERRO");
          broadcastField.setText("ERRO");
          ipRangeField.setText("ERRO");
        }
      }
    });

    // Criar a cena e exibir a janela principal
    Scene scene = new Scene(root, 250, 400);
    primaryStage.setTitle("Calculadora de Subredes");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
