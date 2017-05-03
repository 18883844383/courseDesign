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
		// ���뵥λ����
		System.out.println("�����뵥λ������");
		Scanner input = new Scanner(System.in);
		G.n = input.nextInt();
		// ���뵥λ·����
		System.out.printf("�����뵥λ���·������");
		G.e = input.nextInt();

		// ���뵥λ����
		System.out.println("�����뵥λ���ƣ�");
		for (i = 0; i <G.n; i++) {
			System.out.printf("�������%d����λ���ƣ�\n", i);
			G.vexs[i] = input.next();
		}

		for (i = 0; i < G.n; i++) {
			// ��ʼ����ͨ��λ�;����Ƶ��
			for (j = 0; j < G.n; j++) {
				G.adj[i][j] = 0;
				G.dis[i][j] = 0;
				G.f[i] = 0;
			}

			// ������ͨ��������λ
			for (k = 0; k < G.e; k++) {
				System.out.printf("��������ͨ��������λ��(�����ʽ��i j)\n");
				i = input.nextInt();
				j = input.nextInt();

				// ���뵥λ��ľ���
				System.out.printf("��������ͨ��������λ�ľ���:(��ʽ��dis)\n");
				G.dis[i][j] = input.nextInt();
				G.adj[i][j] = 1;
				G.adj[j][i] = 1;
				G.dis[i][j] = G.adj[j][i];

			}
			// ���������λȥ���е����Ƶ��
			for (k = 0; k < G.n; k++) {
				System.out.printf("�������%d����λȥ���е����Ƶ�ʣ�\n", k);
				G.f[k] = input.nextInt();
			}
			for (i = 0; i < G.n; i++) {
				for (j = 0; j < G.n; j++) {
					G.dis[i][j] *= G.f[i];    //�Ծ����Ƶ��֮����ΪȨֵ
					if (G.adj[i][j] == 0 && i != j)
						G.dis[i][j] = INF;   //����Ȩֵ��ȫ����
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
		for (i = 0; i < G.n; i++)         //��ʼ��A[][]��path[][]
			for (j = 0; j < G.n; j++) {   
				A[i][j] = G.dis[i][j];
				path[i][j] = 1;
				count[i] = false;
			}
		for (k = 0; k < G.n; k++) {     //k�������㲽��
			for (i = 0; i < G.n; i++)
				for (j = 0; j < G.n; j++)
					if (A[i][j] > A[i][k] + A[k][k]) {//��i��j��k��һ��·������
						A[i][j] = A[i][k] + A[k][j];
						path[i][j] = k;
					}
		}
		System.out.println("**********ѧУ����ѡַ�������************");

		for (i = 0; i < G.n; i++)
			for (j = 0; j < G.n; j++) {
				if (i != j) {
					System.out.printf("%d"+i+"***"+"%d"+j);
					if (A[i][j] == INF) {
						if (i != j) 
							System.out.printf("������·��\n");
						} else {
							System.out.printf("·������Ϊ��%d" + A[i][j] + "\n");
							System.out.printf("·��Ϊ��%d" + i + "*");
							pre = path[i][j];
							while (pre != 1) {
								System.out.printf("·������Ϊ��%d" + pre + "\n");
								pre = path[pre][j];
							}
							System.out.printf( "%d"+j + "\n");
						}
					}
				}
				//����Ϊ�������Ź���
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
				System.out.printf("���е����Ϊ��" + G.vexs[k] + "\n");
			}

	}
