import java.util.*;

//parent constructor for the following classes:
//LimberPine,Seedling and Cache
public class Soil
{
	//dimention of the soil is 5m by 5m
	private final int dimension = 5; //1 meter per 1 meter

	public Soil()
	{

	}

	public boolean containsPine()
	{
		return false;
	}

	public int getDimension()
	{
		return dimension;
	}
	
	public int amountCones()
	{
		return 0;
	}

	public void produceCones(boolean mast, Metapop metapop)
	{

	}

	public int dimension()
	{
		return dimension;
	}	

	public String toString()
	{
		return " ";
	}

	public boolean isCache()
	{
		return false;
	}

	public int getSeed()
	{
		return 0;
	}

	public void nutCrackerFeed(int seed)
	{
	} 

	public boolean hasSeeds()
	{
		return false;
	}

	public void incrementAge()
	{
	}

	public boolean isSeedling()
	{
		return false;
	}

	public boolean germinated()
	{
		return false;
	}

	public int age()
	{
		return 0;
	}

	public boolean survived()
	{
		return false;
	}

	public boolean mature()
	{
		return false;
	}

}