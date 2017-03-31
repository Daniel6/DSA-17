import com.google.common.collect.Lists;

import java.util.List;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> list = Lists.newArrayList();

        for (int i : A) {
            List<List<Integer>> sublist = helper(A, Lists.newArrayList(i));
            list.addAll(sublist);
        }

        return list;
    }

    public static List<List<Integer>> helper(List<Integer> A, List<Integer> prev) {
        List<List<Integer>> list = Lists.newArrayList();

        if (prev.size() == A.size()) {
            list.add(prev);
            return list;
        }

        for (int i : A) {
            if (!prev.contains(i)) {
                List<Integer> newPrev = Lists.newArrayList(prev);
                newPrev.add(i);

                list.addAll(helper(A, newPrev));
            }
        }

        return list;
    }

}
