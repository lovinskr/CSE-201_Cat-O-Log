import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Animal implements Comparable<Animal>
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	Integer numOfLimbs, averageLifespan, coldOrWarmBlooded; // 1 = cold blooded 0 = warmblooded
	String[] methodsOfTravel = new String[10];
	String[] comments = new String[500]; 
	int lastComment = 0; 
	File animalStorage;
	String filename;
	
	public Animal() {
		
	}
	
	public Animal(String Aname, String identifier) {
		name = Aname;
		filename = identifier + "Storage.dat";
	}
	
	public Animal(String Aname, String Adiet, String region, String biome, String Aclass,
			int limbs, int lifespan, String[] travel, String identifier, String blood) throws IOException {
		name = Aname;
		diet = Adiet;
		commonRegion = region;
		prefferedBiome = biome;
		animalClass = Aclass;
		numOfLimbs = limbs;
		averageLifespan = lifespan;
		methodsOfTravel = travel;
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
	
	String[] getTravelMethods() {
		return methodsOfTravel;
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
	void printComments() {
		System.out.println(comments[0]);
		for(int i = 0; i < lastComment; i++) {
			System.out.println(comments[i]);
		}
	}
	
	void printTravel() {
		for(int i = 0; i < methodsOfTravel.length; i++) {
			System.out.println(methodsOfTravel[i]);
		}
	}
	
	public void saveAnimal() throws IOException {
		File checker = new File(filename);
		if(checker.exists()) {
			checker.delete();
		}
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
			out.write(String.valueOf(getNumOfLimbs()));
			out.newLine();
			out.write(String.valueOf(getAverageLifespan()));
			out.newLine();
			out.write(String.valueOf(getColdOrWarmBlooded()));
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

	public int compareTo(Animal o) {
		Animal a = (Animal) o;
		return this.name.compareTo(a.name);
	}
}
class SortReverse implements Comparator<Animal> {
	public int compare(Animal a, Animal b) {
		return b.name.compareTo(a.name);
	}
	
}
class SortLimbs implements Comparator<Animal> {
	public int compare(Animal a, Animal b) {
		int compare = a.numOfLimbs - b.numOfLimbs;
		if (compare == 0) {
			return a.filename.compareTo(b.name);
		}
		else return compare;
	}
}
class SortBiome implements Comparator<Animal> {
	public int compare(Animal a, Animal b) {
		return a.prefferedBiome.compareTo(b.prefferedBiome);
	}
}
/*
 * still figuring this out rip
 * havent figured out filtering by blood either
 */
class SortTravel implements Comparator<Animal> {
	public int compare(Animal a, Animal b) {
		return 0;
	}
}
class SortLifespan implements Comparator<Animal> {
	public int compare(Animal a, Animal b) {
		return a.averageLifespan-b.averageLifespan;
	}
}
class SortDiet implements Comparator<Animal> {
	public int compare(Animal a, Animal b) {
		return a.diet.compareTo(b.diet);
	}
}

class SortCold implements Comparator<Animal>
{
	public int compare(Animal a, Animal b)
	{
		return b.getColdOrWarmBlooded()-(a.getColdOrWarmBlooded());
	}
}

class SortWarm implements Comparator<Animal>
{
	public int compare(Animal a, Animal b)
	{
		return a.getColdOrWarmBlooded()-(b.getColdOrWarmBlooded());
	}
}
