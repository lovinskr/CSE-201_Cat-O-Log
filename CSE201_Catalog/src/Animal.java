import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class Animal 
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	int numOfLimbs, averageLifespan; 
	String[] methodsOfTravel = new String[10];
	String[] comments = new String[500]; 
	int lastComment = 0;
	int coldOrWarmBlooded; // 1 = cold blooded 0 = warmblooded
	File animalStorage;
	String filename;
	
	public Animal() {
		
	}
	
	public Animal(String Aname, String identifier) {
		name = Aname;
		filename = identifier + "Storage.dat";
	}
	
	public Animal(String Aname, String Adiet, String region, String biome, String Aclass,
			int limbs, int lifespan, String[] travel, String[] anycomments, String identifier, String blood) throws IOException {
		name = Aname;
		diet = Adiet;
		commonRegion = region;
		prefferedBiome = biome;
		animalClass = Aclass;
		numOfLimbs = limbs;
		averageLifespan = lifespan;
		methodsOfTravel = travel;
		comments = anycomments;
		if(blood.contains("cold")) {
			coldOrWarmBlooded = 1;
		} else {
			coldOrWarmBlooded = 0;
		}
		filename = identifier + "Storage.dat";
		animalStorage = new File(filename);
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
	
	int getColdOrWarmBlooded() {
		return coldOrWarmBlooded;
	}
	
	File getAnimalFile() {
		return animalStorage;
	}
	
	String getFilename() {
		return filename;
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
		if (coldOrWarmBlooded==0) blood = "warm blooded";
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
			FileWriter fstream = new FileWriter(filename, true);
	        BufferedWriter out = new BufferedWriter(fstream);
			out.write(name);
			out.newLine();
			out.write(diet);
			out.newLine();
			out.write(commonRegion);
			out.newLine();
			out.write(prefferedBiome);
			out.newLine();
			out.write(animalClass);
			out.newLine();
			out.write(numOfLimbs);
			out.newLine();
			out.write(averageLifespan);
			out.newLine();
			out.write(coldOrWarmBlooded);
			out.newLine();
			for(int i = 0; i < lastComment; i++) {
				out.write(comments[i]);
			}
			out.newLine();
			for(int j = 0; j < methodsOfTravel.length; j++) {
				out.write(methodsOfTravel[j]);
			}
		out.close();		
		}
	}