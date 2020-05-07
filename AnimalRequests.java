
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
 * Creates a linked list to hold and manage requests
 *
 */
public class AnimalRequests 
{
	String fileName = "requests.txt";
	LinkedList<Request> requests;
	int index;
	public AnimalRequests() {
		requests = new LinkedList<Request>();
		index = 0;
	}
	/* 
	 * Constructs the linked list of requests
	 */
	public LinkedList<Request> viewRequests() {
		return requests;
	}
	/**
	 * Adds a request to the list
	 * @param newRequest
	 * @return
	 * @throws FileNotFoundException 
	 */
	public boolean addRequest(Request newRequest) throws FileNotFoundException 
	{
		requests.add(newRequest);
		writeRequests(); 
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
	/*
	 * returns an array of the requests in the linkedlist 
	 */
	Request[] getRequests() throws IOException
	{
		readRequests(); 
		Request[] reqs = new Request[requests.size()]; 
		
		ListIterator<Request> reqIt = requests.listIterator(); 
		
		int c = 0; 
		while(reqIt.hasNext())
		{
			reqs[c] = reqIt.next(); 
			c++; 
		}
		return reqs;
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
		
        Scanner read = new Scanner(new File(fileName));
		
		while(read.hasNext())
		{
			String[] methodsOfTravel = {null};
			String name = read.next().trim();
			String diet = read.next().trim();
			String commonRegion = read.next().trim();
			String biome = read.next().trim();
			String aclass = read.next().trim();
			int n = read.nextInt();
			int a = read.nextInt();
			int c = read.nextInt();
			methodsOfTravel[0] = read.next();
			String blood = "";
	        if(c == 1) {
	        	blood = "cold";
	        } else {
	        	blood = "warm";
	        }
			Request request = new Request(name, diet, commonRegion, biome, aclass,
	        		n, a, methodsOfTravel, blood);
	        requests.add(request);
		}
		
		read.close();
	}
	
	// writes requests to the file 
	void writeRequests() throws FileNotFoundException
	{
		PrintWriter to = new PrintWriter(new File(fileName));
		String line;
		ListIterator<Request> reqIt = requests.listIterator();
		to.println(); 
		
		while(reqIt.hasNext())
		{
			Request temp = reqIt.next(); 
			to.println(temp.name);
			to.println(temp.diet);
			to.println(temp.commonRegion);
			to.println(temp.prefferedBiome);
			to.println(temp.animalClass);
			to.println(temp.numOfLimbs);
			to.println(temp.averageLifespan);
			to.println(temp.coldOrWarmBlooded);
			to.println(temp.methodsOfTravel == null ? "N/A" : temp.methodsOfTravel[0]);
		}
		
		to.println();
		to.close();
	}
	
	
	
	
}