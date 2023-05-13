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
}