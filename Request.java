
public class Request 
{
	String name, diet, commonRegion, prefferedBiome, animalClass;
	Integer numOfLimbs, averageLifespan, coldOrWarmBlooded;
	String[] methodsOfTravel = new String[10];
	String date;
	
	public Request(String name, String date) {
		this.name = name;
		this.date = date;
	}
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
	public boolean exists(Catalog catalog) {
		Animal[] animals = catalog.createSmallArray();
		for (int x = 0; x < animals.length; x++) {
			if (animals[x].name.toLowerCase().contains(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
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
}
