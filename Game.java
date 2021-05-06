/**
 * Game class is responsible for initiating the game, reading the file
 * loading the settings, interacting with other classes and writing in the file 
 * when the game ends.
 * 
 * @author Pranav Sharma
 * @version 1 Date 25/01/2019
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game 
{
    private Rounds rounds;
    boolean isAllColorGuessed = false;

    public static void main(String[] args) 
    {
        File f = new File("d://outcome.txt");
        if(f.exists())
            f.delete();
        Game game = new Game();
        game.loadGameSettings();
        game.startGame();
    }

    private void loadGameSettings() 
    {
        FileIO fileIO = new FileIO();
        String gameSettings = fileIO.readSettingFile();
        this.createRoundInstance(gameSettings);

        showWelcomeMessage();

        System.out.println("Please enter the number of rounds you would like to play :");
        int maxRound = Input.takeUserInputInteger();

        rounds.setMaxRounds(maxRound);
    }

    public void startGame() 
    {
        int maxRound = rounds.getMaxRounds();
        int maxPeg = rounds.getMaxPegsPerRound();
        System.out.println("Computer has generated a color grid consisting of " + rounds.getMaxPegsPerRound() + " colors.");
        FileIO file = new FileIO();
        String userData [] = new String[rounds.getMaxPegsPerRound()];
        List<ColorGrid> roundGuesses = new ArrayList<>();
        ColorGrid cgRound = null;
        for(int round = 1; round <= maxRound && !isAllColorGuessed; round++) 
        {
            System.out.println("Beginning round " + round);
            System.out.println("Player Score : " + rounds.getScore());
            printColors("The available colors which can be used are ", rounds.getAllColorGrid().getPegs());

            List<Peg> userPegs = new ArrayList<>();
            String guess = "";
            for(int peg = 1; peg <= maxPeg; peg++) 
            {
                boolean isValidColor = false;

                while(!isValidColor) 
                {
                    System.out.println("Please enter your guess for the color sequence " + peg);
                    guess = Input.takeUserInput();

                    if(Validation.isValidColor(rounds.getAllColorGrid(), guess)) {
                        userPegs.add(rounds.getAllColorGrid().getPegByColorName(guess));
                        isValidColor = true;
                    }
                    else 
                    {
                        System.out.println("Please enter a color from the provided list only.");
                    }
                }

            }
 
            for(int i=0; i<userPegs.size(); i++)
            {
            userData[i]=userPegs.get(i).getColor();
            }
            
            file.writeToFile(userData);
            roundGuesses.add(createColorGridInstance(maxPeg, userPegs));
            evaluateRoundResult(userPegs);

            if(round < maxRound) 
            {
                System.out.println("Still another round. Try again!");
                printEnterKeyMessage();
            }
        }

        try
        {
                file.writeToFile(rounds.getScore());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        rounds.setRoundGuesses(roundGuesses);

        if(!isAllColorGuessed) 
        {
            System.out.println("Too Bad!! your final score is : " + rounds.getScore());
            printColors("The computer colors were : " , rounds.getComputerColorGrid().getPegs());
        } 
        else 
        {
            System.out.println("Your final score is : " + rounds.getScore());
        }

        printEnterKeyMessage();
    }

    private void evaluateRoundResult(List<Peg> guesses) 
    {
        int[] results = new int[guesses.size()];

        List<Peg> computerPegs = rounds.getComputerColorGrid().getPegs();
        for(int i = 0; i < rounds.getMaxPegsPerRound(); i++) 
        {
            if( guesses.get(i).compareTo(computerPegs.get(i)) == 0 ) 
            {
                updatePlayerScore(computerPegs.get(i).getValue());
                results[i] = i + 1;
            } 
            else 
            {
                results[i] = -1;
            }
        }
        printRoundResult(results);
    }

    private void updatePlayerScore(int points) 
    {
         int score = rounds.getScore();
         score += points;

         rounds.setScore(score);
    }

    private void createRoundInstance(String settings) 
    {
        String[] gameSetting = settings.split("\n");
        int peg;
        try
        {
            peg = Integer.parseInt(gameSetting[0]);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid Peg Number! Please specify a valid Integer.");
            return;
        }

        List<String> colorList = new ArrayList<>(Arrays.asList(gameSetting));
        colorList.remove(0);

        this.rounds = Rounds.getRoundsInstance(peg, 0, colorList);
    }

    public ColorGrid createColorGridInstance(int noOfpeg, List<Peg> pegs) 
    {
        return new ColorGrid(noOfpeg, pegs);
    }

    private void printRoundResult(int[] result) 
    {
        boolean isWrongGuess = false;
        String correctGuess = "";

        for(int n : result) 
        {
            if(n > 0) correctGuess += n + ", ";
            else isWrongGuess = true;
        }

        if(isWrongGuess) 
        {
            System.out.println("Unfortunately, you only managed to guess # " + correctGuess + " colors correctly! ");
        } 
        else 
        {
            System.out.println("Hoooorey!!!, you guessed all # " + correctGuess + " colors correctly! ");
            isAllColorGuessed = true;
        }
    }

    private void printColors(String msg, List<Peg> pegs) 
    {
        System.out.print(msg);
        for(Peg peg: pegs) 
        {
            System.out.print( peg.getColor() + ", ");
        }

        System.out.println();
    }

    private void showWelcomeMessage() 
    {
        System.out.println("+=====================================================================================+");
        System.out.println(String.format("|%85s|", ""));
        System.out.print(String.format("|%53s", "Welcome to MasterMind"));
        System.out.println(String.format("%32s|", ""));
        System.out.println(String.format("|%85s|", ""));
        System.out.println("+=====================================================================================+");
        System.out.println("The game will use the colors defined in the settings file.");
        System.out.println("The maximum number of  colored pegs in each round is set to " + rounds.getMaxPegsPerRound());
        printColors("The available colors which will be used are " , rounds.getAllColorGrid().getPegs());
        printEnterKeyMessage();
    }

    private void printEnterKeyMessage() 
    {
        System.out.println("Press the enter key to continue...");
        Input.takeAnyKey();
    }
}
