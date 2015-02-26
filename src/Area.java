import java.util.*;

public class Area
{
	private Soil[][] area;
	private Metapop metapop;
	private int dimension;
	private int yearCones = 0;
	private int conesEaten = 0;
	private int amountOfTrees = 0;

	//input the dimension in meter square
	public Area(int dimension, Metapop metapop)
	{
		this.dimension = dimension/((new Soil()).getDimension());
		area = new Soil[this.dimension][this.dimension];
		initiate();
		this.metapop = metapop;
	}

	private void initiate()
	{
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				area[i][j] = new Soil();
			}
		}
	}

	public void setPineTrees(int amount)
	{
		amountOfTrees = amount;
		Random random = new Random();
		for(int i = 0; i < amount; i++)
		{
			int x = random.nextInt(dimension);
			int y = random.nextInt(dimension);

			while(area[x][y].containsPine())
			{
				x = random.nextInt(dimension);
				y = random.nextInt(dimension);
			}
			area[x][y] = new LimberPine();
		}
	}

	public void Year(boolean mast)
	{
		yearCones = 0;
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				if(area[i][j].containsPine())
				{
					area[i][j].produceCones(mast,metapop);
					yearCones += area[i][j].amountCones();
				}
			}
		}

		double totalPercentage = percetange(80.0, 90.0)/100;
		double squirellPercentage = percetange(74.0, 89.0)/100;
		conesEaten = (int)(yearCones * totalPercentage);
		yearCones = yearCones - conesEaten;
		int totalSeeds = 0;
		int nutcrackerCones = (int)(conesEaten*(totalPercentage - squirellPercentage));
		System.out.println(nutcrackerCones);
		for(int i =0; i < nutcrackerCones; i++)
		{
			totalSeeds += seeds();
		}

		System.out.println("seeds"+totalSeeds);


	}

	public int[] conesProducePerTree()
	{
		int cones[] = new int[amountOfTrees];
		int counter  = 0;
		for(int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				if(area[i][j].containsPine())
				{
					cones[counter] = area[i][j].amountCones();
					counter++;
				}
			}
		}
		return cones;
	}


	private double percetange(double min, double max)
	{
		Random random = new Random();
		return (min + (max - min) * random.nextDouble());
	}

	private int seeds()
	{
		Random random = new Random();
		return (40 + (80 - 40)*random.nextInt());
	}

}