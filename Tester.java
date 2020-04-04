import java.io.IOException;

public class Tester {

	public static void main(String[] args) throws IOException {
		String[] travel = new String[1];
		travel[0] = "Flying";
		String[] comments = new String[1];
		comments[0] = "this bird sucks";
		Animal bird = new Animal("testBird", "Nuts", "regionz", "biomez", "birdtype",
				2, 5, travel, "B1", "warm");
		bird.addComment("this bird sucks");
		bird.saveAnimal();
		Animal birdClone = new Animal("BirdClone", "B1");
		Catalog testcat = new Catalog();
		testcat.readAnimal(birdClone);
		//System.out.println(birdClone.diet);

	}

}
