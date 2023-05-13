import java.util.*;

public class DirectedGraph<T> {
    private Map<T, List<T>> adjacencies;

    public DirectedGraph() {
        adjacencies = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencies.put(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination) {
        adjacencies.get(source).add(destination);
    }

    public void removeEdge(T source, T destination) {
        adjacencies.get(source).remove(destination);
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

    public int getInDegree(T vertex) {
        int inDegree = 0;
        for (T v : adjacencies.keySet()) {
            if (adjacencies.get(v).contains(vertex)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    public int getOutDegree(T vertex) {
        return adjacencies.get(vertex).size();
    }
}

