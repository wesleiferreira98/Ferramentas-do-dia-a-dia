# Classe que representa um nó da rede
class Node:
    def __init__(self, name):
        self.name = name
        self.neighbors = []

    def add_neighbor(self, node):
        self.neighbors.append(node)

# Classe que representa a rede
class Network:
    def __init__(self):
        self.nodes = {}

    def add_node(self, node):
        self.nodes[node.name] = node

    # Método que implementa o Algoritmo de Roteamento por Inundação
    def flood(self, source_node, destination_node):
        # Cria uma fila para armazenar os nós que ainda precisam ser visitados
        queue = [source_node]
        # Cria um conjunto para armazenar os nós que já foram visitados
        visited = set()

        # Enquanto ainda houver nós na fila
        while queue:
            # Pega o primeiro nó da fila
            current_node = queue.pop(0)
            # Adiciona o nó atual ao conjunto de visitados
            visited.add(current_node)
            # Se o nó atual for o nó de destino, termina o algoritmo
            if current_node == destination_node:
                return
            # Percorre os vizinhos do nó atual
            for neighbor in current_node.neighbors:
                # Se o vizinho ainda não foi visitado, adiciona na fila
                if neighbor not in visited:
                    queue.append(neighbor)

# Cria uma rede com alguns nós e ligações entre eles
network = Network()
A = Node('A')
B = Node('B')
C = Node('C')
D = Node('D')
E = Node('E')
F = Node('F')
A.add_neighbor(B)
A.add_neighbor(C)
B.add_neighbor(D)
C.add_neighbor(D)
C.add_neighbor(E)
D.add_neighbor(F)
E.add_neighbor(F)
network.add_node(A)
network.add_node(B)
network.add_node(C)
network.add_node(D)
network.add_node(E)
network.add_node(F)
