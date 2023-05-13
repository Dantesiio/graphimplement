import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectedMultiGraph<T> {

    private Map<T, List<Edge<T>>> adjacencyList;

    public DirectedMultiGraph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(T source, T destination, int weight) {
        List<Edge<T>> sourceList = adjacencyList.get(source);
        sourceList.add(new Edge<>(source, destination, weight));
    }

    public void removeVertex(T vertex) {
        adjacencyList.remove(vertex);

        for (T key : adjacencyList.keySet()) {
            List<Edge<T>> list = adjacencyList.get(key);

            list.removeIf(edge -> edge.getDestination().equals(vertex));
        }
    }

    public void removeEdge(T source, T destination) {
        List<Edge<T>> sourceList = adjacencyList.get(source);

        sourceList.removeIf(edge -> edge.getDestination().equals(destination));
    }

    public List<Edge<T>> getEdges(T vertex) {
        return adjacencyList.get(vertex);
    }

    public List<T> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    private static class Edge<T> {
        private T source;
        private T destination;
        private int weight;

        public Edge(T source, T destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public T getSource() {
            return source;
        }

        public T getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
}



