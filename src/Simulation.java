import java.io.*;
import java.util.Random;
public class Simulation
{
	public static void main(String args[])
	{
		int ha = 100; // one hactre is 100 my 100 meters
		boolean runRscript = false;
		int dimensions = ha; 		// default 100m or 1 ha
		int numOfTrees = 150;	// default fiddy
		int minTreesPerHa = 50;
		int maxTreesPerHa = 200;
		String location = "PH";		// default PH
		int maxCones = 1000000000; 	// default no limit
		int years = 40;			// default one year
		boolean variable = false;	// default constant cycle
		int mastRate = 3;			// default 3 years
		int density = 150;
		String fileName = "Data.txt";
		boolean yearlyInfo = true;
		double squirrelLow = 74.0;
		double squirrelHigh = 89.0;
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
						if (i + 1 < args.length)
							dimensions = ha * Integer.valueOf(args[++i]);
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-t":
						if (i + 1 < args.length)
							numOfTrees = Integer.valueOf(args[++i]);
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-l":
						if (!args[i + 1].equals("KP") && !args[i + 1].equals("PH"))
							System.out.println("Location is unknown: using default settings");
						else if (args[++i].equals("KP"))
							location = "KP";
						break;
					case "-m":
						if (i + 1 < args.length)
							maxCones = Integer.valueOf(args[++i]);
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-y":
						if (i + 1 < args.length)
							years = Integer.valueOf(args[++i]);
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-i":
						if (i + 2 < args.length)
						{
							if (!args[i + 1].equals("fixed") && !args[i + 1].equals("var"))
								System.out.println("Mast year variability unknown: using defualts");
							else if(args[++i].equals("var"))
								variable = true;
							mastRate = Integer.valueOf(args[++i]);
						}
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-d":
						if (i + 1 < args.length)
						{
							density = Integer.valueOf(args[++i]);
							if (density > 500 || density < 50)
							{
								System.out.println("Density must be between 50 and 500 trees per ha.");
							}
						}
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-o":
						if (i + 1 < args.length)
						{
							if (args[i + 1].equals("year"))
								yearlyInfo = true;
							else if (args[++i].equals("tree"))
								yearlyInfo = false;
							else
								System.out.println("Unknown output type: using defualts");
						}
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-f":
						if (i + 1 < args.length)
							fileName = args[++i];
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-c":
						if (i + 2 < args.length)
						{
							seedsPerConeLow = Integer.valueOf(args[++i]);
							seedsPerConeHigh = Integer.valueOf(args[++i]);
							if (squirrelLow >= squirrelHigh)
							{
								System.out.println("ConePerSeed low bound must be lower then the high bound: using defualts");
								seedsPerConeLow = 30;
								seedsPerConeHigh = 40;
							}
						}
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-s":
						if (i + 2 < args.length)
						{
							squirrelLow = Double.valueOf(args[++i]);
							squirrelHigh = Double.valueOf(args[++i]);
							if (squirrelLow >= squirrelHigh)
							{
								System.out.println("Squirrel low bound must be lower then the high bound: using defualts");
								squirrelLow = 80;
								squirrelHigh = 90;
							}
						}
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-n":
						if (i + 1 < args.length)
							nutcrackerBoundry = Integer.valueOf(args[++i]);
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
						break;
					case "-r":
						if (i + 1 < args.length)
							runRscript = true;
						else
							System.out.println("Error parsing inputs. Missing parameter for " + args[i] + " using defaults");
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
			numOfTrees = (density * (dimensions / ha));
		// print off current simulation parameters
		// add pound
		System.out.println("Simulation Parameters" + 
							"\n  Area:      " + (dimensions / ha) + "ha" +
							"\n  Density:   " + density + "/ha" +
							"\n  Years:     " + years + 
							"\n  MastCycle: " + mastRate +
							"\n  Location:  " + location +
							"");

		FileParser info = new FileParser("../Data/coneData.txt");
		Area area = new Area(dimensions, info.getDataByMetaPop(location, maxCones),squirrelLow,squirrelHigh,nutcrackerBoundry,seedsPerConeLow,seedsPerConeHigh);
		info.close();
		area.setPineTrees(numOfTrees);
		try
		{
			PrintWriter writer = new PrintWriter("../Data/"+fileName, "UTF-8");
			if (yearlyInfo)
				writer.println("source\tyear\tmastYear\ttrees\tconesProd\tconesEaten\tconeEscape\tconeEscapeDensity\tconeEscapeThreshold\tcaches\tseedlings");
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
					writer.print((area.getYearCones() - area.getYearConesEaten()) / (dimensions / ha) + "\t");
					// // coneEscapePercent
					// writer.print(1 - ((double)area.getYearConesEaten() / (double)area.getYearCones()) + "\t");
					// coneEscapeThreshold
					writer.print(nutcrackerBoundry + "\t");
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
		if (runRscript)
		{
			try
			{
				PrintWriter writer = new PrintWriter("ConeGraphing.r", "UTF-8");
				writer.println("data.Cones = read.table(\"../Data/"+fileName+"\");");
				writer.println("#Draw boxplots to screen (X11)\n");
				writer.println("# data.Cones\n");
				writer.println("pdf(\"../Data/ConeEscape.pdf\")");
				writer.println("plot(coneEscape~year,main=\"Cone Escape per Density vs Time\", data.Cones, xlab=\"Year\", ylab=\"Cone Escape per Density\")");
				writer.println("dev.off()\n");

				writer.println("pdf(\"../Data/ConeEscapeDensity.pdf\")");
				writer.println("plot(coneEscapeDensity~year,main=\"Cone Escape of Zone per year\", data.Cones, xlab=\"Year\", ylab=\"Cone Escape per Tree Density\")");
				writer.println("dev.off()\n");

				writer.println("pdf(\"../Data/ConeProduce.pdf\")");
				writer.println("plot(conesProd~year,main=\"Cone Produce of Zone per year\", data.Cones, xlab=\"Year\", ylab=\"Cone Produce\")");
				writer.println("dev.off()\n");

				writer.println("pdf(\"../Data/ConeEaten.pdf\")");
				writer.println("plot(conesEaten~year,main=\"Cone Eaten of Zone per year\", data.Cones, xlab=\"Year\", ylab=\"Cone Eaten\")");
				writer.println("dev.off()\n");

				writer.println("pdf(\"../Data/ConeProd.pdf\")");
				writer.println("plot(conesEaten~conesProd,main=\"Cones Produces Per Year vs Cones Eaten\", data.Cones, xlab=\"Cones Produce\", ylab=\"Cone Eaten\")");
				writer.println("dev.off()\n");

				writer.println("pdf(\"../Data/ConeEscapevsConeProd.pdf\")");
				writer.println("plot(coneEscape~conesProd,main=\"Cone Escape vs Cones Produce\", data.Cones, xlab=\"Cone Produce\", ylab=\"Cone Escape\")");
				writer.println("dev.off()\n");
				writer.close();
				Runtime.getRuntime().exec("Rscript ConeGraphing.r"); 
				// System.out.println("Asdas");
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}

	}
	public static void printHelp()
	{	
		System.out.println("\nOption input\n   description\n");
		System.out.println("-A int --default 1ha\n   the size of the simulated area in ha. squared.\n");
		// System.out.println("-T int --default 150\n   the number of trees in the simulated area\n");
		System.out.println("-L str --default PH\n   {PH|KP} determines which data to uses for cone production\n");
		System.out.println("-M int --default no limit\n   the max number of cones a tree can produce\n");
		System.out.println("-Y int --default 1\n   the total years the simulation will run for\n");
		System.out.println("-I str int --default fixed 3\n   either 'fixed' or 'var' and the average number of years between mast years\n");
		System.out.println("-D int --default 150\n    density in trees/ha must be between 50 and 500\n");
		System.out.println("-O str --default year\n   {tree|year} tree gives info about trees. year gives info about each year\n");
		System.out.println("-F str --default Data.txt\n   file name including suffix\n");
		System.out.println("-S int int --default 74 89\n   low then high boundry for how much squirrels eat\n");
		System.out.println("-C int int --default 30 40\n   the boundry for seeds from each cone\n");
		System.out.println("-N int --default 1000 \n   the boundry for nutcrackers appearing\n");
		System.out.println("-R \n   including -r will attempt to run rscripts to generate graphs\n");
	}
}