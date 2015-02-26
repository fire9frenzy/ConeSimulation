public class Cache extends Soil
{
	private int seeds;
	private boolean seedling = false;
	public Cache(int seeds)
	{

		this.seeds = seeds;
	}

	public void nutCrackerFeed()
	{
		seeds = 0;
	} 

	public int getSeed()
	{
		return seeds;
	}

	public void seedling()
	{

	}

	public boolean isSeedling()
	{
		return seedling;
	}


	public String toString()
	{
		return "C";
	}
}