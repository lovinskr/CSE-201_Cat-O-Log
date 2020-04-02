import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Animal 
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	int numOfLimbs, averageLifespan; 
	String[] methodsOfTravel = new String[10];
	String[] comments = new String[500]; 
	int lastComment = 0;
	boolean coldOrWarmBlooded; // false == cold blooded
	// File animalStorage = new File("animalStorage.dat");
	
	public Animal() {
		
	}
	
	public Animal(String Aname, String Adiet, String region, String biome, String Aclass,
			int limbs, int lifespan, String[] travel, String[] anycomments) throws IOException {
		name = Aname;
		diet = Adiet;
		commonRegion = region;
		prefferedBiome = biome;
		animalClass = Aclass;
		numOfLimbs = limbs;
		averageLifespan = lifespan;
		methodsOfTravel = travel;
		comments = anycomments;
		saveAnimal();
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
	
	public void saveAnimal() throws IOException{
			DataOutputStream out = new DataOutputStream(new FileOutputStream("AnimalStorage.dat"));
			out.writeChars(name);
			out.writeChars(diet);
			out.writeChars(commonRegion);
			out.writeChars(animalClass);
			out.writeChars(prefferedBiome);
			out.write(numOfLimbs);
			out.write(averageLifespan);
			out.writeBoolean(coldOrWarmBlooded);
			for(int i = 0; i < lastComment; i++) {
				out.writeChars(comments[i]);
			}
			for(int j = 0; j < methodsOfTravel.length; j++) {
				out.writeChars(methodsOfTravel[j]);
			}
		out.close();		
		}
	}
