package cqut.edu.cn.GigitalTriangle;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CreatTower extends JPanel{
	private TowerNode [][] TowerNode;
	
	private int sum;
	public void buildTower(TowerNode [][] TowerNode,int sum){
		
		this.TowerNode = TowerNode;
		this.sum = sum;
		repaint();
		
	}
	
	
	protected void paintComponent(Graphics g) {
		if(TowerNode==null){
			return;
		}
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int TowerNodeWidth = 35;// 矩形宽度
		int TowerNodeHeight = 20;// 矩形宽度
		for(int i = 0;i<TowerNode.length;i++){
			for(int j= 0;j<TowerNode[i].length;j++){
				if(TowerNode[i][j].getValue()!=0){
					int x = width/2-TowerNodeWidth/2-(i+1)*((TowerNodeWidth+20)/2)+j*(TowerNodeWidth+20);
					int y = 50+i*(TowerNodeHeight+20);
					
					if (TowerNode[i][j].getIsTheBestPath()) {
						g.setColor(Color.red);
						g.drawRect(x,y , TowerNodeWidth, TowerNodeHeight);
						g.drawString(Integer.toString(TowerNode[i][j].getValue()), (x+10),(y+13));
					}else{
						g.setColor(Color.black);
						g.drawRect(x,y , TowerNodeWidth, TowerNodeHeight);
						g.drawString(Integer.toString(TowerNode[i][j].getValue()), (x+10),(y+13));
					}
					if(i<TowerNode.length-1){
						g.setColor(Color.black);
						int x1 = x + (TowerNodeWidth/2);
						int y1 = y+TowerNodeHeight;
						int x2 = x1 -20;
						int y2 = y1 +20;
						g.drawLine(x1, y1, x2, y2);
						int x11 = x + (TowerNodeWidth/2);
						int y11 = y+TowerNodeHeight;
						int x22 = x11+20;
						int y22 = y11 +20;
						g.drawLine(x11, y11, x22, y22);
					}
					
				}
			}
			
		}
		
		if (sum!=-1) {
			this.setBackground(new Color(99,00,180));
			g.setColor(Color.red);
			g.drawString("路径经过的数字总和最大为："+Integer.toString(sum), 350,450);
		}
	}

}
