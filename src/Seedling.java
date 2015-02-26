import java.util.*;

public class Seedling extends Soil
{
	private final int maturity = 50;
	private int seedFrom = 0;
	private int age = 0;
	public Seedling(int seeds)
	{
		seedFrom = seeds;
	}

	public void increaseAge()
	{
		age++;
	}

	public boolean mature()
	{
		return (age >= maturity);
	}

	public boolean isSeedling()
	{
		return true;
	}

	public int age()
	{
		return age;
	}

	public boolean survived()
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

		if(age < 3)
		{
			if(seedFrom < 3)
			{
				return 2.0;
			}
			else if(seedFrom< 5)
			{
				return 20.0;
			}
			return 35.0;
		}
		if(seedFrom < 3)
		{
			return 57.0;
		}
		else if(seedFrom < 5)
		{
			return 69.0;
		}

		return 79.0;
	}


}
