package triangle;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TowerPanel extends JPanel{
	private final int width = 20;
	private final int height = 20;
	private final int vGap = 50;
	private final int hGap = 50;
	
	private int[][] nodes;//数塔的节点
	private int layer;//数塔的层数
	private int maxValue;//数塔的最大值
	private int[][] results;//计算数塔自顶到底路径的最大值需要的数组
	
	public TowerPanel(int layer){
		this.layer = layer;
		//初始化数塔
		initTower();
		//计算数塔自顶到底路径的最大值
		calculatedResult();
	}
	
	/**
	 * 初始化数塔
	 */
	private void initTower(){
		nodes = new int[layer][];
		for(int i=0; i<nodes.length; i++){
			nodes[i] = new int[i+1];
			for(int j=0; j<nodes[i].length; j++){
				nodes[i][j] = (int) (Math.random()*(layer*layer) + 1);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(nodes!=null){
			boolean[][] isAfterNodes = throughNodes();
			displayTower(g,getWidth()/2,30,isAfterNodes);
		}
	}
	
	/**
	 * 展示数塔
	 * @param g   			画图工具
	 * @param x 			x轴起始坐标
	 * @param y				y轴起始坐标
	 * @param isAfterNodes  标示路过的节点
	 */
	private void displayTower(Graphics g, int x, int y,boolean[][] isAfterNodes){
		for(int i=0; i<nodes.length; i++){
			int centerIndex = nodes[i].length/2;
			int currentVGap = i*vGap;
			if(nodes[i].length%2==0){
				for(int j=0; j<nodes[i].length; j++){
					if(j < centerIndex){
						int currentHGap = i*hGap - j*2*hGap;
						if(i!=nodes.length-1){
							int x1 = x-currentHGap+width/2;
							int y1 = y+currentVGap+height;
							int x2 = x-currentHGap-hGap+width/2;
							int y2 = y+currentVGap+vGap;
							int x3 = x-currentHGap+hGap+width/2;
							int y3 = y+currentVGap+vGap;
							
							if(isAfterNodes[i][j] && isAfterNodes[i+1][j]){
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.RED);
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.BLACK);
							}else if(isAfterNodes[i][j] && isAfterNodes[i+1][j+1]){
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.RED);
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.drawLine(x1, y1, x3, y3);
							}
						}else{
							if(isAfterNodes[i][j]){
								g.setColor(Color.RED);
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
							}
						}
					}else{
						int currentHGap = (2*(j-centerIndex+1)-1)*hGap;
						if(i!=nodes.length-1){
							int x1 = x+currentHGap+width/2;
							int y1 = y+currentVGap+height;
							int x2 = x+currentHGap-hGap+width/2;
							int y2 = y+currentVGap+vGap;
							int x3 = x+currentHGap+hGap+width/2;
							int y3 = y+currentVGap+vGap;

							if(isAfterNodes[i][j] && isAfterNodes[i+1][j]){
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.RED);
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.BLACK);
							}else if(isAfterNodes[i][j] && isAfterNodes[i+1][j+1]){
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.RED);
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.drawLine(x1, y1, x3, y3);
							}
						}else{
							if(isAfterNodes[i][j]){
								g.setColor(Color.RED);
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
							}
						}
					}
				}
			}else{
				for(int j=0; j<nodes[i].length; j++){
					if(j < centerIndex){
						int currentHGap = (i/2-j)*2*hGap;
						if(i!=nodes.length-1){
							int x1 = x-currentHGap+width/2;
							int y1 = y+currentVGap+height;
							int x2 = x-currentHGap-hGap+width/2;
							int y2 = y+currentVGap+vGap;
							int x3 = x-currentHGap+hGap+width/2;
							int y3 = y+currentVGap+vGap;

							if(isAfterNodes[i][j] && isAfterNodes[i+1][j]){
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.RED);
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.BLACK);
							}else if(isAfterNodes[i][j] && isAfterNodes[i+1][j+1]){
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.RED);
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.drawLine(x1, y1, x3, y3);
							}
						}else{
							if(isAfterNodes[i][j]){
								g.setColor(Color.RED);
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x-currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x-currentHGap+4, y+currentVGap+14);
							}
						}
					}else if(j > centerIndex){
						int currentHGap = (2*(j-centerIndex))*hGap;
						if(i!=nodes.length-1){
							int x1 = x+currentHGap+width/2;
							int y1 = y+currentVGap+height;
							int x2 = x+currentHGap-hGap+width/2;
							int y2 = y+currentVGap+vGap;
							int x3 = x+currentHGap+hGap+width/2;
							int y3 = y+currentVGap+vGap;

							if(isAfterNodes[i][j] && isAfterNodes[i+1][j]){
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.RED);
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.BLACK);
							}else if(isAfterNodes[i][j] && isAfterNodes[i+1][j+1]){
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.RED);
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.drawLine(x1, y1, x3, y3);
							}
						}else{
							if(isAfterNodes[i][j]){
								g.setColor(Color.RED);
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x+currentHGap, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+currentHGap+4, y+currentVGap+14);
							}
						}
					}else{
						if(i!=nodes.length-1){
							int x1 = x+width/2;
							int y1 = y+currentVGap+height;
							int x2 = x-hGap+width/2;
							int y2 = y+currentVGap+vGap;
							int x3 = x+hGap+width/2;
							int y3 = y+currentVGap+vGap;

							if(isAfterNodes[i][j] && isAfterNodes[i+1][j]){
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.RED);
								g.drawRect(x, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.BLACK);
							}else if(isAfterNodes[i][j] && isAfterNodes[i+1][j+1]){
								g.drawLine(x1, y1, x2, y2);
								g.setColor(Color.RED);
								g.drawRect(x, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+4, y+currentVGap+14);
								g.drawLine(x1, y1, x3, y3);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+4, y+currentVGap+14);
								g.drawLine(x1, y1, x2, y2);
								g.drawLine(x1, y1, x3, y3);
							}
						}else{
							if(isAfterNodes[i][j]){
								g.setColor(Color.RED);
								g.drawRect(x, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+4, y+currentVGap+14);
								g.setColor(Color.BLACK);
							}else{
								g.drawRect(x, y+currentVGap, width, height);
								g.drawString(nodes[i][j]+"", x+4, y+currentVGap+14);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 标示出路过的节点
	 * @return
	 */
	private boolean[][] throughNodes(){
		int[][] result = this.getResults();
		int[][] currentNodes = this.getNodes();
		boolean[][] isAfterNodes = new boolean[currentNodes.length][];
		for(int i=0; i<isAfterNodes.length; i++){
			isAfterNodes[i] = new boolean[i+1];
			int currentMaxIndex = 0;
			if(i>0){
				currentMaxIndex = maxValueIndex(i, isAfterNodes[i-1],result);
			}
			for(int j=0; j<isAfterNodes[i].length; j++){
				if(j == currentMaxIndex){
					isAfterNodes[i][j] = true;
				}else{
					isAfterNodes[i][j] = false;
				}
			}
		}
		return isAfterNodes;
	}
	
	/**
	 * 计算出路过节点的下标
	 * @param index
	 * @param isAfterNodesLeft
	 * @param result
	 * @return
	 */
	private int maxValueIndex(int index,boolean[] isAfterNodesLeft, int[][] result){
		int indexAfter = 0;
		for(int i=0; i<isAfterNodesLeft.length; i++){
			if(isAfterNodesLeft[i]){
				indexAfter = i;
			}
		}
		if(result[index][indexAfter] > result[index][indexAfter+1]){
			return indexAfter;
		}else{
			return indexAfter+1;
		}
	}
	
	/**
	 * 计算出最大值
	 */
	private void calculatedResult(){
		int[][] result = new int[nodes.length][];
		result[nodes.length-1] = new int[nodes[nodes.length-1].length];
		for(int i=0; i<result[nodes.length-1].length; i++){
			result[nodes.length-1][i] = nodes[nodes.length-1][i];
		}
		
		for(int i=nodes.length-2; i>=0; i--){
			result[i] = new int[nodes[i].length];
			for(int j=0; j<result[i].length; j++){
				int temp1 = nodes[i][j] + result[i+1][j];
				int temp2 = nodes[i][j] + result[i+1][j+1];
				if(temp1>temp2){
					result[i][j] = temp1;
				}else{
					result[i][j] = temp2;
				}
			}
		}
		this.setMaxValue(result[0][0]);
		this.setResults(result);
	}

	public int[][] getResults() {
		return results;
	}

	public void setResults(int[][] results) {
		this.results = results;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int[][] getNodes() {
		return nodes;
	}

	public void setNodes(int[][] nodes) {
		this.nodes = nodes;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
		initTower();
		calculatedResult();
		this.repaint();
	}
}
