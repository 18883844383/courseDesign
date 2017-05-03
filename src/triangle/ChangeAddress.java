package triangle;

import java.util.Scanner;

public class ChangeAddress {
	int N=5;
	public int[][] company=new int[N][N];
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.print("请输入连通图节点数n:");
		int n=input.nextInt();
	}
	
	
	
	//生成一个随机的二维数组，代表地图
	public int[][] random(int n){
		int[][] graph=new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i<j){
					graph[i][j]=(int)(Math.random()*10);
					if(graph[i][j]==0){
						graph[i][j]=11;
					}
				}
				else if(i==j){
					graph[i][j]=11;
				}
				else{
					graph[i][j]=graph[j][i];
				}
			}
		}
		return graph;
	}
	
	//Floyd算法求两点间的最短路径
	public void floyd(){
		
	}
}
