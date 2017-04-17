import com.google.common.collect.Maps;

import java.util.HashMap;

public class Stocks {

    public int maxProfit(int[] prices) {
        int a = prices[0];
        int b;
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= a) {
                a = prices[i];
            } else {
                b = prices[i];
                if (b - a > profit) {
                    profit = b - a;
                }
            }
        }

        return profit;
    }

    public int maxProfitWithK(int[] prices, int k) {
        // TODO: Optional
        return -1;
    }

}
