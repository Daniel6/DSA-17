import javafx.scene.web.WebHistory;

import java.util.*;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int numBoomerangs = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> distances = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                int d = getSquaredDistance(points[i], points[j]);
                if (distances.containsKey(d)) {
                    distances.put(d, distances.get(d) + 1);
                } else {
                    distances.put(d, 1);
                }
            }

            for (Map.Entry<Integer, Integer> e : distances.entrySet()) {
                numBoomerangs += e.getValue() * (e.getValue() - 1);
            }
        }

        return numBoomerangs;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}
