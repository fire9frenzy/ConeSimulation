public class LimberPine extends Soil
{
	private int cones;
	private int conesEaten;
	private int age;
	public LimberPine(int age)
	{
		this.age = age;
	}

	public boolean containsPine()
	{
		// super.containsPine();
		return true;
	}

	public void produceCones(boolean mast,Metapop metapop)
	{
		super.produceCones(mast,metapop);
		if(mast)
		{
			cones = metapop.getRandomMastCone();
		}
		else
		{
			cones = metapop.getRandomCone();
		}
	}

	public int amountCones()
	{
		super.amountCones();
		return cones;
	}

	public String toString()
	{
		return "T";
	}

	public void incrementAge()
	{
		age++;
	}

	public int age()
	{
		return age;
	}

}