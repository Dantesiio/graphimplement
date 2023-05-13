package model;

public class DFS<T> {

    private Graph<T> graph;
    private int time;

    public DFS(Graph<T> graph) {
        this.graph = graph;
    }

    public void dfs() {
        for (Vertex<T> vertex : graph.getVertexs()) {
            vertex.setColor(Colors.BLANCO);
            vertex.setPrevious(null);
        }
        time = 0;
        for (Vertex<T> vertex : graph.getVertexs()) {
            if (vertex.getColor() == Colors.BLANCO) {
                dfsVisit(vertex);
            }
        }
    }

    private void dfsVisit(Vertex<T> vertex) {
        time += 1;
        vertex.setInitialTime(time);
        vertex.setColor(Colors.GRIS);
        for (int adjIndex : graph.getAdjList().get(graph.getVertexs().indexOf(vertex))) {
            Vertex<T> adj = graph.getVertexs().get(adjIndex);
            if (adj.getColor() == Colors.BLANCO) {
                adj.setPrevious(vertex);
                dfsVisit(adj);
            }
        }
        vertex.setColor(Colors.NEGRO);
        time += 1;
        vertex.setFinalTime(time);
    }

    public void printTimeStamps() {
        for (Vertex<T> vertex : graph.getVertexs()) {
            System.out.println(vertex.getValue() + " In time: " + vertex.getInitialTime() + "/" + vertex.getFinalTime() + "\n");
        }
    }

}

