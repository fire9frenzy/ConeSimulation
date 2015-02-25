import java.util.ArrayList;
import java.util.Random;
public class Metapop
{
	private Random rand = new Random();
	private String name = "";
	private ArrayList<Integer> cones = new ArrayList<Integer>();
	private ArrayList<Integer> mastCones = new ArrayList<Integer>();
	public Metapop(String name)
	{
		this.name = name;
	}
	public void addCones(int val)
	{
		cones.add(val);
	}
	public void addMastCones(int val)
	{
		mastCones.add(val);
	}
	public int getRandomCone()
	{
		return cones.get(rand.nextInt(cones.size()));
	}
	public int getRandomMastCone()
	{
		if (mastCones.size() == 0)
			return -1;	
		return mastCones.get(rand.nextInt(mastCones.size()));
	}
}