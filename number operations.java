import java.util.Arrays;

class Main {
    Main() {
    }

    public static int findNext(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int length = digits.length;

        int i;
        for(i = length - 2; i >= 0 && digits[i] >= digits[i + 1]; --i) {
        }

        if (i == -1) {
            return -1;
        } else {
            int j;
            for(j = length - 1; j > i && digits[j] <= digits[i]; --j) {
            }

            char temp = digits[i];
            digits[i] = digits[j];
            digits[j] = temp;
            Arrays.sort(digits, i + 1, length);
            long nextGreaterNumber = Long.parseLong(new String(digits));
            return nextGreaterNumber > 2147483647L ? -1 : (int)nextGreaterNumber;
        }
    }

    public static void main(String[] args) {
        int[] testCases = new int[]{23, 523, 2018, 1, 532};
        int[] var2 = testCases;
        int var3 = testCases.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int testCase = var2[var4];
            System.out.println("" + testCase + " ==> " + findNext(testCase));
        }

    }
}