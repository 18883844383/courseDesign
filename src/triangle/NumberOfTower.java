package triangle;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberOfTower extends JFrame{
	static JLabel jl2 = new JLabel();
	
	public NumberOfTower(){
		JPanel p1 =new JPanel();
		JPanel p2 =new JPanel();
		//JTextArea jta = new JTextArea();
		JLabel jl = new JLabel();
		
		JButton btn = new JButton("确定");
		JTextField jtf = new JTextField(8);
		setLayout(new FlowLayout());
		p1.add(new JLabel("请输入数塔的高度："));
		p1.add(jtf);
		p1.add(btn);
		add(p1);
		p2.setLayout(new FlowLayout());
		p1.add(p2);
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//jf.setLayout(new GridLayout(1,1));
				int n = Integer.parseInt(jtf.getText());
				String s =null;
				int[][] rec = new int[n][n];
				for(int i=0;i<n;i++){
					for(int j=0;j<i;j++){
						rec[i][j]=(int)(Math.random()*10);
						//System.out.print(rec[i][j]+" ");
						//String[][] rect= new 
						//jta.setText(String.valueOf(rec[i][j]));
						s=String.valueOf(rec[i][j])+" ";
						JPanel p3 =new JPanel();
						JLabel jl3 = new JLabel();
						//JLabel jl4 = new JLabel();
						jl3.setText(s);
						p3.add(jl3);
						p2.add(p3);
						
					}
					System.out.println();
					
				}
				//System.out.println("路径所经过的总和最大是："+getRecMaxNum(rec));
				
				JPanel p4 =new JPanel();
				p4.add(jl2);
				p2.add(p4);
				
				jl.setText(String.valueOf(getRecMaxNum(rec)));
				add(jl);
			}
			
		});
		p2.add(jl2);
		p2.add(jl);
		//p2.setLayout(new GridLayout(1,1));
		add(p2);
	}
	public static void main(String[] args){
		NumberOfTower T = new NumberOfTower();
		T.setTitle("数字三角形问题：");
		T.setSize(400,250);
		T.setLocationRelativeTo(null);
		T.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		T.setVisible(true);
	}
	public static int getRecMaxNum(int[][] rec){
		int n = rec.length;
		int[][] value = new int[n][n];//表value[i][j]表示以rec[i][j]作为当前路径的终点所得到的值
		int[][] pre = new int[n][n];/*pre[i][j]表示到达rec[i][j]的数字的上一个数字在定位
		-1表示是rec[i-1][j-1],1表示是rec[i][j].然后以pre[n-1][k]为起点，逆向构造整条路径到达rec[0][0]*/
		
		value[0][0]=pre[0][0];
		/*求路径数字之和最大值：
		 * 1.rec[i][j]为行首即rec[i][0],那么要到达rec[i][0],只能经过rec[i-1][0],则value[i][0]=value[i-1][0]+rec[i][0];
		 * 2.rec[i][j]为行尾即rec[i][i],那么要到达rec[i][i],只能经过rec[i-1][i-1],则value[i][i]=value[i-1][i-1]+rec[i][i];
		 * 3.rec[i][j]为中部元素，此时到达rec[i][j]有两种方式，经过rec[i-1][j-1]或rec[i-1][j],
		 * 则value[i][j]=max{value[i-1][j-1]+rec[i][j],value[i-1][j]+rec[i][j];
		 * */
		for(int i=1;i<n;i++){
			value[i][0]=value[i-1][0]+rec[i][0];
			pre[i][0]=1;
			for(int j=1;j<i;j++){
				if(value[i-1][j]+rec[i][j]<value[i-1][j-1]+rec[i][j]){
					value[i][j]=value[i-1][j-1]+rec[i][j];
					pre[i][j]=-1;
				}
				else{
					value[i][j]=value[i-1][j]+rec[i][j];
					pre[i][j]=1;
				}
			}
			value[i][i]=value[i-1][i-1]+rec[i][i];
			pre[i][i]=-1;
		}
		int k=0;
		for(int j=1;j<n;j++){
			if(value[n-1][k]<value[n-1][j]){
				k=j;
			}
		}
		StringBuffer s = new StringBuffer("");
		for(int i = n-1,j=k;i>=0&&j>=0;){
			s.insert(0,Integer.toString(rec[i][j])+" ");
			if(pre[i][j]==-1){//-1表示是rec[i-1][j-1],1表示是rec[i][j].
				i--;
				j--;
			}
			else{
				i--;
			}
		}
		//System.out.println(s);
		jl2.setText(String.valueOf(s)+" ");
		return value[n-1][k];
	}

}
