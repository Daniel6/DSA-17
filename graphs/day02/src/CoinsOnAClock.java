import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> solns = Lists.newArrayList();

        int pos = 0;

        if (pennies > 0) {
            char[] myCoins = new char[hoursInDay];
            myCoins[0] = 'p';
            solns.addAll(helper(pennies - 1, nickels, dimes, pos + 1, myCoins, hoursInDay));
        }

        if (nickels > 0) {
            char[] myCoins = new char[hoursInDay];
            myCoins[0] = 'n';
            solns.addAll(helper(pennies, nickels - 1, dimes, pos + 5, myCoins, hoursInDay));
        }

        if (dimes > 0) {
            char[] myCoins = new char[hoursInDay];
            myCoins[0] = 'd';
            solns.addAll(helper(pennies, nickels, dimes - 1, pos + 10, myCoins, hoursInDay));
        }

        return solns;
    }

    public static List<char[]> helper(int p, int n, int d, int rpos, char[] hist, int hours) {
        List<char[]> solns = Lists.newArrayList();
        int pos = rpos % hours;

        if (p + n + d == 0) {
            for (int i = 0; i < hist.length; i++) {
                if (hist[i] != 'p' && hist[i] != 'n' && hist[i] != 'd') {
                    return solns;
                }
            }
            solns.add(hist);
            return solns;
        }

        if (p > 0) {
            char[] myCoins = new char[hist.length];
            System.arraycopy(hist, 0, myCoins, 0, hist.length);
            myCoins[pos] = 'p';
            solns.addAll(helper(p - 1, n, d, pos + 1, myCoins, hours));
        }

        if (n > 0) {
            char[] myCoins = new char[hist.length];
            System.arraycopy(hist, 0, myCoins, 0, hist.length);
            myCoins[pos] = 'n';
            solns.addAll(helper(p, n - 1, d, pos + 5, myCoins, hours));
        }

        if (d > 0) {
            char[] myCoins = new char[hist.length];
            System.arraycopy(hist, 0, myCoins, 0, hist.length);
            myCoins[pos] = 'd';
            solns.addAll(helper(p, n, d - 1, pos + 10, myCoins, hours));
        }

        return solns;
    }
}
