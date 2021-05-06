/**
 * This class is used to generate random number which will be used by 
 * game class.
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RNG 
{

    private static int minimumValue = 1;
    private static int maximumValue = 8;

    public static int getRandomInt(int min, int max) 
    {
        return (int) (min + (Math.random() * (max - min + 1)));
    }

    public static List<Integer> getColorPoints(int size) 
    {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= size; i++) 
        {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }
}
