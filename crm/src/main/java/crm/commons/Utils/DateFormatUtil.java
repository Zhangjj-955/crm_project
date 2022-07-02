package crm.commons.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    private static SimpleDateFormat format = new SimpleDateFormat();

    public static String Date(Date date){
        format.applyPattern("yyyy-MM-dd");
        String result = format.format(date);
        return result;
    }
    public static String Time(Date date){
        format.applyPattern("HH:mm:ss");
        String result = format.format(date);
        return result;
    }
    public static String DateTime(Date date){
        format.applyPattern("yyyy-MM-dd HH:mm:ss");
        String result = format.format(date);
        return result;
    }
}
