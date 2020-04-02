
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
}
