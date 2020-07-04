package shortestRoute;

import java.awt.Point;

public class SearchNode {

	double priority;
	Point point;
	SearchNode prev;

	public SearchNode(double priority, Point point, SearchNode prev) {
		this.priority = priority;
		this.point = point;
		this.prev = prev;
	}

	double getPriority() {
		return priority;
	}

	Point getPoint() {
		return point;
	}

	public SearchNode getPrev() {
		return prev;
	}
}