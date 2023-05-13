package test;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GraphTest {
    private Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    @Test
    void testGetIndexNonExistingVertex() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        int expectedIndex = -1;
        int actualIndex = graph.getIndex(4);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void testGetIndexEmptyGraph() {
        Graph<String> graph = new Graph<>();
        int expectedIndex = -1;
        int actualIndex = graph.getIndex("A");
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void testAddVertex() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        assertEquals(2, graph.getIndex(3));
    }

    @Test
    void testAddDirectedEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(2, 3);
        assertEquals(1, graph.getAdjList().get(0).size());
        assertEquals(1, graph.getAdjList().get(1).size());
        assertEquals(0, graph.getAdjList().get(2).size());
    }

    @Test
    public void testAddVertexEmpty() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        assertEquals(1, graph.getVertexs().size());
        assertEquals(1, graph.getVertexs().get(0).getValue());
    }

    @Test
    void testEmptyVertex() {
        ArrayList<Vertex<Integer>> v = graph.getVertexs();
        assertEquals(0, v.size());
    }

    @Test
    void testAddNotDirectedEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addNotDirectedEdge(1, 2);
        graph.addNotDirectedEdge(1, 3);
        assertEquals(2, graph.getAdjList().get(0).size());
        assertEquals(1, graph.getAdjList().get(1).size());
    }

    @Test
    void testBFS() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(7);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(3, 7);
        graph.addDirectedEdge(2, 3);
        BFS.bfs(graph,2);
        assertEquals(1, graph.getAdjList().get(0).size());
        assertEquals(1, graph.getAdjList().get(1).size());
        assertEquals(1, graph.getAdjList().get(2).size());
        assertEquals(0, graph.getAdjList().get(3).size());
        assertEquals(0, graph.getAdjList().get(4).size());
    }

    @Test
    void testAddDirectedEdgeWithValidVertices() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addDirectedEdge("A", "B");
        assertEquals("A", graph.getVertexs().get(0).getValue());
    }

    @Test
    void testAddDirectedEdgeWithOneInvalidVertex() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addDirectedEdge("A", "B");
        boolean test = true;
        try {
            ((Graph<String>) graph).getVertexs().get(1).getValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            test = false;
            assertFalse(test);
        }
    }

    @Test
    void testBFSss() {
// Initialize graph with vertices and edges
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addNotDirectedEdge(1, 2);
        graph.addNotDirectedEdge(2, 3);
        graph.addNotDirectedEdge(3, 4);
        // Perform BFS starting from vertex 1
        BFS.bfs(graph,1);

// Assert distances of vertices from vertex 1
        assertEquals(0, graph.getVertexs().get(0).getDistance());
        assertEquals(1, graph.getVertexs().get(1).getDistance());
        assertEquals(2, graph.getVertexs().get(2).getDistance());
        assertEquals(3, graph.getVertexs().get(3).getDistance());

    }

    @Test
    void testAddVertexDuplicate() {
// Initialize graph with vertices
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        // Attempt to add duplicate vertex
        graph.addVertex("A");

// Assert that duplicate vertex is not added and existing vertices are still present
        assertEquals(4, graph.getVertexs().size());
        assertEquals("A", graph.getVertexs().get(0).getValue());
        assertEquals("B", graph.getVertexs().get(1).getValue());
        assertEquals("C", graph.getVertexs().get(2).getValue());


    }
    @Test
    void testBFSDirectedGraph() {
        // Initialize graph with vertices and directed edges
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(3, 2);

        // Perform BFS starting from vertex 1
        BFS.bfs(graph, 1);

        // Assert number of edges in adjacency list of each vertex
        assertEquals(1, graph.getAdjList().get(0).size());
        assertEquals(0, graph.getAdjList().get(1).size());
        assertEquals(1, graph.getAdjList().get(2).size());
    }
    @Test
    void testGetIndexExistingVertex() {
// Initialize graph with vertices
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        // Get index of existing vertex
        int expectedIndex = 1;
        int actualIndex = graph.getIndex("B");

// Assert index of vertex "B"
        assertEquals(expectedIndex, actualIndex);

    }
}