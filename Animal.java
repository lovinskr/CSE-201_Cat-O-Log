import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Creates an Animal and stores their data in a text file
 *
 */
public class Animal implements Comparable<Animal>
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	Integer numOfLimbs, averageLifespan, coldOrWarmBlooded; // 1 = cold blooded 0 = warmblooded
	String[] methodsOfTravel = new String[10];
	LinkedList comments = new LinkedList(); 
	int lastComment = 0; 
	File animalStorage;
	String filename;
	
	/**
	 * Base empty constructor
	 */
	public Animal() {
		
	}
	/**
	 * Creates an animal from a string name and identifer, used with requests
	 * @param Aname
	 * @param identifier
	 */
	public Animal(String Aname, String identifier) {
		name = Aname;
		filename = identifier + "Storage.dat";
		try {
			readComments();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Creates an animal with all given trait fields
	 * @param Aname
	 * @param Adiet
	 * @param region
	 * @param biome
	 * @param Aclass
	 * @param limbs
	 * @param lifespan
	 * @param travel
	 * @param identifier
	 * @param blood
	 * @throws IOException
	 */
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
		readComments(); 
	}
	/**
	 * Returns the name
	 * @return
	 */
	String getName() {
		return name;
	}
	/**
	 * Returns the diet
	 * @return
	 */
	String getDiet() {
		return diet;
	}
	/**
	 * Returns the region
	 * @return
	 */
	String getCommonRegion() {
		return commonRegion;
	}
	/**
	 * Returns the Biome
	 * @return
	 */
	String getPreferredBiome() {
		return prefferedBiome;
	}
	/**
	 * Returns the class
	 * @return
	 */
	String getAnimalClass() {
		return animalClass;
	}
	/**
	 * Returns the travel methods
	 * @return
	 */
	String[] getTravelMethods() {
		return methodsOfTravel;
	}
	/**
	 * Returns the number of limbs
	 * @return
	 */
	int getNumOfLimbs() {
		return numOfLimbs;
	}
	/**
	 * Returns the lifespan
	 * @return
	 */
	int getAverageLifespan() {
		return averageLifespan;
	}
	/**
	 * Returns the blood type
	 * @return
	 */
	int getColdOrWarmBlooded() {
		return coldOrWarmBlooded;
	}
	/**
	 * Returns the file where the animal is stored
	 * @return
	 */
	File getAnimalFile() {
		return animalStorage;
	}
	/**
	 * Returns the name of the file in which the animal is stored
	 * @return
	 */
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
	/**
	 * Returns the string array of the comments for the animal
	 * @return
	 */
	String[] getComments() throws IOException
	{
		String[] com = new String[comments.size()];
		
		ListIterator<String> commentIterator = comments.listIterator();
		for(int c = 0; c < com.length; c++)
		{
			com[c] = commentIterator.next(); 
		}
		
		return com; 
	}
	/**
	 * Adds a comment to the animal's comment list
	 * @return
	 */
	boolean addComment(String comment) throws IOException
	{ 
		lastComment++;
		writeComments(comment); 
		return true; 
	}
	/**
	 * Reads the animals comments
	 * @return
	 */
	public void readComments() throws IOException 
	{
		Scanner read = new Scanner(new File(name + " Comments.txt"));
		
		while(read.hasNext())
		{
			String line = read.nextLine(); 
			if(!line.isEmpty())
				comments.add(line); 
		}
		
		read.close();
	}
	/**
	 * Saves rhe comments to the animals file
	 * @return
	 */
	void writeComments(String newCom) throws IOException 
	{
		//readUsers(); // get all current accounts in the linked list
		comments.add(newCom); 
		PrintWriter to = new PrintWriter(new File(filename));
		String line;
		ListIterator<String> ui = comments.listIterator();
		to.println(); 
		
		while(ui.hasNext())
		{
			
		}
		
		to.println();
		to.close();
	}
	/**
	 *Prints the animals comments
	 * @return
	 */
	void printComments() throws IOException {
		String[] com = getComments(); 
		System.out.println(com[0]);
		for(int i = 0; i < com.length; i++) {
			System.out.println(com[i]);
		}
	}
	/**
	 * Prints the animals travel methods
	 * @return
	 */
	void printTravel() {
		for(int i = 0; i < methodsOfTravel.length; i++) {
			System.out.println(methodsOfTravel[i]);
		}
	}
	/**
	 * Saves the animal to a specific unique text file
	 * @return
	 */
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
			String[] com = getComments(); 
			for(int i = 0; i < com.length; i++) {
				out.write(com[i]);
			}
			out.newLine();
			for(int j = 0; j < methodsOfTravel.length; j++) {
				out.write(methodsOfTravel[j]);
			}
		out.close();		
		}
//Below is the list of comparators for each trait used for sorting with the GUI
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
