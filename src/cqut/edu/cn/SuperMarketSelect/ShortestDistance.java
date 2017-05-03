package cqut.edu.cn.SuperMarketSelect;

import java.util.Scanner;

public class ShortestDistance {
	public static final int MAXVEX = 100;
	public static final int INF = 72363;

	public static void main(String[] args) {
		SuperMarket G = creatSuperMarket();
		Floyed(G);
	}

	public static SuperMarket creatSuperMarket() {
		SuperMarket G = new SuperMarket();
		final int INF = 72363;
		int i, j, k;
		// 输入单位个数
		System.out.println("请输入单位个数：");
		Scanner input = new Scanner(System.in);
		G.n = input.nextInt();
		// 输入单位路径数
		System.out.printf("请输入单位间的路径数：");
		G.e = input.nextInt();

		// 输入单位名称
		System.out.println("请输入单位名称：");
		for (i = 0; i <G.n; i++) {
			System.out.printf("请输入第%d个单位名称：\n", i);
			G.vexs[i] = input.next();
		}

		for (i = 0; i < G.n; i++) {
			// 初始化相通单位和距离和频率
			for (j = 0; j < G.n; j++) {
				G.adj[i][j] = 0;
				G.dis[i][j] = 0;
				G.f[i] = 0;
			}

			// 输入相通的两个单位
			for (k = 0; k < G.e; k++) {
				System.out.printf("请输入相通的两个单位：(输入格式：i j)\n");
				i = input.nextInt();
				j = input.nextInt();

				// 输入单位间的距离
				System.out.printf("请输入相通的两个单位的距离:(格式：dis)\n");
				G.dis[i][j] = input.nextInt();
				G.adj[i][j] = 1;
				G.adj[j][i] = 1;
				G.dis[i][j] = G.adj[j][i];

			}
			// 输入各个单位去超市的相对频率
			for (k = 0; k < G.n; k++) {
				System.out.printf("请输入第%d个单位去超市的相对频率：\n", k);
				G.f[k] = input.nextInt();
			}
			for (i = 0; i < G.n; i++) {
				for (j = 0; j < G.n; j++) {
					G.dis[i][j] *= G.f[i];    //以距离和频率之积作为权值
					if (G.adj[i][j] == 0 && i != j)
						G.dis[i][j] = INF;   //最终权值完全无向
				}
			}
		}
		return G;
	}

	public static void Floyed(SuperMarket G) {
		int[][] A = new int[MAXVEX][MAXVEX];
		int[][] path = new int[MAXVEX][MAXVEX];
		int i, j, k, pre;
		boolean[] count = new boolean[MAXVEX];
		for (i = 0; i < G.n; i++)         //初始化A[][]和path[][]
			for (j = 0; j < G.n; j++) {   
				A[i][j] = G.dis[i][j];
				path[i][j] = 1;
				count[i] = false;
			}
		for (k = 0; k < G.n; k++) {     //k代表运算步骤
			for (i = 0; i < G.n; i++)
				for (j = 0; j < G.n; j++)
					if (A[i][j] > A[i][k] + A[k][k]) {//从i经j到k的一条路径更短
						A[i][j] = A[i][k] + A[k][j];
						path[i][j] = k;
					}
		}
		System.out.println("**********学校超市选址求解如下************");

		for (i = 0; i < G.n; i++)
			for (j = 0; j < G.n; j++) {
				if (i != j) {
					System.out.printf("%d"+i+"***"+"%d"+j);
					if (A[i][j] == INF) {
						if (i != j) 
							System.out.printf("不存在路径\n");
						} else {
							System.out.printf("路径长度为：%d" + A[i][j] + "\n");
							System.out.printf("路径为：%d" + i + "*");
							pre = path[i][j];
							while (pre != 1) {
								System.out.printf("路径长度为：%d" + pre + "\n");
								pre = path[pre][j];
							}
							System.out.printf( "%d"+j + "\n");
						}
					}
				}
				//以下为总体最优过程
				for (i = 0; i < G.n; i++)
					for (j = 0; j < G.n; j++) {
						if (A[i][j] == INF)
							count[i] = false;
						else
							count[i] = true;
					}
				for (i = 0; i < G.n; i++)
					if (true) {
						for (j = 0; j < G.n; j++)
							if (i != j)
								A[i][i] += A[j][i];
					}
				k = 0;
				for (i = 0; i < G.n; i++) {
					if (true)
						if (A[k][k] > A[i][i])
							k = i;
				}
				System.out.printf("超市的最佳为：" + G.vexs[k] + "\n");
			}

	}
