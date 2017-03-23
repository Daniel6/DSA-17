import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by dbishop on 3/22/17.
 */
public class UndirectedGraphNode {

    private final int name;
    private final List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int name, List<UndirectedGraphNode> neighbors) {
        this.name = name;
        this.neighbors = Lists.newArrayList();
        if (neighbors != null) {
            this.neighbors.addAll(neighbors);
        }
    }

    public int getName() {
        return name;
    }

    public List<UndirectedGraphNode> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(UndirectedGraphNode neighbor) {
        if (neighbor.equals(this)) {
            // Cannot add oneself as neighbor
            return;
        }

        if (!hasNeighbor(neighbor)) {
            neighbors.add(neighbor);
            neighbor.addNeighbor(this);
        }
    }

    public UndirectedGraphNode removeNeighbor(UndirectedGraphNode neighbor) {
        if (hasNeighbor(neighbor)) {
            neighbors.remove(neighbor);
            neighbor.removeNeighbor(this);
            return neighbor;
        } else {
            return null;
        }
    }

    public boolean hasNeighbor(UndirectedGraphNode neighbor) {
        return neighbors.contains(neighbor);
    }
}
