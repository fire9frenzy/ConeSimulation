// Main function
import java.io.*;

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
		for (int i = 0; i < args.length; ++i)
		{
			args[i] = args[i].toLowerCase();
			switch (args[i])
			{
				case "-d":
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
				case "-h":
					printHelp();
					System.exit(0);
				default: break;
			}
		}
		FileParser info = new FileParser("../Data/coneData.txt");

		Area area = new Area(dimensions, info.getDataByMetaPop(location));
		info.close();
		area.setPineTrees(numOfTrees);
		try
		{
			PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
			int lastMastYear = 0;
			for(int i = 0; i < years; ++i)
			{
				String s = "";
				if (variable)
				{
					// do some stuff	
				}
				else if (i - lastMastYear == mastRate)
				{
					lastMastYear = i + 1;
					area.Year(true);
					s = "M";
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
		System.out.println("-D num\n the size of the simulated area in kilometers squared.");
		System.out.println("-T num\n the number of trees in the simulated area");
		System.out.println("-L str\n either 'PH' or 'KP' determines which data to uses for cone production");
		System.out.println("-M num\n the max number of cones a tree can produce");
		System.out.println("-Y num\n the total years the simulation will run for");
		System.out.println("-I str num\n either 'fixed' or 'var' and the average number of years between mast years");
	}
}

// import java.io.*;

// public class Simulation
// {
// 	public static void main(String args[])
// 	{
// 		int dimensions = 2000; //default 1Km
// 		FileParser info = new FileParser("../Data/coneData.txt");

// 		Area area = new Area(dimensions, info.getDataByMetaPop("PH"));
// 		info.close();
// 		area.setPineTrees(372);
// 		try
// 		{
// 			PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
// 			for(int i =0; i < 2; i++)
// 			{
// 				area.Year(false);
// 				int[] numbers = area.conesProducePerTree();
// 				for(int j = 0; j < 372; j++)
// 				{
// 					writer.println("Simulation\t"+numbers[j]);
// 				}
// 			}
// 			writer.close();
// 		}
// 		catch(Exception e)
// 		{
// 			e.printStackTrace();
// 		}
// 	}
// }