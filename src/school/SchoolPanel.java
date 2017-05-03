package school;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class SchoolPanel extends JPanel {
	private final int radius = 20;

	private int number;
	private List<Building> builds;
	private int[][] path;
	private List<Integer> supermarket;

	public SchoolPanel(){
	}
	
	public SchoolPanel(List<Map<String, Object>> builds) {
		init(builds);
		initSchoolMap();
	}

	private void init(List<Map<String, Object>> builds) {
		if (builds == null || builds.isEmpty()) {
			this.setNumber(0);
		} else {
			this.builds = new ArrayList<Building>();
			this.setNumber(builds.size());
			List<Integer> currentSupermarket = new ArrayList<Integer>();
			for (int i = 0; i < builds.size(); i++) {
				try {
					int x = Integer.parseInt(builds.get(i).get("X").toString());
					int y = Integer.parseInt(builds.get(i).get("Y").toString());
					String name = builds.get(i).get("NAME").toString();
					boolean isSupermarket = Boolean.parseBoolean(builds.get(i).get("isSupermarket").toString());

					Building building = new Building(name, x, y);
					this.builds.add(building);
					if(isSupermarket){
						currentSupermarket.add(i);
					}
				} catch (Exception exception) {
					this.setNumber(0);
					exception.printStackTrace();
				}
			}
			this.setSupermarket(currentSupermarket);
		}
	}

	private void initSchoolMap() {
		int[][] map = new int[number][number];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i == j) {
					map[i][j] = 0;
				} else {
					map[i][j] = (int) (Math.random() * (map.length * map.length));
				}
			}
		}
		this.setPath(map);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (builds!=null && number!=0) {
			displaySchoolMap(g);
		}
	}

	private void displaySchoolMap(Graphics g) {
		List<Building> currentBuilds = this.getBuilds();
		for(int i=0; i<currentBuilds.size(); i++){
			int x = currentBuilds.get(i).getX();
			int y = currentBuilds.get(i).getY();
			String name = currentBuilds.get(i).getName();
			
			g.fillOval(x-8, y-8, radius, radius);
			g.drawString(name, x-12, y-12);
		}
		
		int[][] currentPath = getPath();
		for(int i=0; i<currentPath.length; i++){
			for(int j=0; j<i; j++){
				if(currentPath[i][j] == 0){
				}else{
					int x1 = currentBuilds.get(i).getX() + 2;
					int y1 = currentBuilds.get(i).getY() - 2;
					int x2 = currentBuilds.get(j).getX() + 2;
					int y2 = currentBuilds.get(j).getY() - 2;
					int x3 = (x1+x2)/2 - 10;
					int y3 = (y1+y2)/2 - 10;
					g.setColor(Color.RED);
					g.drawLine(x1, y1, x2, y2);
					g.drawString(currentPath[i][j]+"", x3, y3);
					g.setColor(Color.BLACK);
				}
			}
			
			for(int j=i+1; j<currentPath[i].length; j++){
				if(currentPath[i][j] == 0){
				}else{
					int x1 = currentBuilds.get(i).getX() - 2;
					int y1 = currentBuilds.get(i).getY() + 2;
					int x2 = currentBuilds.get(j).getX() - 2;
					int y2 = currentBuilds.get(j).getY() + 2;
					int x3 = (x1+x2)/2 + 10;
					int y3 = (y1+y2)/2 + 10;
					
					g.setColor(Color.GREEN);
					g.drawLine(x1, y1, x2, y2);
					g.drawString(currentPath[i][j]+"", x3, y3);
					g.setColor(Color.BLACK);
				}
			}
		}
	}

	public List<Integer> getSupermarket() {
		return supermarket;
	}

	public void setSupermarket(List<Integer> supermarket) {
		this.supermarket = supermarket;
	}

	public List<Building> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Building> builds) {
		this.builds = builds;
		this.setNumber(builds.size());
		initSchoolMap();
		this.repaint();
	}

	public int[][] getPath() {
		return path;
	}

	public void setPath(int[][] path) {
		this.path = path;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
