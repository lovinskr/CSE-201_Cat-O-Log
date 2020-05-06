import java.io.IOException;
import java.util.*;

public class AnimalRequests 
{
	LinkedList<Request> requests;
	int index;
	public AnimalRequests() {
		requests = new LinkedList<Request>();
		index = 0;
	}
	public LinkedList<Request> viewRequests() {
		return requests;
	}
	public boolean addRequest(Request newRequest) {
		requests.add(newRequest);
		return true;
	}
	public boolean removeRequest(Request request) {
		if (requests.contains(request)) {
			requests.remove(request);
			return true;
		}
		return false;
	}
	public boolean addAnimal(Catalog catalog, Request request) throws IOException {
		if (requests.contains(request)) {
			Animal newAnimal = request.changeToAnimal();
			catalog.addAnimal(newAnimal);
			removeRequest(request);
			return true;
		}
		return false;
	}
}