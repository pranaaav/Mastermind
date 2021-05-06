/**
 * Peg class is used to specify single coloured pegs.
 * which can be used within any guess sequence within the game.
 */
import java.util.Objects;


public class Peg implements Comparable<Peg>
{

    private String color;
    private int value;

    public Peg() 
    {
    }

    public Peg(String color, int value)  
    {
        this.color = color;
        this.value = value;
    }

    public String getColor() 
    {
        return color;
    }

    public void setColor(String color) 
    {
        this.color = color;
    }

    public int getValue() 
    {
        return value;
    }

    public void setValue(int value) 
    {
        this.value = value;
    }

    @Override
    public int compareTo(Peg peg) 
    {
        return this.color.compareTo(peg.color);
    }

    @Override
    public String toString() 
    {
        return "Peg{" +
                "color='" + color + '\'' +
                ", value=" + value +
                '}';
    }
}

