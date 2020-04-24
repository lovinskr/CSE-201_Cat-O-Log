import java.io.IOException;

public class AnimalRequests 
{
	Request[] requests;
	int index;
	public AnimalRequests() {
		requests = new Request[100];
		index = 0;
	}
	public Request[] viewRequests() {
		return requests;
	}
	public boolean addRequest(Request newRequest) {
		if (index == requests.length) return false;
		requests[index] = newRequest;
		index++;
		return true;
	}
	public boolean removeRequest(Request request) {
		for (int x = 0; x < index; x++) {
			if (request.name.equals(requests[x].name)) {
				if (index == 0) {
					requests[x] = null;
					return true;
				}
				requests[x] = requests[index-1];
				requests[index-1] = null;
				return true;
			}
		}
		return false;
	}
	public boolean addAnimal(Catalog catalog, Request request) throws IOException {
		for (int x = 0; x < index; x++) {
			if (requests[x].getName().equals(request.getName())) {
				Animal newAnimal = request.changeToAnimal();
				catalog.addAnimal(newAnimal);
				removeRequest(request);
				return true;
			}
		}
		return false;
	}
}
