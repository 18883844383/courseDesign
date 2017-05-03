package triangle;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PlainChangeAddress extends JPanel{
	int N=4;
//	public int[][] company={{11,9,6},{9,11,4},{6,4,11}};
	
	public PlainChangeAddress(){
		JPanel panel=new JPanel();
		add(panel);
	}
	
	public void paintComponent(Graphics g){
		int[][] local=random(N);
		int[][] addLocal=random(1);
		int[][] resultLocal=addChange(local, addLocal);
		int[][] length=changeToLength(resultLocal);
		int minPath=findThrough(length);
		int len=length.length;
		for(int i=0;i<len;i++){
			g.setColor(Color.BLACK);
			if(i==len-1){
				g.setColor(Color.RED);
			}
			g.drawRect(resultLocal[i][0], resultLocal[i][1], 15, 15);
			g.drawString(i+"", resultLocal[i][0]+5, resultLocal[i][1]+14);
			g.setColor(Color.BLACK);
			for(int j=0;j<len;j++){
			    g.drawLine(resultLocal[i][0]+8, resultLocal[i][1]+15, resultLocal[j][0]+8, resultLocal[j][1]+15);
			}
		}
	}
	
	//在地图中加入可选点
	public int[][] addChange(int[][] local,int[][] addLocal){
		int len=local.length;
		int[][] result=new int[len+1][len+1];
		for(int i=0;i<len+1;i++){
			System.out.println();
			for(int j=0;j<2;j++){
				if(i<len){
					result[i][j]=local[i][j];
				}
				else {
					result[i][j]=addLocal[0][j];
				}
			}
		}
		return result;
	}
	
	//生成一个随机的二维数组列数为2，代表地图坐标
	public int[][] random(int n){
		int[][] local=new int[n][2];
		for(int i=0;i<n;i++){
			System.out.println();
			for(int j=0;j<2;j++){
				local[i][j]=(int) (Math.random()*250+50);
				System.out.print("\t"+local[i][j]);
			}
		}
		return local;
	}
	
	
	//找出最短路径,返回最小的权重和
	public int findThrough(int[][] length){
		int minNum;
		minNum=prime(1,length,N);
		System.out.print("\t"+minNum);
		return minNum;
	}
	
	//将坐标转化成长度数组存储
	public int[][] changeToLength(int[][] local){
		int len=local.length;
		int[][] length=new int[len][len];
		for(int i=0;i<len;i++){
			System.out.println();
			for(int j=0;j<len;j++){
				if(i!=j){
					length[i][j]=(int) Math.sqrt(Math.pow(local[i][0]-local[j][0], 2)+
							Math.pow(local[i][1]-local[j][1], 2));
				}
				else {
					length[i][j]=300;
				}
				System.out.print("\t"+length[i][j]);
			}
		}
		return length;
	}
	
	//计算每一个可选点的最短路径距离中最小的一个
	public int min(int[] minPath){
		int minNum=minPath[0];
		int local=0;
		for(int i=0;i<minPath.length-1;i++){
			if(minNum>minPath[i]){
				local=i;
			}
		}
		return local;
	}
	
	//Prime算法求两点间的最短路径
	public int prime(int cur,int[][] graph,int n){
		int index = 0,sum=0;
		boolean[] visit = new boolean[n];
		meset(visit,false,n);
		visit[cur]=true;
		int[] dist = new int[n];
		for(int i=0;i<n;i++){
			dist[i]=graph[cur][i];
		}
		
		for(int i=1;i<n;i++){
			int mincost=300;
			for(int j=0;j<n;j++){
				if(!visit[j]&&dist[j]<mincost){
					mincost=dist[j];
					index=j;
				}
			}
			visit[index]=true;
			sum+=mincost;
			
			for(int j=0;j<visit.length;j++){
				if(!visit[j]&&dist[j]>graph[index][j]){
					dist[j]=graph[index][j];
				}
			}
		}
		return sum;
	}
	
	private void meset(boolean[] visit, boolean b, int length) {
		for(int i=0;i<visit.length;i++){
			visit[i]=b;
		}
	}
}
