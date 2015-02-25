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
	public Metapop getDataByMetaPop(String name)
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
	// public static void main(String[] args)
	// {
	// 	if (args.length == 1)
	// 	{
	// 		new FileParser(args[0]);
	// 	}
	// 	else
	// 	{
	// 		FileParser t = new FileParser("../../Data/coneData.txt");
	// 		t.getDataByMetaPop("PH");
	// 	}
	// }
}