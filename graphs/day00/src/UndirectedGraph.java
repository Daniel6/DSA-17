import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class UndirectedGraph implements Graph {

    private List<UndirectedGraphNode> vertices = Lists.newArrayList();

    public UndirectedGraph(int n) {
        for (int i = 0; i < n; i++) {
            vertices.add(new UndirectedGraphNode(i, null));
        }
    }

    /*
        Time Complexity: O(n) due to looking up nodes by name, would be O(1) if given nodes
     */
    @Override
    public void addEdge(int v, int w) {
        getNodeWithName(v).addNeighbor(getNodeWithName(w));
    }

    /*
        Time Complexity: O(v)
     */
    @Override
    public List<Integer> vertices() {
        return vertices.stream().map(node -> node.getName()).collect(Collectors.toList());
    }

    /*
        Time Complexity: O(1)
     */
    @Override
    public int numVertices() {
    	return vertices.size();
    }

    /*
        Time Complexity: O(v^2)
     */
    @Override
    public int numEdges() {
        int numEdges = 0;
        List<UndirectedGraphNode> seen = Lists.newArrayList();

    	for (UndirectedGraphNode n : vertices) {
            if (!seen.contains(n)) {
                seen.add(n);
                for (UndirectedGraphNode neighbor : n.getNeighbors()) {
                    if (!seen.contains(neighbor)) {
                        numEdges++;
                    }
                }
            }
        }

        return numEdges;
    }

    /*
        Time Complexity: O(n)
     */
    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	return getNodeWithName(v).getNeighbors().stream().map(node -> node.getName()).collect(Collectors.toList());
    }

    /*
        Time Complexity: O(n)
     */
    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	return getNodeWithName(v).hasNeighbor(getNodeWithName(w));
    }

    public UndirectedGraphNode getNodeWithName(int name) {
        for (UndirectedGraphNode n : vertices) {
            if (n.getName() == name) {
                return n;
            }
        }
        return null;
    }

}
