package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<T> implements Graphinterface<T>{
    private ArrayList<Vertex<T>> vertexs;
    private ArrayList<ArrayList<Integer>> adjList;
    private int time;

    public Graph() {
        vertexs = new ArrayList<>();
        adjList = new ArrayList<>();
    }

    @Override
    public void addVertex(T data) {
        Vertex<T> vertex = new Vertex<>(data);
        vertexs.add(vertex);
        adjList.add(new ArrayList<>());
    }

    @Override
    public void addNotDirectedEdge(T init, T end) {
        addDirectedEdge(init, end);
        addDirectedEdge(end, init);
    }

    @Override
    public void addDirectedEdge(T init, T end) {
        int initIndex = getIndex(init);
        int endIndex = getIndex(end);
        if (initIndex == -1 || endIndex == -1) {
            System.out.println("The vertex doesn't exist");
        } else {
            adjList.get(initIndex).add(endIndex);
        }
    }

    public void bfs(T rootVertex) {
        int rootIndex = getIndex(rootVertex);
        if (rootIndex == -1) {
            System.out.println("The vertex doesn't exist");
            return;
        }

        for (Vertex<T> vertex : vertexs) {
            vertex.setColor(Colors.BLANCO);
            vertex.setDistance(0);
            vertex.setPrevious(null);
        }

        Vertex<T> root = vertexs.get(rootIndex);
        root.setColor(Colors.GRIS);
        root.setDistance(0);
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            System.out.println(current.getValue() + " its distance: [" + current.getDistance() + "] " + "\n");
            for (int adjIndex : adjList.get(vertexs.indexOf(current))) {
                Vertex<T> adj = vertexs.get(adjIndex);
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

    public void dfs() {
        for (Vertex<T> vertex : vertexs) {
            vertex.setColor(Colors.BLANCO);
            vertex.setPrevious(null);
        }
        time = 0;
        for (Vertex<T> vertex : vertexs) {
            if (vertex.getColor() == Colors.BLANCO) {
                dfsVisit(vertex);
            }
        }
    }

    private void dfsVisit(Vertex<T> vertex) {
        time += 1;
        vertex.setInitialTime(time);
        vertex.setColor(Colors.GRIS);
        for (int adjIndex : adjList.get(vertexs.indexOf(vertex))) {
            Vertex<T> adj = vertexs.get(adjIndex);
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
        for (Vertex<T> vertex : vertexs) {
            System.out.println(vertex.getValue() + " In time: " + vertex.getInitialTime() + "/" + vertex.getFinalTime() + "\n");
        }
    }

    @Override
    public int getIndex(T val) {
        for (int i = 0; i < vertexs.size();
             i++) {
            if (vertexs.get(i).getValue().equals(val)) {
                return i;
            }
        }
        return -1;
    }
    public ArrayList<ArrayList<Integer>> getAdjList() {
        return adjList;
    }

    public void setAdjList(ArrayList<ArrayList<Vertex<T>>> adjList) {
    }

    public ArrayList<Vertex<T>> getVertexs() {
        return vertexs;
    }

    public void setVertexs(ArrayList<Vertex<T>> vertexs) {
        this.vertexs = vertexs;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
}