package cqut.edu.cn.GigitalTriangle;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import triangle.TowerPanel;

public class ShowTower extends JFrame{
	JLabel towerHighLabel = new JLabel("请输入数塔的高度：");
	private JTextField towerHighInput =new JTextField(25);
	private JButton towerHighButton = new JButton("确定");
	private JButton computButton = new JButton("计算");
	
	private int N;
	private static TowerNode [][] TowerNode;
	private static ArrayList<TowerNode>maxNodeArray = new ArrayList<TowerNode>();
	private static TowerNode maxNode = new TowerNode();
	
	private Random random = new Random();
   
	CreatTower canvas = new CreatTower();
    
    public ShowTower(){
    	JPanel jPanel = new JPanel();
    	jPanel.add(towerHighLabel);
    	jPanel.add(towerHighInput);
    	jPanel.add(towerHighButton);
    	jPanel.add(computButton);
    	this.add(jPanel,BorderLayout.NORTH);
    	this.add(canvas, BorderLayout.CENTER);
    	towerHighButton.addActionListener(new buildTowerBtn());
    	computButton.addActionListener(new computTowerBtn() );
    }
    
    class buildTowerBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String n = towerHighInput.getText();
			
		      try {
		    	    N = Integer.parseInt(n) ;
		    	    if(N<0){
		    	    	JOptionPane.showMessageDialog(null, "请先输入数字！");
		    	    }else{
		    	    	
		    	    	initTower(N);
		    	    	canvas.buildTower(TowerNode,-1);
		    	    }
		    	    
		    	} catch (NumberFormatException err) {
		    		JOptionPane.showMessageDialog(null, "请先输入数字！");
		    		System.out.println("请先输入数字！");
		    	    return ;  
		    	}
			
		}
    	
    }
    class computTowerBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int sum = getMaxSum(0,0);
			isTheBestPath();
			canvas.buildTower(TowerNode,sum);
			
			
		}
    	
    }
   
    public void initTower(int N){
    	 TowerNode = new TowerNode[N][N];
		//初始化数组
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				TowerNode[i][j] = new TowerNode();
				TowerNode[i][j].setValue(0);
				TowerNode[i][j].setIsTheBestPath(false);
			}
		}
		
		for(int i=0;i<N;i++){
			for(int j=0;j<=i;j++){
				TowerNode[i][j].setValue((int) (Math.random()*10));
				TowerNode[i][j].setIsTheBestPath(false);
				
			}
		}
		
	}
    /*
    * 求路径最大值
    * /*求路径数字之和最大值：
	* 在用动态规划考虑数塔问题时可以自顶向下的分析，自底向上的计算。
              从顶点出发时到底向左走还是向右走应取决于是从左走能取到最大值还是从右走能取到最大值，
               只要左右两道路径上的最大值求出来了才能作出决策。
              同样的道理下一层的走向又要取决于再下一层上的最大值是否已经求出才能决策。
              这样一层一层推下去，直到倒数第二层时就非常明了。
             如数字2，只要选择它下面较大值的结点19前进就可以了。
             所以实际求解时，可从底层开始，层层递进，最后得到最大值。
             总结：此题是最为基础的动态规划题目，阶段、状态的划分一目了然。
            而决策的记录，充分体现了动态规划即“记忆化搜索”的本质。
	* */
    public static int getMaxSum(int i,int j)
	{
    	
      if(TowerNode!=null){
    	  if(i==TowerNode.length-1){
    		  System.out.println(TowerNode[i][j].getValue());
    		  return TowerNode[i][j].getValue();
    	  }else{
    		  int left=0,right=0;
    		  left=TowerNode[i][j].getValue()+getMaxSum(i+1,j);
    		  right=TowerNode[i][j].getValue()+getMaxSum(i+1,j+1);
    		  if(left>right){
    			  maxNode = new TowerNode();
    			  System.out.println("left"+(i)+","+j+":"+left+"\n");
    			  maxNode.setRow(i);
    			  maxNode.setColumn(j);
    			  maxNode.setMaxSum(left);
    			  maxNodeArray.add(maxNode);
    			  return left;
    		  }else{
    			  maxNode = new TowerNode();
    			  System.out.println("right"+(i)+","+(j)+":"+right+"\n");
    			  maxNode.setRow(i);
    			  maxNode.setColumn(j);
    			  maxNode.setMaxSum(right);
    			  maxNodeArray.add(maxNode);
    			  return right;
    		  }
    	  }
      }else{
    	  JOptionPane.showMessageDialog(null, "请先输入数字！");
    	  return -1;
      }
	}
    
    /*
     * 判断是否为它的最佳路径
     * */
    public static void isTheBestPath (){
    	TowerNode nodeTemp = new TowerNode();
    	for(int i = 0;i<maxNodeArray.size();i++){
    		System.out.print(maxNodeArray.get(i).getMaxSum()+"\t");
    	}
    	if (maxNodeArray.size()>0) {
    		
    		TowerNode firstMaxNode = new TowerNode();
    		for(int i = 0;i<TowerNode.length-1;i++){
        		nodeTemp = null;
        		for(int j = 0;j<maxNodeArray.size();j++){
                	if (i==maxNodeArray.get(j).getRow()) {
                		if(firstMaxNode == null){
                			if (nodeTemp == null) {
        						nodeTemp = maxNodeArray.get(j);
        					}else{
        						if(nodeTemp.getMaxSum()<maxNodeArray.get(j).getMaxSum()){
        							nodeTemp = maxNodeArray.get(j);
        						}
        					}
                		}else {
							if(maxNodeArray.get(j).getColumn()==firstMaxNode.getColumn()||maxNodeArray.get(j).getColumn()==(firstMaxNode.getColumn()+1)){
								if(nodeTemp == null) {
	        						nodeTemp = maxNodeArray.get(j);
	        					}else{
	        						if(nodeTemp.getMaxSum()<maxNodeArray.get(j).getMaxSum()){
	        							nodeTemp = maxNodeArray.get(j);
	        						}
	        					}
							}
						}
    					
    				}
                }
        		if (nodeTemp!=null) {
    				TowerNode[nodeTemp.getRow()][nodeTemp.getColumn()].setIsTheBestPath(true);
    				firstMaxNode = new TowerNode();
    				firstMaxNode.setRow(nodeTemp.getRow());
    				firstMaxNode.setColumn(nodeTemp.getColumn());
    			}
        		if(i==TowerNode.length-2){
        			if(TowerNode[nodeTemp.getRow()+1][nodeTemp.getColumn()].getValue()>TowerNode[nodeTemp.getRow()+1][nodeTemp.getColumn()+1].getValue()){
        				TowerNode[nodeTemp.getRow()+1][nodeTemp.getColumn()].setIsTheBestPath(true);
        			}else{
        				TowerNode[nodeTemp.getRow()+1][nodeTemp.getColumn()+1].setIsTheBestPath(true);
        			}
        			
        		}
        	}
		}else{
			JOptionPane.showMessageDialog(null, "请先输入数字！");
		}
    	
        
     }
    public static void main(String[]args){
		ShowTower mainFrame = new ShowTower();
	    mainFrame.setTitle("数字三角形问题");
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setSize(1000, 600);
	    mainFrame.setVisible(true);
	    mainFrame.setLocation(100, 60);
	}
}
