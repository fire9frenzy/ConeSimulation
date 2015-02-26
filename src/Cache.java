import java.util.*;

public class Cache extends Soil
{
	private int seeds;
	private boolean seedling = false;
	private int age = 0;
	public Cache(int seeds)
	{

		this.seeds = seeds;
	}

	public void nutCrackerFeed(int seed)
	{
		seeds = seeds - seed;
	} 

	public int getSeed()
	{
		return seeds;
	}


	public boolean hasSeeds()
	{
		return (seeds > 0);
	}

	public String toString()
	{
		return "C";
	}

	public boolean isCache()
	{
		return true;
	}

	public void incrementAge()
	{
		age++;
	}

	public boolean germinated()
	{
		return (percentange(1.0,100.0) <= chances());
	}


	private double percentange(double min, double max)
	{
		Random random = new Random();
		return (min + (max - min) * random.nextDouble());
	}

	private double chances()
	{
		if(seeds >= 1 && seeds < 3)
		{
			if(age == 2)
			{
				return 0.0;
			}
			return 27.0;
		}
		else if(seeds == 3)
		{
			if(age == 2)
			{
				return 5.0;
			}
			return 50.0;
		}
		if(age == 2)
		{
			return 3.0;
		}

		return 39.0;
	}

	public int age()
	{
		return age;
	}

	public boolean mature()
	{
		return false;
	}
}