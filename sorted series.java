import java.util.ArrayList;
import java.util.List;

class Main {

    public static String formatRanges(int[] nums) {
        if (nums.length == 0) {
            return "";
        }

        List<String> ranges = new ArrayList<>();
        int start = nums[0];
        int end = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                end = nums[i];
            } else {
                addRange(ranges, start, end);
                start = nums[i];
                end = nums[i];
            }
        }
        addRange(ranges, start, end);

        return String.join(",", ranges);
    }

    private static void addRange(List<String> ranges, int start, int end) {
        if (start == end) {
            ranges.add(String.valueOf(start));
        } else {
            ranges.add(start + "-" + end);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20};
        String result = formatRanges(nums);
        System.out.println(result);
    }
}