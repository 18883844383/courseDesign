package cqut.edu.cn.SuperMarketSelect;

public class SuperMarket {
	public static final int MAXVEX =100;
	String[] vexs= new String[MAXVEX];//��λ����
	int[][] adj =new int[MAXVEX][MAXVEX];//��λ֮����ͨ�����
	int[][] dis =new int[MAXVEX][MAXVEX];//��λ��ľ���
	int[] f = new int[MAXVEX];//����λȥ���е�Ƶ��
	int n,e;//�������ͱ���
	
	
	public String[] getVexs() {
		return vexs;
	}
	public void setVexs(String[] vexs) {
		this.vexs = vexs;
	}
	public int[][] getAdj() {
		return adj;
	}
	public void setAdj(int[][] adj) {
		this.adj = adj;
	}
	public int[][] getDis() {
		return dis;
	}
	public void setDis(int[][] dis) {
		this.dis = dis;
	}
	public int[] getF() {
		return f;
	}
	public void setF(int[] f) {
		this.f = f;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
	
}
