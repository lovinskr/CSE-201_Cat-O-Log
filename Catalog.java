
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Catalog 
{
	Animal[] animals;
	int lastAnimal = 0;
	String searchFor;
	Animal[] currentlyDisplaying;
	
	public Catalog() throws IOException {
		animals = new Animal[500];
		currentlyDisplaying = animals;
		
		// reads the animals that we have files for 
		String current = System.getProperty("user.dir");
		
		try (Stream<Path> walk = Files.walk(Paths.get(current))) {

			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(" Storage.dat")).collect(Collectors.toList());
			
			for(String file : result) {
				this.readAnimal(file);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	public Catalog(Animal[] animal, int index) {
		animals = animal;
		lastAnimal = index;
	}
	public boolean addAnimal(Animal newAnimal) throws IOException {
		if (lastAnimal == animals.length) return false;
		animals[lastAnimal] = newAnimal;
		lastAnimal++;
		Arrays.sort(animals, 0, lastAnimal);
		return true;
	}
	
	public Animal[] getAnimals() {
		return animals;
	}
	public int getIndex() {
		return lastAnimal;
	}
	public Animal[] createSmallArray() {
		Animal[] small = new Animal[lastAnimal];
		for (int x = 0; x < lastAnimal; x++) {
			small[x] = animals[x];
		}
		return small;
	}
	
	public Animal[] createSmallArray(Animal[] temp) 
	{
		int c = 0; 
		Animal[] small = new Animal[temp.length];
		for (int x = 0; x < temp.length && temp[x] != null; x++) {
			small[x] = temp[x];
			c = x; 
		}
		c++;
		Animal[] a = new Animal[c]; 
		for(int i = 0; i < c; i++)
		{
			a[i] = small[i];
		}
		return a;
	}
	
	public Animal[] searchFor(String search) {
		Animal[] searchArray = new Animal[500];
		int index = 0;
		for (int x = 0; x < lastAnimal; x++) {
			if (animals[x].name.toLowerCase().contains(search.toLowerCase())) {
				searchArray[index] = animals[x];
				index++;
			}
		}
		return searchArray;
	}
	/*
	 * 0=blood
	 * 1=action
	 * 2=class
	 * 3=biome
	 * 4=diet
	 * 5 = region 
	 * 6 = bloodtype 
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
		//diet
		case 4:
			for (int x = 0; x < lastAnimal; x++) {
				if (animals[x].getDiet().equals(filter)) {
					filteredAnimals[index] = animals[x];
					index++;
				}
			}
			break; 
		// region 
		case 5:
			for(int c = 0; c < lastAnimal; c++)
			{
				if(animals[c].getCommonRegion().equals(filter))
				{
					filteredAnimals[index] = animals[c]; 
					index++; 
				}
			}
		// cold and warm blooded 	
		case 6:
			int x = 0; // warm is default 
			if(filter.equalsIgnoreCase("cold"))
				x = 1; 
			for(int c = 0; c < lastAnimal; c++)
			{
				if(animals[c].getColdOrWarmBlooded() == x)
				{
					filteredAnimals[index] = animals[c]; 
					index++; 
				}
			}
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
	
	public boolean changeDisplay(Animal[] newArray) {
		currentlyDisplaying = newArray;
		return true;
	}
	
	public Animal[] joinArrays(Animal[] a1, Animal[] a2) {
		Animal[] newArray = new Animal[500];
		int index = 0;
		for (int x = 0; x < 500 && a1 != null; x++) {
			if (a1[x] != null) {
				newArray[index] = a1[x];
				index++;
			}
		}
		for (int x = 0; x < 500 && a2 != null; x++) {
			if (a2[x] != null) {
				boolean contains = false;
				for (int y = 0; y < index; y++) {
					if (a2[x].compareTo(newArray[y]) == 0) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					newArray[index] = a2[x];
					index++;
				}
			}
		}
		Arrays.sort(newArray, 0, index);
		return newArray;
	}
	/*
	 * removes anything in remove from main array
	 */
	public Animal[] separateArrays(Animal[] main, Animal[] remove) {
		Animal[] newArray = new Animal[500];
		int index = 0;
		for (int x = 0; x < 500; x++) {
			if (main[x] != null) {
				boolean contains = false;
				for (int y = 0; y < 500; y++) {
					if (remove[y] != null && remove[y].compareTo(main[x]) == 0) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					newArray[index] = main[x];
					index++;
				}
			}
		}
		return newArray;
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
        animal.methodsOfTravel[0] = br.readLine();
        animal.printTravel();
        br.close();
		
	}
	
	public void readAnimal(String file) throws IOException {
		Animal a = new Animal();
		String fileName = file;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        a.name = br.readLine();
        a.diet = br.readLine();
        a.commonRegion = br.readLine();
        a.prefferedBiome = br.readLine();
        a.animalClass = br.readLine();
        a.numOfLimbs = Integer.parseInt(br.readLine());
        a.averageLifespan = Integer.parseInt(br.readLine());
        a.coldOrWarmBlooded = Integer.parseInt(br.readLine());
        a.comments.add(br.readLine());
        a.methodsOfTravel[0] = br.readLine();
        br.close();
        this.addAnimal(a);
		
	}
}