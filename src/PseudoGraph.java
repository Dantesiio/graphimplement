import java.util.*;

public class PseudoGraph<T, U> {
    private Set<T> nodes;
    private Map<T, Set<Edge<T, U>>> edges;

    public PseudoGraph() {
        this.nodes = new HashSet<>();
        this.edges = new HashMap<>();
    }

    public void addNode(T node) {
        nodes.add(node);
    }

    public void addEdge(T source, T dest, U label) {
        if (source.equals(dest)) {
            return; // En pseudografo no permitimos bucles
        }
        Edge<T, U> edge = new Edge<>(source, dest, label);
        edges.computeIfAbsent(source, k -> new HashSet<>()).add(edge);
        edges.computeIfAbsent(dest, k -> new HashSet<>()).add(edge);
    }

    public void removeNode(T node) {
        nodes.remove(node);
        edges.values().forEach(edgesOfNode -> edgesOfNode.removeIf(edge -> edge.getSource().equals(node) || edge.getDestination().equals(node)));
    }

    public void removeEdge(T source, T dest, U label) {
        edges.computeIfPresent(source, (k, edgesOfNode) -> {
            edgesOfNode.removeIf(edge -> edge.getSource().equals(source) && edge.getDestination().equals(dest) && Objects.equals(edge.getLabel(), label));
            return edgesOfNode;
        });
        edges.computeIfPresent(dest, (k, edgesOfNode) -> {
            edgesOfNode.removeIf(edge -> edge.getSource().equals(source) && edge.getDestination().equals(dest) && Objects.equals(edge.getLabel(), label));
            return edgesOfNode;
        });
    }

    public boolean containsEdge(T source, T dest, U label) {
        return edges.getOrDefault(source, Collections.emptySet()).stream()
                .anyMatch(edge -> edge.getDestination().equals(dest) && Objects.equals(edge.getLabel(), label));
    }
}

class Edge<T, U> {
        private T source;
        private T destination;
        private U label;

        public Edge(T source, T destination, U label) {
            this.source = source;
            this.destination = destination;
            this.label = label;
        }

        public T getSource() {
            return source;
        }

        public T getDestination() {
            return destination;
        }

        public U getLabel() {
            return label;
        }
}



