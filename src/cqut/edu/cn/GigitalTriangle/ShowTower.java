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
	JLabel towerHighLabel = new JLabel("�����������ĸ߶ȣ�");
	private JTextField towerHighInput =new JTextField(25);
	private JButton towerHighButton = new JButton("ȷ��");
	private JButton computButton = new JButton("����");
	
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
		    	    	JOptionPane.showMessageDialog(null, "�����������֣�");
		    	    }else{
		    	    	
		    	    	initTower(N);
		    	    	canvas.buildTower(TowerNode,-1);
		    	    }
		    	    
		    	} catch (NumberFormatException err) {
		    		JOptionPane.showMessageDialog(null, "�����������֣�");
		    		System.out.println("�����������֣�");
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
		//��ʼ������
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
    * ��·�����ֵ
    * /*��·������֮�����ֵ��
	* ���ö�̬�滮������������ʱ�����Զ����µķ������Ե����ϵļ��㡣
              �Ӷ������ʱ���������߻���������Ӧȡ�����Ǵ�������ȡ�����ֵ���Ǵ�������ȡ�����ֵ��
               ֻҪ��������·���ϵ����ֵ������˲����������ߡ�
              ͬ���ĵ�����һ���������Ҫȡ��������һ���ϵ����ֵ�Ƿ��Ѿ�������ܾ��ߡ�
              ����һ��һ������ȥ��ֱ�������ڶ���ʱ�ͷǳ����ˡ�
             ������2��ֻҪѡ��������ϴ�ֵ�Ľ��19ǰ���Ϳ����ˡ�
             ����ʵ�����ʱ���ɴӵײ㿪ʼ�����ݽ������õ����ֵ��
             �ܽ᣺��������Ϊ�����Ķ�̬�滮��Ŀ���׶Ρ�״̬�Ļ���һĿ��Ȼ��
            �����ߵļ�¼����������˶�̬�滮�������仯�������ı��ʡ�
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
    	  JOptionPane.showMessageDialog(null, "�����������֣�");
    	  return -1;
      }
	}
    
    /*
     * �ж��Ƿ�Ϊ�������·��
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
			JOptionPane.showMessageDialog(null, "�����������֣�");
		}
    	
        
     }
    public static void main(String[]args){
		ShowTower mainFrame = new ShowTower();
	    mainFrame.setTitle("��������������");
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setSize(1000, 600);
	    mainFrame.setVisible(true);
	    mainFrame.setLocation(100, 60);
	}
}
