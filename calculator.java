public class Main {

    private static final int SECONDS_IN_MINUTE = 60;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_DAY = 86400;
    private static final int SECONDS_IN_WEEK = 604800;
    private static final int SECONDS_IN_YEAR = 31536000;

    public static String formatTime(int seconds) {
        if (seconds == 0) {
            return "0 секунд";
        }

        StringBuilder result = new StringBuilder();
        int years = seconds / SECONDS_IN_YEAR;
        seconds %= SECONDS_IN_YEAR;

        int days = seconds / SECONDS_IN_DAY;
        seconds %= SECONDS_IN_DAY;

        int hours = seconds / SECONDS_IN_HOUR;
        seconds %= SECONDS_IN_HOUR;

        int minutes = seconds / SECONDS_IN_MINUTE;
        seconds %= SECONDS_IN_MINUTE;

        if (years > 0) {
            result.append(years).append(" ").append(formatUnit("год", years)).append(", ");
        }
        if (days > 0) {
            result.append(days).append(" ").append(formatUnit("день", days)).append(", ");
        }
        if (hours > 0) {
            result.append(hours).append(" ").append(formatUnit("час", hours)).append(", ");
        }
        if (minutes > 0) {
            result.append(minutes).append(" ").append(formatUnit("минута", minutes)).append(", ");
        }
        if (seconds > 0) {
            result.append(seconds).append(" ").append(formatUnit("секунда", seconds)).append(", ");
        }

        int lastComma = result.lastIndexOf(",");
        if (lastComma != -1) {
            result.replace(lastComma, lastComma + 1, "");
        }

        int lastAnd = result.lastIndexOf(",");
        if (lastAnd != -1) {
            result.replace(lastAnd, lastAnd + 1, " и");
        }

        return result.toString().trim();
    }

    private static String formatUnit(String unit, int value) {
        if (value == 1) {
            return unit;
        } else if (unit.equals("год")) {
            return "года";
        } else if (unit.equals("день")) {
            return "дня";
        } else if (unit.equals("минута")) {
            return "минуты";
        } else if (unit.equals("секунда")) {
            return "секунды";
        } else {
            return unit + "а";
        }
    }

    public static void main(String[] args) {
        System.out.println(formatTime(62));
        System.out.println(formatTime(3662));
        System.out.println(formatTime(0));
        System.out.println(formatTime(31536000));
    }
}