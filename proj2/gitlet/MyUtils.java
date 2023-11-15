package gitlet;

import java.io.File;
import java.net.Inet4Address;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.logging.SimpleFormatter;

public class MyUtils {
    public static String generateTimeStamp(Date date) {
        DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);
        return df.format(date);
    }

    public static void checkArgSum(String[] args, Integer... argsum) {
        int length = args.length;
        for (Integer i : argsum) {
            if (length == i) return;
        }
        System.out.println("Wrong number of args.");
        System.exit(0);
    }
}
