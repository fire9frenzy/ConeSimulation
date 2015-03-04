import java.util.*;

//class use to represent a cache
public class Cache extends Soil
{
	private int seeds;
	private boolean seedling = false;
	private int age = 0;

	//constructor use to create a cache
	//the parameter indicates the amount of seeds that the cache has
	public Cache(int seeds)
	{
		this.seeds = seeds;
	}

	//function will subtract the amount of seeds in the parameter from the amount of seeds that the cache has
	public void nutCrackerFeed(int seed)
	{
		seeds = seeds - seed;
	} 

	//function will return the amount of seeds currently in the cache
	public int getSeed()
	{
		return seeds;
	}

	//fucntion will return a boolean value whether or not the cache has seeds
	public boolean hasSeeds()
	{
		return (seeds > 0);
	}

	//function will return a string representation of the cache
	public String toString()
	{
		return "C";
	}

	//function will return true if the area is a cache
	public boolean isCache()
	{
		return true;
	}

	//function will increment the age of the cache
	public void incrementAge()
	{
		age++;
	}

	//function will randomly decide whether or not the seeds in the cache has germinated
	public boolean germinated()
	{
		return (percentange(1.0,100.0) <= chances());
	}

	//function will return a random percentage within the given range
	private double percentange(double min, double max)
	{
		Random random = new Random();
		return (min + (max - min) * random.nextDouble());
	}

	//function will return the chances of the seeds germinating
	//the changes depends on how many seeds there are and how old it is
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

	//function will return the age of the cache
	public int age()
	{
		return age;
	}
}