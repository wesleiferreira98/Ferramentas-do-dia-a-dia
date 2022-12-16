import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NetworkCalculator extends Application {
  // Constants for the number of bits in an IP address and a network mask
  private static final int NUM_IP_BITS = 32;
  private static final int NUM_MASK_BITS = 32;

  // Constants for the minimum and maximum values of an IP address
  private static final int MIN_IP_VALUE = 0;
  private static final int MAX_IP_VALUE = (int) Math.pow(2, NUM_IP_BITS) - 1;

  // Constants for the minimum and maximum values of a network mask
  private static final int MIN_MASK_VALUE = 0;
  private static final int MAX_MASK_VALUE = (int) Math.pow(2, NUM_MASK_BITS) - 1;

  // Text fields for the IP address and network mask
  private TextField ipField;
  private TextField maskField;

  // Labels for the results of the calculations
  private Label networkLabel;
  private Label broadcastLabel;
  private Label ipRangeLabel;
  private Label numSubnetsLabel;
  private Label subnetInfoLabel;

  @Override
  public void start(Stage primaryStage) {
    // Create a grid pane to hold the input fields and labels
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    // Create the input fields and labels
    Label ipLabel = new Label("IP address:");
    grid.add(ipLabel, 0, 0);
    ipField = new TextField();
    grid.add(ipField, 1, 0);

    Label maskLabel = new Label("Network mask:");
    grid.add(maskLabel, 0, 1);
    maskField = new TextField();
    grid.add(maskField, 1, 1);

    // Create a button to initiate the calculations
    Button calculateButton = new Button("Calculate");
    grid.add(calculateButton, 1, 2);

    // Create the labels to display the results
    networkLabel = new Label();
    grid.add(networkLabel, 0, 3);
    broadcastLabel = new Label();
    grid.add(broadcastLabel, 0, 4);
    ipRangeLabel = new Label();
    grid.add(ipRangeLabel, 0, 5);
    numSubnetsLabel = new Label();
    grid.add(numSubnetsLabel, 0, 6);
    subnetInfoLabel = new Label();
    grid.add(subnetInfoLabel, 0, 7);
    // Set up the button to initiate the calculations when clicked
    calculateButton.setOnAction(event -> calculate());

    // Set up the scene and stage
    Scene scene = new Scene(grid, 400, 400);
    primaryStage.setTitle("Network Calculator");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  /**

  Calculates and displays the network, broadcast address, range of possible IPs, number of
  possible subnetworks, and information about the subnetworks for the given IP address and
  network mask.
  */
  private void calculate() {
    // Parse the IP address and network mask from the input fields
    int[] ip = parseIp(ipField.getText());
    int[] mask = parseIp(maskField.getText());
    
    int[] network = new int[4];
    for (int i = 0; i < 4; i++) {
      network[i] = ip[i] & mask[i];
    }

    // Calculate the broadcast address
    int[] broadcast = new int[4];
    for (int i = 0; i < 4; i++) {
      broadcast[i] = network[i] | ~mask[i];
    }

    // Calculate the range of possible IPs in the network
    int[] startIp = new int[4];
    int[] endIp = new int[4];
    for (int i = 0; i < 4; i++) {
      startIp[i] = network[i];
      endIp[i] = broadcast[i];
    }
    startIp[3]++;
    endIp[3]--;

    // Calculate the number of possible subnetworks
    int numSubnets = (int) Math.pow(2, NUM_IP_BITS - maskField.getText().split("\\.")[3]);

    // Calculate the information about the subnetworks
    List<String> subnetInfo = new ArrayList<>();
    int[] subnetMask = new int[4];
    int[] subnetNetwork = new int[4];
    int[] subnetBroadcast = new int[4];
    int[] subnetStartIp = new int[4];
    int[] subnetEndIp = new int[4];
    for (int i = 0; i < numSubnets; i++) {
      // Calculate the subnet mask
      for (int j = 0; j < 4; j++) {
        subnetMask[j] = mask[j];
      }
      subnetMask[3] = mask[3] + (int) (Math.log(i + 1) / Math.log(2));

      // Calculate the subnet network address
      for (int j = 0; j < 4; j++) {
        subnetNetwork[j] = ip[j] & subnetMask[j];
      }
      }


}


