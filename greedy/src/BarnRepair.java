import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BarnRepair {
    public static int solve(int M, int S, int C, int[] occupied) {
        // S and C are not needed

        List<Integer> covered = Lists.newArrayList(Ints.asList(occupied));
        Collections.sort(covered);

        int boards = 1;
        int prev = covered.get(0);
        for (int i = 1; i < covered.size(); i++) {
            if (covered.get(i) != prev + 1) {
                boards++;
            }
            prev = covered.get(i);
        }

        while (boards > M) {
            // Find smallest gap
            int gap_start = -1;
            int min_gap = Integer.MAX_VALUE;
            prev = covered.get(0);
            for (int i = 1; i < covered.size(); i++) {
                if (covered.get(i) != prev + 1) {
                    int gap = covered.get(i) - prev - 1;
                    if (gap < min_gap) {
                        min_gap = gap;
                        gap_start = prev;
                    }
                }
                prev = covered.get(i);
            }

            // Fill in the gap
            if (gap_start >= 0) {
                for (int i = 0; i < min_gap; i++) {
                    covered.add(gap_start + i + 1);
                }
                boards--;
            }

            Collections.sort(covered);
        }

        return covered.size();
    }
}
