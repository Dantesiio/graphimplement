package test;

import model.Graphinterface;
import model.Graph;
import static org.junit.jupiter.api.Assertions.*;

import model.Vertex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;


public class GraphTest {
    private Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }
    @Test
    void testGetIndexExistingVertex() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        int expectedIndex = 1;
        int actualIndex = graph.getIndex("B");
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void testGetIndexNonExistingVertex() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        int expectedIndex = -1;
        int actualIndex = graph.getIndex("D");
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
    public void testAddVertexEmpty() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        assertEquals(1, ((Graph<Integer>) graph).getVertexs().size());
        assertEquals(1, ((Graph<Integer>) graph).getVertexs().get(0).getValue());
    }

    @Test
    public void testAddVertexDuplicate() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("A");
        assertEquals(4, ((Graph<String>) graph).getVertexs().size());
        assertEquals("A", ((Graph<String>) graph).getVertexs().get(0).getValue());
        assertEquals("B", ((Graph<String>) graph).getVertexs().get(1).getValue());
        assertEquals("C", ((Graph<String>) graph).getVertexs().get(2).getValue());
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
    void testAddDirectedEdgeWithValidVertices() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addDirectedEdge("A", "B");
        assertEquals("A", ((Graph<String>) graph).getVertexs().get(0).getValue());
    }

    @Test
    void testAddDirectedEdgeWithOneInvalidVertex() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addDirectedEdge("A", "B");
        boolean test = true;
        try {
            ((Graph<String>) graph).getVertexs().get(1).getValue();
        }catch (Exception ex){
            ex.printStackTrace();
            test = false;
            assertFalse(test);
        }
    }
    @Test
    void testBFS() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addNotDirectedEdge(1, 2);
        graph.addNotDirectedEdge(2, 3);
        graph.addNotDirectedEdge(3, 4);
        graph.bfs(1);
        assertEquals(0, graph.getVertexs().get(0).getDistance());
        assertEquals(1, graph.getVertexs().get(1).getDistance());
        assertEquals(2, graph.getVertexs().get(2).getDistance());
        assertEquals(3, graph.getVertexs().get(3).getDistance());
    }
    @Test
    void testBFS2() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(3, 2);
        graph.bfs(1);
        assertEquals(1, graph.getAdjList().get(0).size());
        assertEquals(0, graph.getAdjList().get(1).size());
        assertEquals(1, graph.getAdjList().get(2).size());
    }

    @Test
    void testDFS() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addNotDirectedEdge(1, 2);
        graph.addNotDirectedEdge(2, 3);
        graph.addNotDirectedEdge(3, 4);
        graph.dfs();
        graph.printTimeStamps();
        assertEquals(8, graph.getVertexs().get(0).getFinalTime());
        assertEquals(7, graph.getVertexs().get(1).getFinalTime());
        assertEquals(6, graph.getVertexs().get(2).getFinalTime());
        assertEquals(5, graph.getVertexs().get(3).getFinalTime());
    }
    @Test
    void testDFS2() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(7);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(3, 7);
        graph.addDirectedEdge(2,3);
        graph.bfs(2);
        assertEquals(1, graph.getAdjList().get(0).size());
        assertEquals(1, graph.getAdjList().get(1).size());
        assertEquals(1, graph.getAdjList().get(2).size());
        assertEquals(0, graph.getAdjList().get(3).size());
        assertEquals(0, graph.getAdjList().get(4).size());
    }
}