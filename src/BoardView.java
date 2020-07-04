package shortestRoute;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BoardView extends JPanel {

	List<Point> points = new ArrayList<>();
	List<Point> route = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public BoardView() {

	}

	@Override
	public void paintComponent(Graphics g0) {
		Graphics2D g = (Graphics2D) g0;
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);

		int size = points.size();
		int counter = 0;
		for (Point p : points) {
			counter++;
			if (counter == size) {
				g.setColor(Color.ORANGE);
			}
			g.fillOval(p.x, p.y, 10, 10);
			g.setColor(Color.BLUE);
		}

		if (route.isEmpty()) {

		} else {
			Point here = route.get(0);
			route.remove(0);
			g.setColor(Color.RED);
			for (Point r : route) {
				g.drawLine(here.x, here.y, r.x, r.y);
				here = r;
			}
		}

	}

	public void mouseClicked(Point point) {
		addPoint(point);
		repaint();
	}

	public void addPoint(Point a) {
		points.add(a);
	}

	public double solve() {
		Solver s = new Solver();
		int size = points.size();
		Point goal = points.get(size - 1);
		double result = s.solve(points, goal);
		route = s.getRoute();
		repaint();
		return result;
	}

	public void reset() {
		points.clear();
		route.clear();
		repaint();
	}

	/*public void load(String filename) {
		points.clear();
		try {

			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				String[] data1 = line.split("=", 0);
				int x, y;
				String[] data3 = data1[2].split("]", 0);
				y = Integer.parseInt(data3[0]);
				String[] data2 = data1[1].split(",", 0);
				x = Integer.parseInt(data2[0]);

				Point temp = new Point(x, y);
				points.add(temp);
			}
			br.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			System.err.println();
		}
		repaint();
	}*/

	//object serialization
	/*
	public String load(String filename) {
		points.clear();

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".txt"));
			PointData obj1 = (PointData)in.readObject();
			points = obj1.points;
			in.close();
		} catch (FileNotFoundException e) {
			return "ファイルが見つかりません";
		} catch (IOException e) {
			return "エラー";
		} catch (ClassNotFoundException e) {
		    return "エラー";
		}
		repaint();
		return filename;
	}
	*/

	public int load(int filename) {
		points.clear();

		GSRequestGet get = new GSRequestGet();
		try {
			points = get.get(filename);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        repaint();
		return filename;
	}

	/*public void save(String filename) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

			for (Point p : points) {
				pw.println(p);
			}

			pw.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}*/

	//object serialization
	/*public void save(String filename) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			PointData obj = new PointData(points);
			out.writeObject(obj);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	*/

	public void save(int filename) {
		GSRequestPost po = new GSRequestPost();
		try {
			po.post(points, filename);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
