/**
 * This class is used to validate all user input accepted within the game.
 *
 */
import java.util.List;


public class Validation 
{

    public static boolean isEmpty(String str) 
    {
        return str == null || str == "" || str.trim().length() == 0;
    }

    public static boolean isValidColor(ColorGrid colors, String inputColor) 
    {
        List<Peg> pegs = colors.getPegs();

        for(Peg peg : pegs) 
        {
             if(peg.getColor().equalsIgnoreCase(inputColor)) return true;
        }
        return false;
    }

}
