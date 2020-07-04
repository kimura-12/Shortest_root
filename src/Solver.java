package shortestRoute;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {

	public static final double OneHop = 10.00001;
	List<Point> point = new ArrayList<>();

	public Double solve(List<Point> points, Point goal) {
		PriorityQueue<SearchNode> q = new PriorityQueue<>(new MyCompare()); /* 優先度キュー */
		HashMap<Point, Double> visited = new HashMap<>(); /* 探索済みの点の管理 */
		System.out.println("Points: " + points);
		System.out.println("Goal: " + goal);
		Point start = points.get(0);
		SearchNode rootNode = new SearchNode(0.0, start, null);
		q.add(rootNode);
		SearchNode here = rootNode;
		SearchNode result = null;
		SearchNode temp;
		SearchNode pre;
		while (true) {
			here = q.poll();

			if (here == null) {
				break;
			}

			if (here.getPoint() == goal) {
				if (result == null) {
					result = here;
				} else if (result.getPriority() > here.getPriority())
					result = here;
			}

			for (Point p : points) {
				double dist = here.point.distance(p);
				if (dist == 0.0 || dist > 60.0)
					continue;
				double nextPri = here.getPriority() + dist;
				if (visited.containsKey(p)) {
					double a = visited.get(p);
					if (a > nextPri) {
						visited.put(p, nextPri);
						q.add(new SearchNode(nextPri, p, here));
					} else {
						visited.put(p, a);
					}

				} else {
					visited.put(p, nextPri);
					q.add(new SearchNode(nextPri, p, here));

				}
			}
		}

		if (visited.containsKey(goal)) {

		} else {
			visited.put(goal, 0.0);
		}
		if (result != null) {
			point.add(result.getPoint());
			pre = result;

			while (true) {
				temp = pre.getPrev();
				point.add(temp.getPoint());
				if (temp.getPrev() == null) {
					break;
				}
				pre = temp;
			}

			for (Point a : point) {
				System.out.println(a);
			}
		}

		return visited.get(goal);
	}

	public List<Point> getRoute() {
		return point;
	}
}
