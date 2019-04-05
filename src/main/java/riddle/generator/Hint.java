package riddle.generator;

import java.util.ArrayList;
import java.util.Random;

public class Hint
{
    private Random RANDOM = new Random();
    private ArrayList<String> hints;

    public Hint()
    {

    }

    public Hint(ArrayList<String> hints)
    {
        this.hints = new ArrayList<String>();
        this.hints = hints;
    }

    public String getHint()
    {
//    System.out.println(hints.get(1));
        int val = RANDOM.nextInt(hints.size());
        return hints.get(val);
    }
}
