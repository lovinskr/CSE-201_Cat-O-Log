import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Creates a linked list to hold and manage requests
 *
 */
public class AnimalRequests 
{
	LinkedList<Request> requests;
	int index;
	public AnimalRequests() {
		requests = new LinkedList<Request>();
		index = 0;
	}
	/**
	 * Constructs the linked list of requests
	 */
	public LinkedList<Request> viewRequests() {
		return requests;
	}
	/**
	 * Adds a request to the list
	 * @param newRequest
	 * @return
	 */
	public boolean addRequest(Request newRequest) {
		requests.add(newRequest);
		return true;
	}
	/*
	 * Finds a given request and removes it if it is in the list
	 */
	public boolean removeRequest(Request request) {
		if (requests.contains(request)) {
			requests.remove(request);
			return true;
		}
		return false;
	}
	/**
	 * Adds an animal to the catalog from a request and then removes the request from the linked list
	 * @param catalog
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public boolean addAnimal(Catalog catalog, Request request) throws IOException {
		if (requests.contains(request)) {
			Animal newAnimal = request.changeToAnimal();
			catalog.addAnimal(newAnimal);
			removeRequest(request);
			return true;
		}
		return false;
	}
	// Reads the requests from the request text file
	public void readRequests() throws IOException {
		
		String fileName = "requests.txt";
        Scanner read = new Scanner(new File(fileName));
		
		while(read.hasNext())
		{
			String[] methodsOfTravel = {};
			String name = read.next().trim();
			String diet = read.next().trim();
			String commonRegion = read.next().trim();
			String biome = read.next().trim();
			String aclass = read.next().trim();
			int n = read.nextInt();
			int a = read.nextInt();
			int c = read.nextInt();
			methodsOfTravel[0] = read.next();
			String date = read.next().trim();
			String blood = "";
	        if(c == 1) {
	        	blood = "cold";
	        } else {
	        	blood = "warm";
	        }
			Request request = new Request(name, diet, commonRegion, biome, aclass,
	        		n, a, methodsOfTravel, blood, date);
	        requests.add(request);
		}
		
		read.close();
	}
}