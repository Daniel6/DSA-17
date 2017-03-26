public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        int minInd = 0;
        int maxInd = 0;
        int runningSum = 0;
        int minLen = 0;

        while (maxInd < A.length) {
            while (maxInd < A.length && runningSum < desiredSum) {
                runningSum += A[maxInd];
                maxInd++;
            }

            while (minInd < maxInd && runningSum - A[minInd] >= desiredSum) {
                runningSum -= A[minInd];
                minInd++;
            }

            if (maxInd - minInd < minLen || minLen == 0) {
                minLen = maxInd - minInd;
            }

            if (minInd < maxInd) {
                runningSum -= A[minInd];
                minInd++;
            }
        }

        return minLen;
    }

}
