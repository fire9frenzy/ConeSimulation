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

		double totalPercentage = percentange(80.0, 90.0)/100;
		double squirellPercentage = percentange(74.0, (totalPercentage*100))/100;
		conesEaten = (int)(yearCones * totalPercentage);
		// System.out.println(yearCones);
		yearCones = yearCones - conesEaten;
		int totalSeeds = 0;
		// System.out.println(conesEaten);
		int nutcrackerCones = (int)(conesEaten*(totalPercentage - squirellPercentage));
		// System.out.println(nutcrackerCones);
		for(int i =0; i < nutcrackerCones; i++)
		{
			// System.out.println(seeds());	
			totalSeeds += seeds(40,85);
		}
		System.out.println(totalSeeds);
		createCaches(totalSeeds);

		// System.out.println("seeds"+totalSeeds);


	}

	private void createCaches(int seeds)
	{
		Random random = new Random();
		while(seeds > 0)
		{
			int x = random.nextInt(dimension);
			int y = random.nextInt(dimension);
			
			int seed= seeds(1,5);
			double perc = percentange(0.0,100.0);
			if((!area[x][y].containsPine()) && (perc <= (20 * (double)(dimension*(new Soil().dimension()))/1000)) )
			{
				area[x][y] = new Cache(seed);
				seeds =- seed;
			}
		}	
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

	public int conesLeft()
	{
		return yearCones;
	}


	private double percentange(double min, double max)
	{
		Random random = new Random();
		return (min + (max - min) * random.nextDouble());
	}

	private int seeds(int min, int max)
	{
		Random random = new Random();
		return (random.nextInt((max - min) + 1) + min);
	}

	public String toString()
	{
		String temp = "dimension for each square is"+(new Soil()).dimension();
		temp += "\n";
		for(int i =0; i < area.length; i++)
		{
			for(int j=0; j < area[i].length; j++)
			{
				temp +="| "+area[i][j]+" ";
				// table[i][j] = new Block(input[counter]);
				// counter++;
			}
			temp += "|\n";
		}
		return temp;
	}
}