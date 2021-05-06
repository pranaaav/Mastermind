/**
 * Input class is responsible for reading input from keyboard.
 *
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input 
{

    public static int takeUserInputInteger() 
    {
        Scanner scanner = new Scanner(System.in);
        
        int num = 0;
        do 
        {
            String userInput = scanner.next().toUpperCase();
            Pattern p = Pattern.compile("^(0|[1-9][0-9]*)$");
            Matcher m = p.matcher(userInput);
            boolean b = m.matches();
                if(b==true)
                num = Integer.parseInt(userInput);
                else
                System.out.println("Please enter a valid positive Integer.");
            
        } while (num <= 0);

        return num;
    }

    public static String takeUserInput() 
    {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next().toUpperCase();
        System.out.println();
        return userInput;
    }

    public static void takeAnyKey() 
    {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }
}
