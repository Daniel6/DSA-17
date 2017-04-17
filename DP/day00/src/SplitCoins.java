import java.util.stream.IntStream;

public class SplitCoins {

    private static int[][] bestSolutions;
    private static int target;
    private static int[] coinsGiven;
    private static int total;

    public static int splitCoins(int[] coins) {
        coinsGiven = coins;

        // Yay Java 8
        total = IntStream.of(coins).sum();
        target = total/2;
        bestSolutions = new int[total][coins.length];
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < coins.length; j++) {
                bestSolutions[i][j] = -1;
            }
        }
        int soln = helper(0, 0);
        return soln;
    }

    private static int helper(int sum, int ncoins) {
        if (sum >= target || ncoins == coinsGiven.length - 1) {
            if (bestSolutions[sum][ncoins] == -1) {
                bestSolutions[sum][ncoins] = Math.abs(sum - (total - sum));
            }
        } else {
            int coin = coinsGiven[ncoins];

            if (bestSolutions[sum][ncoins] == -1) {
                int bestSolnUsingCoin = helper(sum + coin, ncoins + 1);
                int bestSolnWithoutCoin = helper(sum, ncoins + 1);
                int bestSoln = Math.min(bestSolnUsingCoin, bestSolnWithoutCoin);
                bestSolutions[sum][ncoins] = bestSoln;
            }
        }

        return bestSolutions[sum][ncoins];
    }
}
