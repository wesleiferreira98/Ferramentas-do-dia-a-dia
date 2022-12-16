import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Classe que representa um nó da rede
class Node {
    String name;
    List<Node> neighbors;

    public Node(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node node) {
        this.neighbors.add(node);
    }
}

// Classe que representa a rede
class Network {
    Set<Node> nodes;

    public Network() {
        this.nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    // Método que implementa o Algoritmo de Roteamento por Inundação
    public void flood(Node sourceNode, Node destinationNode) {
        // Cria uma fila para armazenar os nós que ainda precisam ser visitados
        List<Node> queue = new ArrayList<>();
        queue.add(sourceNode);
        // Cria um conjunto para armazenar os nós que já foram visitados
        Set<Node> visited = new HashSet<>();

        // Enquanto ainda houver nós na fila
        while (!queue.isEmpty()) {
            // Pega o primeiro nó da fila
            Node currentNode = queue.remove(0);
            // Adiciona o nó atual ao conjunto de visitados
            visited.add(currentNode);
            // Se o nó atual for o nó de destino, termina o algoritmo
            if (currentNode == destinationNode) {
                return;
            }
            // Percorre os vizinhos do nó atual
            for (Node neighbor : currentNode.neighbors) {
                // Se o vizinho ainda não foi visitado, adiciona na fila
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
    }
}

// Cria uma rede com alguns nós e ligações entre eles
Network network = new Network();
Node A = new Node("A");
Node B = new Node("B");
Node C = new Node("C");
Node D = new Node("D");
Node E = new Node("E");
Node F = new Node("F");
A.addNeighbor(B);
A.addNeighbor(C);
B.addNeighbor(D);
C.addNeighbor(D);
C.addNeighbor(E);
D.addNeighbor(F);
E.addNeighbor(F);
network.addNode(A);
network.addNode(B);
network.addNode(C);
network.addNode(D);
network.addNode(E);
network.addNode(F);
network.flood(A,F);
