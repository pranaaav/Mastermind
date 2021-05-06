/**
 * This class is used to store a new sequence of coloured pegs which
 * is added to the plain board.
 *
 */
import java.util.ArrayList;
import java.util.List;

public class ColorGrid  
{
    private List<Peg> pegs;
    private int noOfPegs;

    private ColorGrid() /* Default constructor*/
    {
    }

    private ColorGrid(int noOfPegs) /* Parameterised constructor*/
    {
        this.noOfPegs = noOfPegs;
    }

    public ColorGrid(int noOfPegs, List<Peg> pegs)
    {
        this.noOfPegs = noOfPegs;
        this.pegs = pegs;
    }

    public static ColorGrid getColorGridInstance(int noOfPegs, List<String> colorList) 
    {
        ColorGrid cg = new ColorGrid(noOfPegs);
        cg.pegs = new ArrayList<>();
        List<Integer> pegPoints = RNG.getColorPoints(colorList.size());

        for(int i = 0; i < colorList.size(); i++) 
        {
            if(Validation.isEmpty(colorList.get(i))) continue;
            cg.pegs.add(new Peg(colorList.get(i), pegPoints.get(i)));
        }

        return cg;
    }

    public List<Peg> getPegs() 
    {
        return pegs;
    }

    public Peg getPegByColorName(String color) 
    {
        for(Peg peg : pegs) 
        {
            if(color.equalsIgnoreCase(peg.getColor())) return peg;
        }
        return null;
    }

    public void setPegs(List<Peg> pegs) 
    {
        this.pegs = pegs;
    }

    public int getNoOfPegs() 
    {
        return noOfPegs;
    }

    public void setNoOfPegs(int noOfPegs) 
    {
        this.noOfPegs = noOfPegs;
    }

    @Override
    public String toString() 
    {
        return "ColorGrid{" +
                "pegs=" + pegs +
                ", noOfPegs=" + noOfPegs +
                '}';
    }
}
