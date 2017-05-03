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

@SuppressWarnings("serial")
public class AddressPanel extends JFrame {
	private PlainChangeAddress plain=new PlainChangeAddress();
	private JButton button1 = new JButton("确定");
	private JTextField textField1 = new JTextField(10);
	private JLabel label1 = new JLabel("请输入单位数：");
	
	public AddressPanel(){
		JPanel p1=new JPanel();
	//	p1.setLayout(new GridLayout(1,3));
		p1.add(label1);
		p1.add(textField1);
		p1.add(button1);
		add(p1,BorderLayout.EAST);
		add(plain,BorderLayout.CENTER);
		
	    button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
	    });
	}
	
	
	public static void main(String[] args){
		AddressPanel addressPanel = new AddressPanel();
		addressPanel.setTitle("MyArrayList");
		addressPanel.setSize(900, 500);
		addressPanel.setLocationRelativeTo(null); // Center the frame
		addressPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addressPanel.setVisible(true);
	}
}
