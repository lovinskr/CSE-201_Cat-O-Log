
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
	
	public Catalog() {
		animals = new Animal[500];
	}
	public Catalog(Animal[] animal, int index) {
		animals = animal;
		lastAnimal = index;
	}
	public boolean addAnimal(Animal newAnimal) {
		if (lastAnimal == animals.length) return false;
		animals[lastAnimal] = newAnimal;
		lastAnimal++;
		Arrays.sort(animals);
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
			for (int x = 0; x < animals.length; x++) {
				if (animals[x].getColdOrWarmBlooded() == bloodType) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
		//action
		case 1:
			for (int x = 0; x < animals.length; x++) {
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
			for (int x = 0; x < animals.length; x++) {
				if (animals[x].getAnimalClass().equals(filter)) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
		//biome
		case 3:
			for (int x = 0; x < animals.length; x++) {
				if (animals[x].getPreferredBiome().equals(filter)) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break;
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

