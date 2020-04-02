
public class Catalog 
{
	Animal[] animals;
	int lastAnimal = 0;
	String searchFor;
	
	public boolean addAnimal(Animal newAnimal) {
		animals[lastAnimal] = newAnimal;
		lastAnimal++;
		return true;
	}
	public Animal[] getAnimals() {
		return animals;
	}
}
