import com.google.common.collect.Maps;

import java.util.HashMap;

public class BillsNeeded {

    // Map of current sum of bills -> lowest # of bills needed to make N
    private HashMap<Integer, Integer> bestSolutions;
    private int target;
    private int[] billDenominations;

    public int billsNeeded(int N, int[] billDenominations) {
        bestSolutions = Maps.newHashMap();
        target = N;
        this.billDenominations = billDenominations;

        helper(0, 0);

        return bestSolutions.get(N);
    }

    private void helper(int sum, int numBills) {
        if (sum == target) {
            // Successful endcase
            int currBestSolution = bestSolutions.getOrDefault(sum, Integer.MAX_VALUE);
            if (numBills < currBestSolution) {
                bestSolutions.put(sum, numBills);
            }
        } else if (sum > target) {
            // Overshot failure case
        } else {
            // Have not reached target, pick another bill
            int currBestSolution_ThisNode = bestSolutions.getOrDefault(sum, Integer.MAX_VALUE);
            if (numBills < currBestSolution_ThisNode) {
                bestSolutions.put(sum, numBills);
                // We have reached this state in a more optimal manner than previously possible
                for (int billVal : billDenominations) {
                    helper(sum + billVal, numBills + 1);
                }
            }
        }
    }
}
