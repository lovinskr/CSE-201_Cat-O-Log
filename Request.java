import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Request 
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	Integer numOfLimbs, averageLifespan, coldOrWarmBlooded;
	String[] methodsOfTravel = new String[10];
	String date;
	public String filename = "requests.txt";
	
	/**
	 * Creates a request containing the name of the person making a request and the date it was made
	 * @param name
	 * @param date
	 */
	public Request(String name, String date) {
		this.name = name;
		this.date = date;
	}
	/**
	 * Creates a request with all given information about an animal
	 * @param Aname
	 * @param Adiet
	 * @param region
	 * @param biome
	 * @param Aclass
	 * @param limbs
	 * @param lifespan
	 * @param travel
	 * @param blood
	 * @param date
	 */
	public Request(String Aname, String Adiet, String region, String biome, String Aclass,
			Integer limbs, Integer lifespan, String[] travel, String blood, String date) {
		name = Aname;
		diet = Adiet;
		commonRegion = region;
		prefferedBiome = biome;
		animalClass = Aclass;
		numOfLimbs = limbs;
		averageLifespan = lifespan;
		methodsOfTravel = travel;
		if (blood != null) {
		if(blood.contains("cold")) {
			coldOrWarmBlooded = 1;
		} else {
			coldOrWarmBlooded = 0;
		}
		}
		this.date = date;
	}
	/**
	 * Returns the name of the person making the request
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Checks to see if the animal being requested is already in the catalog
	 * @param catalog
	 * @return
	 */
	public boolean exists(Catalog catalog) {
		Animal[] animals = catalog.createSmallArray();
		for (int x = 0; x < animals.length; x++) {
			if (animals[x].name.toLowerCase().contains(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Fills in possibly vacant information for a given request
	 * @param Adiet
	 * @param region
	 * @param biome
	 * @param Aclass
	 * @param limbs
	 * @param lifespan
	 * @param travel
	 * @param blood
	 */
	public void addTraits(String Adiet, String region, String biome, String Aclass,
			Integer limbs, Integer lifespan, String[] travel, String blood) {
		if (Adiet != null) {
			diet = Adiet;
		}
		if (region != null) {
			commonRegion = region;
		}
		if (biome != null) {
			prefferedBiome = biome;
		}
		if (Aclass != null) {
			animalClass = Aclass;
		}
		if (limbs != null) {
			numOfLimbs = limbs;
		}
		if (lifespan != null) {
			averageLifespan = lifespan;
		}
		if (travel != null) {
			methodsOfTravel = travel;
		}
		if (blood != null) {
			if(blood.contains("cold")) {
				coldOrWarmBlooded = 1;
			} else {
				coldOrWarmBlooded = 0;
			}
		}
	}
	/**
	 * Converts the request information into an animal object
	 * @return
	 * @throws IOException
	 */
	public Animal changeToAnimal() throws IOException {
		String blood;
		if (coldOrWarmBlooded == 1) {
			blood = "cold";
		}
		else blood = "warm";
		Animal animal = new Animal(name, diet, commonRegion, prefferedBiome, animalClass,
				numOfLimbs, averageLifespan, methodsOfTravel, name, blood);
		return animal;
	}
	// Writes the requests to the request.txt file
	public void writeRequests() throws IOException {
		String[] TBA = {"TBA" };
		this.addTraits("TBA", "TBA", "TBA", "TBA", 0, 0,TBA , "TBA");
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
		out.write(String.valueOf(numOfLimbs));
		out.newLine();
		out.write(String.valueOf(averageLifespan));
		out.newLine();
		out.write(String.valueOf(coldOrWarmBlooded));
		out.newLine();
		for(int j = 0; j < methodsOfTravel.length; j++) {
			out.write(methodsOfTravel[j]);
		}
		out.newLine();
		out.write(date);
		out.newLine();
	out.close();	
	}
}
