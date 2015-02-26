public class Seedling extends Soil
{
	private final int maturity = 50;
	private int year = 0;
	public Seedling()
	{

	}

	public void increaseAge()
	{
		year++;
	}

	public boolean mature()
	{
		return (year >= maturity);
	}
}
