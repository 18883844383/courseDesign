package arithmetic;

import java.util.*;

public class Arithmetic {

	public Map map = new Map();
	public List<Vertex> cost = new ArrayList<Vertex>();
	public List<Vertex> parent = new ArrayList<Vertex>();
	public int[] pdex = new int[30];
	public float length = 0;

	public Arithmetic() {
		this.buildTestMap();
	}

	public Arithmetic(Map map) {

		this.map = map;
	}

	public void buildTestMap() {

		String name[] = { "a", "b", "c", "d" };
		float freq[] = { 1.1f, 1.4f, 0.9f, 1.8f };
		float length[][] = { { 0, 1.0f, 3.0f, -1 }, { 7.0f, 0, -1, 2.0f },
				{ 4.0f, -1, 0, 5.0f }, { -1, 3.0f, -1, 0 } };
		map = new Map(name, freq, length);
	}

	// 两点的最佳路径
	/*取得最优路径算法思想：
	 * Floyd算法利用动态规划思想，通过把问题分解为子问题来解决任意两点见的最短路径问题
	 * 。设G=(V, E, w)是一个带权有向图，其边V={v1, v2, …, vn}。对于k≤n，考虑其结点V的一个子集。
	 * 对于V中任何两个结点vi、vj，考虑从vi到vj的中间结点都在vk中的所有路径，
	 * 设是其中最短的，并设的路径长度为。 如果结点vk不在从vi到vj的最短路径上，
	 * 则；反之则可以把分为两段，其中一段从vi到vk，另一段从vk到vj，这样便得到表达式
	 * 
	 * 第一步，让所有路径加上中间顶点1，取A[i][j]与A[i][1]+A[1][j]中较小的值作A[i][j]的新值，
	 * 完成后得到A(1),如此进行下去，当第k步完成后，Ak）[i][j]表示从i到就且路径上的中间顶点的路径的序号
	 * 小于或等于k的最短路径长度。当第n-1步完成后，得到A（n-1），A（n-1）即所求结果。A（n-1）[i][j]表示
	 * 从i到j且路径上的中点顶点的序号小于或等于n-1的最短路径长度，即A(n-1)[i][j]表示从i到j的最短路径长度。
	 * */
	public int[] getOptimalPath(int x, int y) {

		List<Edge> path = new ArrayList<Edge>();
		// 初始化cost、parent集
		cost.add(map.vertex.get(x));
		for (int i = 0; i < map.vertex.size(); i++) {
			parent.add(map.vertex.get(i));
		}
		parent.remove(map.vertex.get(x));
		// 初始化标记
		ArrayList<Edge> len = new ArrayList<Edge>();
		for (int i = 0; i < map.edge.get(x).size(); i++){
			len.add(map.edge.get(x).get(i));
		}
		// 循环更新标记
		Edge min = new Edge(-1, -1, -1);
		while (min.arrive != y) {
			min = getMinLength(len);
			path.add(min);
			cost.add(map.vertex.get(min.arrive));
			parent.remove(map.vertex.get(min.arrive));
			len.remove(min);
			for (int j = 0; j < map.edge.get(min.arrive).size(); j++) {
				if (parent.contains(map.vertex.get(map.edge.get(min.arrive)
						.get(j).arrive))) {
					len.add(map.edge.get(min.arrive).get(j));
				}
			}
		}
	/*
		 * 弗洛伊德（Floyd）算法过程：
                    １、用D[v][w]记录每一对顶点的最短距离。 
                    ２、依次扫描每一个点，并以其为基点再遍历所有每一对顶点D[][]的值，看看是否可用过该基点让这对顶点间的距离更小。 
                       算法理解： 
                    最短距离有三种情况： 
                    １、两点的直达距离最短。
                     ２、两点间只通过一个中间点而距离最短。
                     ３、两点间用通过两各以上的顶点而距离最短。
               对于第一种情况：在初始化的时候就已经找出来了且以后也不会更改到。
               对于第二种情况：弗洛伊德算法的基本操作就是对于每一对顶点，
               遍历所有其它顶点，看看可否通过这一个顶点让这对顶点距离更短，也就是遍历了图中所有的三角形（
               算法中对同一个三角形扫描了九次，原则上只用扫描三次即可，但要加入判断，效率更低）。
               对于第三种情况：如五边形，可先找一点（比如x，使<v,u>=2），就变成了四边形问题，再找一点
               （比如y,使<u,w>=2），可变成三角形问题了（v,u,w），也就变成第二种情况了，由此对于n边形也可以一步步转
 成四边形三角形问题。（这里面不用担心哪个点要先找哪个点要后找，因为找了任一个点都可以使其变成（n－1）边形的问题）

		 * */
		Edge cur = null;
		int j = 0;

		for (int i = path.size() - 1; i >= 0; i--) {
			if (i != 0) {
				if (cur == null) {
					if (path.get(i).getOwn() == path.get(i - 1).getArrive()) {
						pdex[j++] = path.get(i).getArrive();
						length = length + path.get(i).getLength();
						cur = null;
					} else {
						cur = path.get(i);
					}
				} else {
					if (cur.getOwn() == path.get(i - 1).getArrive()) {
						pdex[j++] = cur.arrive;
						length = length + cur.getLength();
						cur = null;
					}
				}
			} else {
				if (cur == null) {
					pdex[j++] = path.get(i).getArrive();
					pdex[j++] = path.get(i).getOwn();
					length = length + path.get(i).getLength();
				} else {
					pdex[j++] = cur.getArrive();
					pdex[j++] = cur.getOwn();
					length = length + cur.getLength();
				}
			}
		}

		int[] result = new int[j];
		for (int i = 0, k = j - 1; i < j; i++, k--) {
			result[k] = pdex[i];
		}
		return result;
	}

	// 离 Cost 最近的相邻节点
	private Edge getMinLength(List<Edge> len) {

		Edge edge = len.get(0);
		float minlen = len.get(0).getLength();
		for (int i = 1; i < len.size(); i++) {
			if (minlen > len.get(i).getLength()) {
				minlen = len.get(i).getLength();
				edge = len.get(i);
			}
		}
		return edge;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public static void main(String args[]) {

		Arithmetic ari = new Arithmetic();

		int[] pa0 = ari.getOptimalPath(1, 0);
		for (int i = 0; i < pa0.length; i++) {
			System.out.print(pa0[i] + " ");
		}
		System.out.println("\n" + ari.getLength());
		ari.length = 0;

		for (int i = 0; i < ari.map.vertex.size(); i++) {
			for (int j = 0; j < ari.map.vertex.size(); j++) {
				if (i != j) {
					int[] pa = ari.getOptimalPath(j, i);
					float len = ari.getLength();
					ari.setLength(0);
					System.out.println(j + "到" + i + "：");
					for (int d = 0; d < pa.length; d++) {
						System.out.print(pa[d] + " ");
					}
					System.out.println("\n" + len);
				}
			}
		}

	}
}
