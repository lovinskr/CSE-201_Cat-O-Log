
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.Arrays;

public class Catalog 
{
	Animal[] animals;
	int lastAnimal = 0;
	String searchFor;
	
	public Catalog() throws IOException {
		animals = new Animal[500];
		Animal tiger;
		String[] travel = {"Walk"};
		tiger = new Animal("Tiger", "Carnivore", "Asia", "Tropical Rain Forest", 
				"Mammal", 4, 15, travel, "m1", "warm");
		addAnimal(tiger);
		String[] travel1 = {"Swim"};
		Animal seaBass = new Animal("Sea Bass", "Carnivore", "U.S.", "Ocean", 
				"Fish", 0, 10, travel1, "f1", "cold");
		addAnimal(seaBass);
		String[] travel2 = {"Walk"};
		Animal salamander = new Animal("Spotted Salamander", "Carnivore", "Canada", 
				"Temperate Deciduous Forest", "Amphibian", 4, 20, travel2, "a1", "cold");
		addAnimal(salamander);
		String[] travel3 = {"Flies", "Walk"};
		Animal rook = new Animal("Rook", "Omnivore", "Europe", "Grassland", 
				"Bird", 4, 20, travel3, "b1", "warm");
		addAnimal(rook);
		String[] travel4 = {"Walk"};
		Animal iguana = new Animal("Green Iguana", "Herbivore", "Americas", 
				"Tropical Rain Forest", "Reptile", 4, 20, travel4, "r1", "cold");
		addAnimal(iguana);
		String[] travel5 = {"Slithers"};
		Animal ballPython = new Animal("Ball Python", "Carnivore", "Africa", "Grassland",
				"Reptile", 0, 30, travel5, "r2", "cold");
		addAnimal(ballPython);
	}
	public Catalog(Animal[] animal, int index) {
		animals = animal;
		lastAnimal = index;
	}
	public boolean addAnimal(Animal newAnimal) {
		if (lastAnimal == animals.length) return false;
		animals[lastAnimal] = newAnimal;
		lastAnimal++;
		Arrays.sort(animals, 0, lastAnimal);
		return true;
	}
	public Animal[] getAnimals() {
		return animals;
	}
	/*
	 * 0=blood
	 * 1=action
	 * 2=species
	 * 3=biome
	 */
	public Animal[] filterAnimals(String filter, int type) {
		Animal[] filteredAnimals = new Animal[500];
		int index = 0;
		switch(type) {
		//blood
		case 0:
			int bloodType = 0;
			if (filter.contentEquals("cold")) bloodType = 1;
			for (int x = 0; x < lastAnimal; x++) {
				if (animals[x].getColdOrWarmBlooded() == bloodType) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
		//action
		case 1:
			for (int x = 0; x < lastAnimal; x++) {
				String[] travel = animals[x].getTravelMethods();
				boolean add = false;
				for (int y = 0; y < travel.length; y++) {
					if (travel[y].equals(filter)) {
						add = true;
						break;
					}
				}
				if (add) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
		//class
		case 2:
			for (int x = 0; x < lastAnimal; x++) {
				if (animals[x].getAnimalClass().equals(filter)) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
		//biome
		case 3:
			for (int x = 0; x < lastAnimal; x++) {
				if (animals[x].getPreferredBiome().equals(filter)) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
		}
		return filteredAnimals;
	}
	/*
	 * 0 for lifespan, 1 for limbs
	 */
	public Animal[] rangeAnimals(int min, int max, int type) {
		Animal[] filteredAnimals = new Animal[500];
		int index = 0;
		if (type == 0) {
			for (int x = 0; x < lastAnimal; x++) {
				if (animals[x].getAverageLifespan() >= min && 
						animals[x].getAverageLifespan() <= max) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
		}
		else {
			for (int x = 0; x < lastAnimal; x++) {
				if (animals[x].getNumOfLimbs() >= min && 
						animals[x].getNumOfLimbs() <= max) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
		}
		return filteredAnimals;
	}
	
	public void readAnimal(Animal animal) throws IOException {
		String fileName = animal.filename;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        animal.name = br.readLine();
        System.out.println(animal.name);
        animal.diet = br.readLine();
        System.out.println(animal.diet);
        animal.commonRegion = br.readLine();
        System.out.println(animal.commonRegion);
        animal.prefferedBiome = br.readLine();
        System.out.println(animal.prefferedBiome);
        animal.animalClass = br.readLine();
        System.out.println(animal.animalClass);
        animal.numOfLimbs = Integer.parseInt(br.readLine());
        System.out.println(animal.numOfLimbs);
        animal.averageLifespan = Integer.parseInt(br.readLine());
        System.out.println(animal.averageLifespan);
        animal.coldOrWarmBlooded = Integer.parseInt(br.readLine());
        System.out.println(animal.coldOrWarmBlooded);
        animal.comments[0] = br.readLine();
        animal.printComments();
        animal.methodsOfTravel[0] = br.readLine();
        animal.printTravel();
        br.close();
		
	}
}

