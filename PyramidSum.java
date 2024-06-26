public class PyramidSum {

    public static int maximumSlidingSum(int[][] pyramid) {
        for (int row = pyramid.length - 2; row >= 0; row--) {
            for (int col = 0; col < pyramid[row].length; col++) {
                pyramid[row][col] += Math.max(pyramid[row + 1][col], pyramid[row + 1][col + 1]);
            }
        }
        return pyramid[0][0];
    }

    public static void main(String[] args) {
        int[][] pyramid = {
                {3},
                {7, 4},
                {2, 4, 6},
                {8, 5, 9, 3}
        };

        int result = maximumSlidingSum(pyramid);
        System.out.println(result);
    }
}