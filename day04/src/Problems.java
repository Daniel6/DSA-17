import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        MyLinearMap<Integer, Integer> map = new MyLinearMap();
        for (int i : arr) {
            Integer occurences = map.get(i);
            if (occurences == null) {
                occurences = 1;
            } else {
                occurences++;
            }
            map.put(i, occurences);
        }
        return map;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {
        List<Integer> l = new ArrayList<>();
        int solution_len = num.length - k;
        int removed = 0;
        for (int i : num) {
            while(!l.isEmpty() && i < l.get(l.size()-1) && removed < k) {
                removed++;
                l.remove(l.size()-1);
            }

            if (l.size() < solution_len) {
                l.add(i);
            }
        }
        return l;
    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        int sum1 = 0;
        int sum2 = 0;

        while (h1 != null) {
            sum1 *= 10;
            sum1 += h1.data;
            h1 = h1.next;
        }

        while (h2 != null) {
            sum2 *= 10;
            sum2 += h2.data;
            h2 = h2.next;
        }

        return sum1 + sum2;
    }

}
