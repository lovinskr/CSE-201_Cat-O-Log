import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

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
		return true;
	}
	public Animal[] getAnimals() {
		return animals;
	}
	public void readAnimal(Animal animal) throws IOException {
		String fileName = animal.filename;
        Scanner scanner;
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
