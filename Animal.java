
public class Animal 
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	int numOfLimbs, averageLifespan; 
	String[] methodsOfTravel = new String[10];
	String[] comments = new String[500]; 
	int lastComment = 0;
	boolean coldOrWarmBlooded; // false == cold blooded
	
	public Animal() {
		
	}
	
	String getName() {
		return name;
	}
	
	String getDiet() {
		return diet;
	}
	
	String getCommonRegion() {
		return commonRegion;
	}
	
	String getPreferredBiome() {
		return prefferedBiome;
	}
	
	String getAnimalClass() {
		return animalClass;
	}
	
	int getNumOfLimbs() {
		return numOfLimbs;
	}
	
	int getAverageLifespan() {
		return averageLifespan;
	}
	
	boolean getColdOrWarmBlooded() {
		return coldOrWarmBlooded;
	}
	
	/*
	 * Returns name, diet, commonRegion, preferredBiome, animalClass, 
	 * numOfLimbs, averageLifespan, coldOrWarmBlooded
	 */
	String[] getTraits()
	{
		String[] traits = new String[8];
		traits[0] = name;
		traits[1] = diet;
		traits[2] = commonRegion;
		traits[3] = prefferedBiome;
		traits[4] = animalClass;
		traits[5] = Integer.toString(numOfLimbs);
		traits[6] = Integer.toString(averageLifespan);
		String blood;
		if (coldOrWarmBlooded) blood = "warm blooded";
		else blood = "cold blooded";
		traits[7] = blood;
		return traits; 
	}
	
	boolean addTrait()
	{
		return false;
	}
	
	String[] getComments()
	{
		return comments; 
	}
	
	boolean addComment(String comment)
	{
		if (lastComment == 499) return false;
		comments[lastComment] = comment;
		lastComment++;
		return true; 
	}
}
