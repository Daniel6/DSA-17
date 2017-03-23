import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;

import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        Map<Integer, Integer> dists = createDistanceMap(g, w, v);
        List<Integer> path = Lists.newArrayList(v);

        int currVertex = v;
        while (dists.get(currVertex) > 0) {
            int closestNeighbor = currVertex;
            int minDist = Integer.MAX_VALUE;
            for (Integer i : g.getNeighbors(currVertex)) {
                if (dists.containsKey(i) && dists.get(i) < minDist) {
                    minDist = dists.get(i);
                    closestNeighbor = i;
                }
            }

            path.add(closestNeighbor);
            currVertex = closestNeighbor;
        }

        return path;
    }

    /*
        Create mapping of vertices to their distances from vertex v
     */
    private static Map<Integer, Integer> createDistanceMap(Graph g, int v, int w) {
        return createDistanceMap(g, v, w, 10000);
    }

    private static Map<Integer, Integer> createDistanceMap(Graph g, int v, int w, int max_allowable_dist) {
        Map<Integer, Integer> dists = Maps.newHashMap();
        dists.put(v, 0);

        Set<Integer> seen = Sets.newHashSet();

        Queue<Integer> queue1 = Queues.newArrayDeque();
        Queue<Integer> queue2 = Queues.newArrayDeque();
        queue1.add(v);

        int dist = 1;

        Queue<Integer> pollQueue = queue1;
        Queue<Integer> fillQueue = queue2;
        while (!dists.containsKey(w) && dist < max_allowable_dist) {
            while (pollQueue.size() > 0) {
                int currVertex = pollQueue.poll();
                seen.add(currVertex);
                for (Integer i : g.getNeighbors(currVertex)) {
                    if (!dists.containsKey(i)) {
                        dists.put(i, dist);
                    }
                    if (!seen.contains(i)) {
                        fillQueue.add(i);
                    }
                }
            }
            dist++;

            // Swap pointers to queues
            Queue<Integer> temp = pollQueue;
            pollQueue = fillQueue;
            fillQueue = temp;
        }

        return dists;
    }

    public static int distanceBetween(Graph g, int v, int w) {
        Map<Integer, Integer> dists = createDistanceMap(g, w, v);
        if (dists.containsKey(v)) {
            return dists.get(v);
        } else {
            return -1;
        }
    }

}