package cqut.edu.cn.GigitalTriangle;

public class TowerNode {

	public  int value;//�����ڵ����ֵ
	public  boolean isTheBestPath;//��¼���·��
	public int x;//�ڵ�������
	public int column;//�ڵ�������
	public int maxSum;
	public  int getValue() {
		return value;
	}
	public  void setValue(int value) {
		this.value = value;
	}
	public  boolean getIsTheBestPath() {
		return isTheBestPath;
	}
	public  void setIsTheBestPath(boolean isDye) {
		this.isTheBestPath = isDye;
	}
	public int getRow() {
		return x;
	}
	public void setRow(int row) {
		this.x = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getMaxSum() {
		return maxSum;
	}
	public void setMaxSum(int maxSum) {
		this.maxSum = maxSum;
	}
	
	
}
