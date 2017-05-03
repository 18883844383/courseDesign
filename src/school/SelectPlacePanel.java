package school;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class SelectPlacePanel extends JPanel{
	private final int radius = 20;
	
	private List<Building> builds;
	private int[][] path;
	private int supermarket;
	private int[][] points;
	private int finalWeight;
	
	public SelectPlacePanel(){
	}
	
	public SelectPlacePanel(List<Building> builds, int[][] path, int supermarket){
		this.builds = builds;
		this.path = path;
		this.supermarket = supermarket;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(builds!=null && !builds.isEmpty()){
			displaySchool(g);
		}
	}
	
	private void displaySchool(Graphics g){
		findOptimalSolution();
		List<Building> currentBuilds = this.getBuilds();
		for(int i=0; i<currentBuilds.size(); i++){
			int x = currentBuilds.get(i).getX();
			int y = currentBuilds.get(i).getY();
			String name = currentBuilds.get(i).getName();
			
			g.fillOval(x-8, y-8, radius, radius);
			g.drawString(name, x-12, y-12);
		}
		
		for(int i=0; i<points.length; i++){
			int x1 = currentBuilds.get(points[i][0]).getX();
			int y1 = currentBuilds.get(points[i][0]).getY();
			int x2 = currentBuilds.get(points[i][1]).getX();
			int y2 = currentBuilds.get(points[i][1]).getY();
			
			g.drawLine(x1, y1, x2, y2);
		}
	}
	
	private void findOptimalSolution(){
		
		int places = this.getSupermarket();
		int[][] map = this.getPath();
		int[][] currentPoints = new int[map.length][2];
		int[] currentPath = new int[map.length];
		initPath(currentPath, places);
		
		System.out.println("生成的图：");
		printMap(map);
		
		dijkstra(map, places, currentPath, currentPoints);
		
		System.out.println("最短路径是：");
		printPath(currentPath);
		
		this.setPoints(currentPoints);
	}
	
	private void dijkstra(int[][] map, int node, int[] path, int[][] points){
		int maxWeight = map.length*map.length + map.length;
		int currentNode = node;
		int[] leftNode = new int[map.length];
		initNodes(leftNode);
		
		for(int i=0; i<map.length-1; i++){
			if(i == 0){
				leftNode[0]= node;
				maxWeight = map.length*map.length + map.length; // 初始权值给一个无穷大值
				int len = map[currentNode].length;
				// 找到从当前节点到可到达的节点的最短路径
				for(int j=0; j<len; j++){
					if(j == node){
						continue;
					}
					int currentPath = map[leftNode[i]][j];
					path[j] = currentPath;
					points[j][0] = node;
					points[j][1] = j;
					
					if(maxWeight > currentPath){
						maxWeight = currentPath;
						currentNode = j;
					}
				}
				// 把当前节点到可到达的节点的最短路径和起始点到该点的距离进行比较
				if(path[currentNode] > maxWeight + path[leftNode[i]]){
					path[currentNode] = maxWeight + path[leftNode[i]];
					points[currentNode][0] = currentNode;
					points[currentNode][1] = leftNode[i];
				}
				System.out.print("当前起始点到各个节点的最短路径：");
				printPath(path);
				leftNode[i+1] = currentNode;
				System.out.print("选择了的节点：");
				printPath(leftNode);
			}else{
				maxWeight = map.length*map.length + map.length;
				int len = map[currentNode].length;
				// 找到从当前节点到可到达的节点的最短路径
				for(int j=0; j<len; j++){
					int currentPath = map[leftNode[i]][j];
					boolean isContain = containInMap(j, leftNode);
					if(!isContain && maxWeight > currentPath){
						maxWeight = currentPath;
						currentNode = j;
					}
				}
				// 把当前节点到可到达的节点的最短路径和起始点到该点的距离进行比较
				if(i > 0){
					if(path[currentNode] > maxWeight + path[leftNode[i]]){
						path[currentNode] = maxWeight + path[leftNode[i]];
						points[currentNode][0] = currentNode;
						points[currentNode][1] = leftNode[i];
					}
				}else{
					if(path[currentNode] > maxWeight + path[leftNode[i]]){
						path[currentNode] = maxWeight + path[leftNode[i]];
						points[currentNode][0] = currentNode;
						points[currentNode][1] = leftNode[i];
					}
				}
				System.out.print("当前起始点到各个节点的最短路径：");
				printPath(path);
				leftNode[i+1] = currentNode;
				System.out.print("选择了的节点：");
				printPath(leftNode);
			}
		}
	}
	
	private void initPath(int[] path, int node){
		for(int i=0; i<path.length; i++){
			if(i == node){
				path[i] = 0;
			}else{
				path[i] = (path.length+1)*(path.length+1)+(path.length+1);
			}
		}
	}
	
	private void initNodes(int[] nodes){
		for(int i=0; i<nodes.length; i++){
			nodes[i] = -1;
		}
	}
	
	private boolean containInMap(int node, int[] nodes){
		boolean result = false;
		for(int i=0; i<nodes.length; i++){
			if(node == nodes[i]){
				result = true;
			}
		}
		return result;
	}
	
	private void printMap(int[][] map){
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map[i].length; j++){
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private void printPath(int[] path){
		int weight = 0;
		System.out.println();
		for(int i=0; i<path.length; i++){
			weight += path[i];
			System.out.print(path[i] + "\t");
		}
		this.setFinalWeight(weight);
		System.out.println();
	}
	
	public int[][] getPoints() {
		return points;
	}

	public void setPoints(int[][] points) {
		this.points = points;
	}

	public List<Building> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Building> builds) {
		this.builds = builds;
	}

	public int[][] getPath() {
		return path;
	}

	public void setPath(int[][] path) {
		this.path = path;
	}

	public int getSupermarket() {
		return supermarket;
	}

	public void setSupermarket(int supermarket) {
		this.supermarket = supermarket;
	}

	public int getFinalWeight() {
		return finalWeight;
	}

	public void setFinalWeight(int finalWeight) {
		this.finalWeight = finalWeight;
	}
}
