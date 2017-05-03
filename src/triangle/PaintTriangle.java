package triangle;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PaintTriangle extends JPanel{
	static int[][] D3={{7,0,0,0,0},{3,8,0,0,0},{8,1,0,0,0},{2,7,4,4,0},{4,5,2,6,5}};
	public PaintTriangle(){
		JPanel panel=new JPanel();
		add(panel);
	}

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		for(int i=0;i<D3.length;i++){
			for(int j=0;j<D3.length;j++){
				if(i<=j)
					g.drawString(D3[i][j]+"",450+i*30,20+j*30);
			}
		}
		
	}
}

