package ps.android.mcalculator;

public class Utils {

    public static float toPercent(float value) {
        return value/10;
    }

    public static float mainsMerit(float value) {
        float percent =  (value*100)/150;
        return (percent*60)/100;
    }
}
