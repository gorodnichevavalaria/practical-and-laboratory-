public class Main {

    public static int countNumbersInIntervals(int[][] intervals) {
        int count = 0;
        for (int[] interval : intervals) {
            count += (interval[1] - interval[0] + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {6, 10}, {11, 15}};
        int result = countNumbersInIntervals(intervals);
        System.out.println(result);
    }
}