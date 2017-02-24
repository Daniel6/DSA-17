
public class PeakFinding {

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peak(int i, int[] nums) {
        if (i>0 && nums[i] < nums[i-1])
            return -1;
        if (i<nums.length-1 && nums[i] < nums[i+1])
            return 1;
        return 0;
    }

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x>0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x<nums[0].length-1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 is up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y>0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y<nums.length-1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // Return the index of the highest value along the X axis, with the given
    // value for the Y axis
    private static int maxX(int y, int[][] nums) {
        int maxIndex = -1;
        for (int x = 0; x < nums[0].length; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    // Return the index of the highest value along the Y axis, with the given
    // value for the X axis
    private static int maxY(int x, int[][] nums) {
        int maxIndex = -1;
        for (int y = 0; y < nums.length; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    public static int findOneDPeak(int[] nums){
        int dist = nums.length/2;
    	int index = nums.length/2;
    	int dir = peak(index, nums);
    	while (dir != 0) {
    	    dist = Math.max(dist / 2, 1);
            index += dir * dist;
            dir = peak(index, nums);
        }

        return index;
    }

    public static int[] findTwoDPeak(int[][] nums){
    	int dist = nums.length/2;
    	int x = nums.length/2;
    	int y = nums[0].length/2;

    	int dirX = peakX(x, y, nums);
    	int dirY = peakY(x, y, nums);

    	while(dirX != 0 || dirY != 0) {
    	    dist = Math.max(dist / 2, 1);
    	    x += dirX * dist;
    	    y += dirY * dist;
    	    dirX = peakX(x, y, nums);
            dirY = peakY(x, y, nums);
        }
//        System.out.println("dirX: " + dirX);
//        System.out.println("dirY: " + dirY);
//        System.out.println("x: " + x);
//        System.out.println("y: " + y);
        int[] answer = {y,x}; // r u kidding me
        return answer;
    }

    public static void main(String[] args) {
        PeakFinding p = new PeakFinding();
        int[] a = {1, 1, 1, 1, 2, 3, 1};
        System.out.println(p.findOneDPeak(a));
    }

}
