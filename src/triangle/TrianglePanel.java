package triangle;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TrianglePanel extends JFrame{
	static int[][] D3={{7,0,0,0,0},{3,8,0,0,0},{8,1,0,0,0},{2,7,4,4,0},{4,5,2,6,5}};
	private JButton button1 = new JButton("确定");
	private JTextField textField1 = new JTextField(10);
	private PlainTriangle2 plain=new PlainTriangle2();
	private JLabel label1 = new JLabel("请输入数塔的层数：");
	private JLabel label2 = new JLabel("选择的最长路径总和：");
	//画出数字三角形吊塔
	public TrianglePanel(){
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1,5));
		p1.add(label1);
		p1.add(textField1);
		p1.add(label2);
		p1.add(button1);
		add(p1,BorderLayout.SOUTH);
		add(plain,BorderLayout.CENTER);
		
        button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String label1String = textField1.getText().trim();
				int high = Integer.parseInt(label1String);
				System.out.print(high);
				plain.setN(high);
				plain.setD3();
				int sum=plain.getMaxValue();
				label2.setText("选择的最长路径总和："+sum);
			}
		});
	}
	
	public static void main(String[] args){
		TrianglePanel arraylistView = new TrianglePanel();
		arraylistView.setTitle("MyArrayList");
		arraylistView.setSize(900, 500);
		arraylistView.setLocationRelativeTo(null); // Center the frame
		arraylistView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		arraylistView.setVisible(true);
	}
}
