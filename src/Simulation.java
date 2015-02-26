import java.io.*;
import java.util.Random;
public class Simulation
{
	public static void main(String args[])
	{
		int dimensions = 1000; 		// default 1Km
		int numOfTrees = 50;		// default fiddy
		String location = "PH";		// default PH
		int maxCones = 1000000000; 	// default no limit
		int years = 1;				// default one year
		boolean variable = false;	// default constant cycle
		int mastRate = 3;			// default 3 years
		int density = -1;
		for (int i = 0; i < args.length; ++i)
		{
			args[i] = args[i].toLowerCase();
			switch (args[i])
			{
				case "-s":
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
				case "-h":
					printHelp();
					System.exit(0);
				default: break;
			}
		}
		if (density > 0)
			numOfTrees = (density * (dimensions / 1000));
		// print off current simulation parameters
		System.out.println("Simulation Parameters" + 
							"\n  Area:      " + (dimensions / 1000) + "km^2" +
							"\n  Trees:     " + numOfTrees +
							"\n  Years:     " + years + 
							"\n  MastCycle: " + mastRate +
							"\n  Location:  " + location +
							"");

		FileParser info = new FileParser("../Data/coneData.txt");
		Area area = new Area(dimensions, info.getDataByMetaPop(location));
		info.close();
		area.setPineTrees(numOfTrees);
		try
		{
			PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
			int lastMastYear = 0;
			boolean needReset = true;
			int currentMastRate = mastRate;
			Random rand = new Random();
			for(int i = 0; i < years; ++i)
			{
				String s = "";
				if (variable)
				{
					if (needReset)
					{
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
				}
				else if (i - lastMastYear == currentMastRate)
				{
					lastMastYear = i + 1;
					area.Year(true);
					s = "M";
					needReset = true;
				}
				else
					area.Year(false);
				int[] numbers = area.conesProducePerTree();
				for(int j = 0; j < numOfTrees; ++j)						// should get end value from area object
				{
					writer.println("Simulation" + s + "\t"+numbers[j]);
				}
			}
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void printHelp()
	{
		System.out.println("-S num\n the size of the simulated area in kilometers squared.");
		System.out.println("-T num\n the number of trees in the simulated area");
		System.out.println("-L str\n either 'PH' or 'KP' determines which data to uses for cone production");
		System.out.println("-M num\n the max number of cones a tree can produce");
		System.out.println("-Y num\n the total years the simulation will run for");
		System.out.println("-I str num\n either 'fixed' or 'var' and the average number of years between mast years");
		System.out.println("-D num\n three density in trees/km^2");
	}
}