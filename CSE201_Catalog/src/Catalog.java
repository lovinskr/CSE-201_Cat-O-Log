import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        scanner = new Scanner(fileName);
        System.out.println(scanner.hasNextLine());
        animal.name = scanner.nextLine();
        animal.diet = scanner.nextLine();
        animal.commonRegion = scanner.nextLine();
        animal.prefferedBiome = scanner.nextLine();
        animal.animalClass = scanner.nextLine();
        animal.numOfLimbs = Integer.parseInt(scanner.nextLine());
        animal.averageLifespan = Integer.parseInt(scanner.nextLine());
        animal.coldOrWarmBlooded = Integer.parseInt(scanner.nextLine());
        animal.comments[0] = scanner.nextLine();
        animal.methodsOfTravel[0] = scanner.nextLine();
        scanner.close();
		
	}
}
