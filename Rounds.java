/**
 * Responsible for creating an array list which stores each peg for 
 * both player and the computer.
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Rounds 
{
    private int maxPegsPerRound;
    private int maxRounds;
    private int score;
    private ColorGrid allColorGrid;
    private ColorGrid computerColorGrid;
    private List<ColorGrid> roundGuesses;

    private Rounds() /* Default constructor*/
    {
    }

    public Rounds(int maxPegsPerRound, int maxRounds, ColorGrid allColorGrid) /* Parameterised constructor*/ 
    {
        this.maxPegsPerRound = maxPegsPerRound;
        this.maxRounds = maxRounds;
        this.allColorGrid = allColorGrid;
        this.roundGuesses = new ArrayList<>();
        this.score = 0;
    }

    public static Rounds getRoundsInstance(int maxPegsPerRound, int maxRounds, List<String> colorList) /* Accessor*/
    {
        ColorGrid colorGrid = ColorGrid.getColorGridInstance(maxPegsPerRound, colorList);

        Rounds rounds = new Rounds(maxPegsPerRound, maxRounds, colorGrid);

        rounds.setComputerColorGrid(maxPegsPerRound, colorList.size());
        return rounds;
    }

    public void setComputerColorGrid(int maxPegsPerRound, int gridSize) /* Mutator*/
    {
        List<Peg> pegs = new ArrayList<>();
        for(int i = 0; i < maxPegsPerRound; i++) 
        {
            pegs.add(allColorGrid.getPegs().get(RNG.getRandomInt(0, gridSize-1)));
        }

        computerColorGrid = new ColorGrid(maxPegsPerRound, pegs);
    }

    public int getMaxPegsPerRound() 
    {
        return maxPegsPerRound;
    }

    public void setMaxPegsPerRound(int maxPegsPerRound) 
    {
        this.maxPegsPerRound = maxPegsPerRound;
    }

    public int getMaxRounds() 
    {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) 
    {
        this.maxRounds = maxRounds;
    }

    public int getScore() 
    {
        return score;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }

    public ColorGrid getAllColorGrid() 
    {
        return allColorGrid;
    }

    public void setAllColorGrid(ColorGrid allColorGrid) 
    {
        this.allColorGrid = allColorGrid;
    }

    public ColorGrid getComputerColorGrid() 
    {
        return computerColorGrid;
    }

    public void setComputerColorGrid(ColorGrid computerColorGrid) 
    {
        this.computerColorGrid = computerColorGrid;
    }

    public List<ColorGrid> getRoundGuesses() 
    {
        return roundGuesses;
    }

    public void setRoundGuesses(List<ColorGrid> roundGuesses) 
    {
        this.roundGuesses = roundGuesses;
    }

    public void addRoundGuesses() 
    {

    }


}
