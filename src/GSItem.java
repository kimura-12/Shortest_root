package shortestRoute;

import java.awt.Point;
import java.util.ArrayList;

/* テスト用のデータ構造。各自の目的に合わせて利用してください。 */

public class GSItem {
    ArrayList<Point> points = new ArrayList<>();
    GSItem(Point[] ps) {
        for(Point p:ps)
            points.add(p);
    }
    public String toString() {
        return "TestItem["+ points + "]";
    }
}
