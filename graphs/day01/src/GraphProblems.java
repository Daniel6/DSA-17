import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

import java.util.*;

public class GraphProblems {

    public static boolean connected(Graph g, int v, int u) {
        Queue<Integer> nodesToLookAt = Queues.newArrayDeque();
        nodesToLookAt.add(v);
        Set<Integer> seen = Sets.newHashSet();
        seen.add(v);

        while (!nodesToLookAt.isEmpty()) {
            int thisNode = nodesToLookAt.poll();
            for (int i : g.getNeighbors(thisNode)) {
                if (i == u) {
                    return true;
                }
                if (!seen.contains(i)) {
                    nodesToLookAt.add(i);
                    seen.add(i);
                }
            }
        }

        return false;
    }

    public static List<Integer> topologicalOrder(Digraph g) {
        HashMap<Integer, Integer> indegrees = Maps.newHashMap();
        for (int node : g.vertices()) {
            indegrees.putIfAbsent(node, 0);
            for (int neighbor : g.getNeighbors(node)) {
                indegrees.put(neighbor, indegrees.getOrDefault(neighbor, 0) + 1);
            }
        }

        Queue<Integer> nodesToVisit = Queues.newArrayDeque();
        for (int node : g.vertices()) {
            if (indegrees.get(node) == 0) {
                nodesToVisit.add(node);
            }
        }

        List<Integer> topOrder = Lists.newArrayList();
        while (!nodesToVisit.isEmpty()) {
            int node = nodesToVisit.poll();
            topOrder.add(node);
            for (int neighbor : g.getNeighbors(node)) {
                indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                if (indegrees.get(neighbor) == 0) {
                    nodesToVisit.add(neighbor);
                }
            }
        }

        return topOrder;
    }

    public static boolean hasCycle(UndirectedGraph g) {
        HashSet<Integer> seen = Sets.newHashSet();

        for (int node : g.vertices()) {
            if (!seen.contains(node)) {
                if (hasCycleHelper(g, node, seen, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasCycleHelper(UndirectedGraph g, int v, HashSet<Integer> seen, int parent) {
        seen.add(v);
        for (int neighbor : g.getNeighbors(v)) {
            if (!seen.contains(neighbor)) {
                if (hasCycleHelper(g, neighbor, seen, v)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

}