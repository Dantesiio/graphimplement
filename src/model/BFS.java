package model;

import java.util.LinkedList;
import java.util.Queue;

public class BFS<T> {

    private Graph<T> graph;

    public BFS(Graph<T> graph) {
        this.graph = graph;
    }

    public static <T> void bfs(Graph<T> graph, T rootVertex) {
        int rootIndex = graph.getIndex(rootVertex);
        if (rootIndex == -1) {
            System.out.println("The vertex doesn't exist");
            return;
        }

        for (Vertex<T> vertex : graph.getVertexs()) {
            vertex.setColor(Colors.BLANCO);
            vertex.setDistance(0);
            vertex.setPrevious(null);
        }

        Vertex<T> root = graph.getVertexs().get(rootIndex);
        root.setColor(Colors.GRIS);
        root.setDistance(0);
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            System.out.println(current.getValue() + " its distance: [" + current.getDistance() + "] " + "\n");
            for (int adjIndex : graph.getAdjList().get(graph.getVertexs().indexOf(current))) {
                Vertex<T> adj = graph.getVertexs().get(adjIndex);
                if (adj.getColor() == Colors.BLANCO) {
                    adj.setColor(Colors.GRIS);
                    adj.setDistance(current.getDistance() + 1);
                    adj.setPrevious(current);
                    queue.add(adj);
                }
            }
            current.setColor(Colors.NEGRO);
        }
    }
}


