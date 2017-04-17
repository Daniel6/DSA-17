import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class MixingMilk {
    public static int solve(int M, int N, int[] units, int[] price) {
        int milk = 0;
        int cost = 0;
        List<Farmer> farmers = Lists.newArrayList();
        for (int i = 0; i < units.length; i++) {
            farmers.add(new Farmer(price[i], units[i])); // reversed the order of these parameters
        }
        // Sort by price
        Collections.sort(farmers);

        int farmerNum = 0;
        while (milk < M && farmerNum < farmers.size()) {
            Farmer f = farmers.get(farmerNum);

            if (f.units > M - milk) {
                cost += f.price * (M - milk);
                milk += M - milk;
            } else {
                cost += f.price * f.units;
                milk += f.units;
            }

            farmerNum++;
        }
        return cost;
    }

    private static class Farmer implements Comparable<Farmer> {
        public int units;
        public int price;

        public Farmer(int units, int price) {
            this.units = units;
            this.price = price;
        }

        public int compareTo(Farmer f) {
            return this.price - f.price;
        }
    }
}
