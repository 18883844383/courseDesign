package triangle;

import java.util.Scanner;

public class Triangle {
	static int[][] D3={{7,0,0,0,0},{3,8,0,0,0},{8,1,0,0,0},{2,7,4,4,0},{4,5,2,6,5}};
	public static void main(String[] args){
		System.out.print("请输入N:");
		Scanner input=new Scanner(System.in);
		int N=input.nextInt();
		
		int[][] D=Radom(N);
		System.out.println();
		System.out.print(Sum(D3));
	}
	
	//数字三角形
	static int Sum(int[][] D2){
		int N=D2.length;
		int[][] D=new int[N][N];
		D[0][0]=D2[0][0];
		for(int i=1;i<D.length;i++){
			for(int j=1;j<=i;j++){
				D[i][0]=D2[i][0]+D[i-1][0];
				D[i][j]=max(D2[i][j]+D[i-1][j-1], D2[i][j]+D[i-1][j]);
			}
		}
		
		for(int i=0;i<D.length;i++){
			System.out.println();
			for(int j=0;j<D.length;j++){
				System.out.print("\t"+D[i][j]);
			}
		}
		return funMax(D);
	}
	
	//比较最大值
	static int max(int a,int b){
		if(a>b)
			return a;
		else
		    return b;
		
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
	
				System.out.print("\t"+D3[i][j]);
			}
		}
		return D2;
	}
	
    //找出最大数
	static int funMax(int[][] D){
		int i=D.length-1;
		int maxNum = D[i][0];
		for(int j=0;j<D.length-1;j++){
			maxNum=max(maxNum,D[i][j+1]);
		}
		return maxNum;
	}
}
