package shortestRoute;

import java.util.Comparator;

public class MyCompare implements Comparator<SearchNode>{

	public int compare(SearchNode o1, SearchNode o2) {
		double p1 = o1.getPriority();
		double p2 = o2.getPriority();

		return Double.compare(p1,p2);
	}

}
