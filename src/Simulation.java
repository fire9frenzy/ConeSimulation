import java.io.*;

public class Simulation
{
	public static void main(String args[])
	{
		int dimensions = 2000; //default 1Km
		FileParser info = new FileParser("../Data/coneData.txt");

		Area area = new Area(dimensions, info.getDataByMetaPop("PH"));
		info.close();
		area.setPineTrees(372);
		try
		{
			PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
			for(int i =0; i < 2; i++)
			{
				area.Year(false);
				int[] numbers = area.conesProducePerTree();
				for(int j = 0; j < 372; j++)
				{
					writer.println("Simulation\t"+numbers[j]);
				}
			}
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}