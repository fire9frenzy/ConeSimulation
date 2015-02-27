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

	public void incrementAge()
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
		// System.out.println(percentange(1.0,100.0) <= chances());
		return (percentange(1.0,100.0) <= chances());
	}

	private double percentange(double min, double max)
	{
		Random random = new Random();
		return (min + (max - min) * random.nextDouble());
	}

	private double chances()
	{
		// if (seedFrom == 1) // one
		// {
		// 	return (0.2 + ((age - 1) * 0.25)) * 100;
		// }
		// else if (seedFrom >= 3) // 3 and less
		// {
			
		// }
		// else // more the 3
		// {

		// }
		// System.out.println("Hello");
		if(age <= 3)
		{
			if(seedFrom < 3)
			{
				return 2.0;
			}
			else if(seedFrom < 5)
			{
				return 20.0;
			}
			return 35.0;
		}
		int factor = 0;
		factor = age / 3;
		if(seedFrom < 3)
		{
			// @ 6 years 57 @9 75
			return (57.0 + ((factor - 2) * 25.0));
			// return (57.0 * (age-2));
		}
		else if(seedFrom < 5)
		{
			return (69.0 + ((factor - 2) * 25.0));
		}
		return (79.0 + ((factor - 2) * 25.0));


	}


}
