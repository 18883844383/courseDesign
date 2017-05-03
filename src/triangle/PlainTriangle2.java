package triangle;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlainTriangle2 extends JPanel{
	static int N;
	//static int[][] D3={{7,0,0,0,0},{3,8,0,0,0},{8,1,0,0,0},{2,7,4,4,0},{4,5,2,6,5}};
	static int[][] D3=new int[N][N];
	public PlainTriangle2(){
		JPanel panel=new JPanel();
		add(panel);
	}

	public static int getN() {
		return N;
	}

	public  void setN(int n) {
		N = Radom(n).length;
	}

	public static int[][] getD3() {
		return D3;
	}

	public  void setD3() {
		D3 = Radom(N);
		this.repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		int Ldistan=50;
		boolean[][] isPath=isThrough();
		for(int i=0;i<N;i++){
			System.out.println();
			for(int j=0;j<N;j++){
				System.out.print("\t"+isPath[i][j]);
				if(j<=i){
					g.setColor(Color.BLACK);
					if(isPath[i][j]){
						g.setColor(Color.RED);
					}
					g.drawRect(400-i*Ldistan/2+j*Ldistan, 5+i*Ldistan, 25, 25);
					g.drawString(D3[i][j]+"",410-i*Ldistan/2+j*Ldistan,25+i*Ldistan);

					if (i<D3.length-1){
						g.drawLine(410-i*Ldistan/2+j*Ldistan, 25+i*Ldistan, 390-i*Ldistan/2+(j+1)*Ldistan, 0+(i+1)*Ldistan);
						g.drawLine(410-i*Ldistan/2+j*Ldistan, 25+i*Ldistan, 435-i*Ldistan/2+(j-1)*Ldistan, 0+(i+1)*Ldistan);
					}

				}
			}
		}
		
	}
	/**
	 * 标示出路过的节点
	 */
	public boolean[][] isThrough(){
		int[] Jl=new int[N];
		boolean[][] isPath=new boolean[N][N];
		for(int i=0;i<N;i++){
			Jl=Sum(D3);
			isPath[i][Jl[i]]=true;
		}
		return isPath;
	}
	
	
	//数字三角形
	static int[] Sum(int[][] D2){
		int N=D2.length;
		int[] Jl=new int[N];
		int[][] D=new int[N][N];
		D[0][0]=D2[0][0];
		for(int i=1;i<D.length;i++){
			for(int j=1;j<=i;j++){
				D[i][0]=D2[i][0]+D[i-1][0];
				D[i][j]=max(D2[i][j]+D[i-1][j-1], D2[i][j]+D[i-1][j]);
			}
		}
		int local=funMax(D, N-1);
		Jl[N-1]=local;
		for(int i=N-1;i>0;i--){
			if(local>0){
				if(D[i-1][local]>D[i-1][local-1]){
					Jl[i-1]=local;
				}
				else {
					Jl[i-1]=local-1;
					local=local-1;
				}
			}
		}
		return Jl;
		
	}
	
	//根据输入的N行数生成三角形数组
	static int[][] Radom(int N){
		int[][] D2=new int[N][N];
		for(int i=0;i<N;i++){
			System.out.println();
			for(int j=0;j<N;j++){
				if(j<=i)
					D2[i][j]=(int) (Math.random()*10);
				else
				    D2[i][j]=0;
			}
		}
		return D2;
	}
	
    //找出最后一行最大数的坐标
	static int funMax(int[][] D,int i){
		int local=0;
		int maxNum = D[i][0];
		for(int j=0;j<D.length-1;j++){
			if(max(maxNum,D[i][j+1])!=maxNum){
				maxNum=D[i][j+1];
				local=j+1;
			}
		}
		System.out.println(maxNum);
		return local;
	}
	
	//比较最大值
	static int max(int a,int b){
		if(a>b)
			return a;
		else
		    return b;
	}
	
	public int getMaxValue(){
		int[] Jl=new int[N];
		int sum=0;
		for(int i=0;i<N;i++){
			Jl=Sum(D3);
			sum=sum+D3[i][Jl[i]];
			
//			System.out.print(i+","+j+"\t");
		}
		return sum;
	}
}

