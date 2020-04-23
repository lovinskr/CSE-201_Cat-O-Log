
public class AnimalRequests 
{
	Request[] requests;
	int index;
	public AnimalRequests() {
		requests = new Request[100];
		index = 0;
	}
	public boolean addRequest(Request newRequest) {
		if (index == requests.length) return false;
		requests[index] = newRequest;
		index++;
		return true;
	}
	public void addAnimal(Catalog catalog) {
		
	}
	
}
