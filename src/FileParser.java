import java.util.Scanner;
import java.io.File;

public class FileParser
{
	private Scanner sc = null;
	public FileParser(String fileName)
	{
		try
		{
			sc = new Scanner(new File(fileName));
			// close();
		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}
	}
	// gets tree production by location specified by name limits cone production to max cones
	public Metapop getDataByMetaPop(String name, int maxCones)
	{
		Metapop data = new Metapop(name);
		while(sc.hasNextLine())
		{
			String[] l = sc.nextLine().split("\t");
			if (l[0].equals(name))
			{
				if (!l[5].equals("NA"))
				{
					// System.out.println(l[0]);
					if (l[6].equals("0"))
					{
						// System.out.println(l[5]);
						if (Integer.valueOf(l[5]) > maxCones)
							data.addCones(maxCones);
						else
							data.addCones(Integer.valueOf(l[5]));
					}
					else
					{
						data.addMastCones(Integer.valueOf(l[5]));
					}
				}
			}
		}
		return data;
	}
	public void close()
	{
		sc.close();
	}
}