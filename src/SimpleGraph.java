import java.util.*;

public class SimpleGraph<T> {
    private Map<T, List<T>> adjacencies;

    public SimpleGraph() {
        adjacencies = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencies.put(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination) {
        adjacencies.get(source).add(destination);
        adjacencies.get(destination).add(source);
    }

    public void removeEdge(T source, T destination) {
        adjacencies.get(source).remove(destination);
        adjacencies.get(destination).remove(source);
    }

    public boolean isAdjacent(T source, T destination) {
        return adjacencies.get(source).contains(destination);
    }

    public List<T> getVertices() {
        return new ArrayList<>(adjacencies.keySet());
    }

    public List<T> getNeighbors(T vertex) {
        return new ArrayList<>(adjacencies.get(vertex));
    }
}

