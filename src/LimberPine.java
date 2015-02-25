public class LimberPine extends Soil
{
	private int cones;
	public LimberPine()
	{
		
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

}