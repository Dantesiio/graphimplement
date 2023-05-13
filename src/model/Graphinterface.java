package model;

public interface Graphinterface<T> {
    public void addVertex(T data);
    public void addNotDirectedEdge(T init, T end);
    public void addDirectedEdge(T init, T end);
    public int getIndex(T val);
}
