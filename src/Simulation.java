import java.io.*;
import java.util.Random;
public class Simulation
{
	public static void main(String args[])
	{
		boolean runRscript = false;
		int dimensions = 100; 		// default 100m or 1 ha
		int numOfTrees = 150;	// default fiddy
		int minTreesPerHa = 50;
		int maxTreesPerHa = 500;
		String location = "PH";		// default PH
		int maxCones = 1000000000; 	// default no limit
		int years = 40;			// default one year
		boolean variable = false;	// default constant cycle
		int mastRate = 3;			// default 3 years
		int density = 150;
		String fileName = "Data.txt";
		boolean yearlyInfo = true;
		int squirrelLow = 80;
		int squirrelHigh = 90;
		int seedsPerConeLow = 30;
		int seedsPerConeHigh = 40;
		int nutcrackerBoundry = 1000;
		for (int i = 0; i < args.length; ++i)
		{
			try
			{
				args[i] = args[i].toLowerCase();
				switch (args[i])
				{
					case "-a":
						dimensions = 1000 * Integer.valueOf(args[++i]);
						break;
					case "-t":
						numOfTrees = Integer.valueOf(args[++i]);
						break;
					case "-l":
						if (!args[i + 1].equals("KP") && !args[i + 1].equals("PH"))
							System.out.println("Location is unknown: using default settings");
						else if (args[++i].equals("KP"))
							location = "KP";
						break;
					case "-m":
						maxCones = Integer.valueOf(args[++i]);
						break;
					case "-y":
						years = Integer.valueOf(args[++i]);
						// System.out.println("Years: " + years);
						break;
					case "-i":
						if (!args[i + 1].equals("fixed") && !args[i + 1].equals("var"))
							System.out.println("Mast year variability unknown: using defualts");
						else if(args[++i].equals("var"))
							variable = true;
						mastRate = Integer.valueOf(args[++i]);
						break;
					case "-d":
						density = Integer.valueOf(args[++i]);
						break;
					case "-o":
						if (args[i + 1].equals("year"))
							yearlyInfo = true;
						else if (args[++i].equals("tree"))
							yearlyInfo = false;
						else
							System.out.println("Unknown output type: using defualts");
						break;
					case "-f":
						fileName = args[++i];
						break;
					case "-c":
						seedsPerConeLow = Integer.valueOf(args[++i]);
						seedsPerConeHigh = Integer.valueOf(args[++i]);
						if (squirrelLow >= squirrelHigh)
						{
							System.out.println("ConePerSeed low bound must be lower then the high bound: using defualts");
							seedsPerConeLow = 30;
							seedsPerConeHigh = 40;
						}
						break;
					case "-s":
						squirrelLow = Integer.valueOf(args[++i]);
						squirrelHigh = Integer.valueOf(args[++i]);
						if (squirrelLow >= squirrelHigh)
						{
							System.out.println("Squirrel low bound must be lower then the high bound: using defualts");
							squirrelLow = 80;
							squirrelHigh = 90;
						}
						break;
					case "-n":
						nutcrackerBoundry = Integer.valueOf(args[++i]);
						break;
					case "-R":
						runRscript = true;
						break;
					case "-h":
						printHelp();
						System.exit(0);
					default: break;
				}
			}
			catch(Exception e)
			{
				System.out.println("Error parsing input with string \"" + args[i] + "\"");
				System.out.println("Exiting");
				System.exit(0);
			}
		}
		if (density > 0)
			numOfTrees = (density * (dimensions / 1000));
		// print off current simulation parameters
		// add pound
		System.out.println("Simulation Parameters" + 
							"\n  Area:      " + (dimensions / 100) + "km^2" +
							"\n  Density:   " + density +
							"\n  Years:     " + years + 
							"\n  MastCycle: " + mastRate +
							"\n  Location:  " + location +
							"");

		FileParser info = new FileParser("../Data/coneData.txt");
		Area area = new Area(dimensions, info.getDataByMetaPop(location, maxCones));
		info.close();
		area.setPineTrees(numOfTrees);
		try
		{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			if (yearlyInfo)
				writer.println("source\tyear\tmastYear\ttrees\tconesProd\tconesEaten\tconeEscape\tconeEscapeDensity\tconeEscapePercent\tcaches\tseedlings");
			else
				writer.println("source\tconesProd");
			int lastMastYear = 0;
			boolean needReset = true;
			int currentMastRate = mastRate;
			Random rand = new Random();
			int index = 0;
			for(int i = 0; i < years; ++i)
			{
				// System.out.println("WOrking on year: " + (i + 1));
				String s = "";
				boolean isMastYear = false;
				if (variable && needReset)
				{
					// System.out.println("Reseting Mast Year: " + i + ", " + lastMastYear);
					// set mast year to average +- 1 year
					double r = rand.nextDouble();
					if (r < 0.75)
						currentMastRate = mastRate + 1;
					if (r > 0.25)
						currentMastRate = mastRate - 1;
					else
						currentMastRate = mastRate;
					needReset = false;
				}
				else if (i - lastMastYear == currentMastRate)
				{
					// System.out.println("Is mast year");
					lastMastYear = i + 1;
					isMastYear = true;
					s = "M";
					needReset = true;
				}

				area.Year(isMastYear);
				if (yearlyInfo) {
					// writer.println("\tsource\tyear\ttrees\tconesProd\tconesEaten\tcaches\tseedlings");
					// index
					writer.print((index++) + "\t");
					// source
					writer.print("Simulation" + "\t");
					// year
					writer.print((i + 1) + "\t");
					// isMastYear
					writer.print(isMastYear + "\t");
					// num of trees
					writer.print(area.getTreeCount() + "\t");
					// conesprod
					writer.print(area.getYearCones() + "\t");
					// cones eaten
					writer.print(area.getYearConesEaten() + "\t");
					// coneEscape
					writer.print((area.getYearCones() - area.getYearConesEaten()) + "\t");
					// coneEscapeDensity
					writer.print((area.getYearCones() - area.getYearConesEaten()) / (dimensions / 100) + "\t");
					// coneEscapePercent
					writer.print(1 - ((double)area.getYearConesEaten() / (double)area.getYearCones()) + "\t");
					// caches
					writer.print(area.getCacheCount() + "\t");
					// seedlings
					writer.println(area.getSeedlingCount());
				}
				else
				{
					int[] numbers = area.conesProducePerTree();

					for(int j = 0; j < area.getTreeCount(); ++j)						// should get end value from area object
					{
						// index
						writer.print((index++) + "\t");
						// source
						writer.print("Simulation" + "\t");
						// conesprod
						writer.print(numbers[j] + "\n");
					}
				}
				area.incrementAge();
			}
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
<<<<<<< HEAD
=======
		if (runRscript)
		{
			try
			{
				Runtime.getRuntime().exec("Rscript ../ConeGraphing.R"); 
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
>>>>>>> cef531d9e3f906c84a97c6ba367651233072d126

	}
	public static void printHelp()
	{
		System.out.println("\nOption input\n   description\n");
		System.out.println("-A int --default 2km\n   the size of the simulated area in kilometers squared.\n");
		// System.out.println("-T int --default 150\n   the number of trees in the simulated area\n");
		System.out.println("-L str --default PH\n   {PH|KP} determines which data to uses for cone production\n");
		System.out.println("-M int --default no limit\n   the max number of cones a tree can produce\n");
		System.out.println("-Y int --default 1\n   the total years the simulation will run for\n");
		System.out.println("-I str int --default fixed 3\n   either 'fixed' or 'var' and the average number of years between mast years\n");
		System.out.println("-D int --default not set\n   three density in trees/km^2\n");
		System.out.println("-O str --default year\n   {tree|year} tree gives info about trees. year gives info about each year\n");
		System.out.println("-F str --default Data.txt\n   file name including suffix\n");
		System.out.println("-S int int --default 80 90\n   low then high boundry for how much squirrels eat\n");
		System.out.println("-C int int --default 30 40\n   the boundry for seeds from each cone\n");
		System.out.println("-N int --default 100 \n   the boundry for nutcrackers appearing\n");
		System.out.println("-R \n   including -r will attempt to run rscripts to generate graphs\n");
	}
}