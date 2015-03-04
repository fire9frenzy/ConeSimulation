public class LimberPine extends Soil
{
	private int cones;
	private int conesEaten;
	private int age;

	//constructor will create a limber pine with the given age
	//the limber pine is of mature state and can produce cones
	public LimberPine(int age)
	{
		this.age = age;
	}

	//function will return whether or not is a limber pine
	public boolean containsPine()
	{
		// super.containsPine();
		return true;
	}

	//function will obtain the amount of cones
	//the first parameter will decide whether or not is a mast year
	//second parameter will give the obtion for the given metapop
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

	//fucntion will return the amount of cones
	public int amountCones()
	{
		super.amountCones();
		return cones;
	}

	//function will return a string representation of the limberpine
	public String toString()
	{
		return "T";
	}

	//function will increment the age of the limber pine
	public void incrementAge()
	{
		age++;
	}

	//function will return the current age of the limber pine
	public int age()
	{
		return age;
	}

}