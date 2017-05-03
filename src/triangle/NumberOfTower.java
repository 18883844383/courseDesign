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
		
		JButton btn = new JButton("ȷ��");
		JTextField jtf = new JTextField(8);
		setLayout(new FlowLayout());
		p1.add(new JLabel("�����������ĸ߶ȣ�"));
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
				//System.out.println("·�����������ܺ�����ǣ�"+getRecMaxNum(rec));
				
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
		T.setTitle("�������������⣺");
		T.setSize(400,250);
		T.setLocationRelativeTo(null);
		T.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		T.setVisible(true);
	}
	public static int getRecMaxNum(int[][] rec){
		int n = rec.length;
		int[][] value = new int[n][n];//��value[i][j]��ʾ��rec[i][j]��Ϊ��ǰ·�����յ����õ���ֵ
		int[][] pre = new int[n][n];/*pre[i][j]��ʾ����rec[i][j]�����ֵ���һ�������ڶ�λ
		-1��ʾ��rec[i-1][j-1],1��ʾ��rec[i][j].Ȼ����pre[n-1][k]Ϊ��㣬����������·������rec[0][0]*/
		
		value[0][0]=pre[0][0];
		/*��·������֮�����ֵ��
		 * 1.rec[i][j]Ϊ���׼�rec[i][0],��ôҪ����rec[i][0],ֻ�ܾ���rec[i-1][0],��value[i][0]=value[i-1][0]+rec[i][0];
		 * 2.rec[i][j]Ϊ��β��rec[i][i],��ôҪ����rec[i][i],ֻ�ܾ���rec[i-1][i-1],��value[i][i]=value[i-1][i-1]+rec[i][i];
		 * 3.rec[i][j]Ϊ�в�Ԫ�أ���ʱ����rec[i][j]�����ַ�ʽ������rec[i-1][j-1]��rec[i-1][j],
		 * ��value[i][j]=max{value[i-1][j-1]+rec[i][j],value[i-1][j]+rec[i][j];
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
			if(pre[i][j]==-1){//-1��ʾ��rec[i-1][j-1],1��ʾ��rec[i][j].
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
