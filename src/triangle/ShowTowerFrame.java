package triangle;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ShowTowerFrame extends JFrame{
	private JTextField layerText;
	private JButton layerButton;
	private TowerPanel panel;
	private JLabel resultLabel;
	public ShowTowerFrame() {
		// TODO Auto-generated constructor stub
		setUI();
	}
	
	private void setUI(){
		layerText = new JTextField(10);
		layerText.setText("9");
		JLabel layerLabel = new JLabel("请输入数塔的层数：");
		layerButton = new JButton("确定");
		JPanel promptPanel = new JPanel();
		promptPanel.add(layerLabel);
		promptPanel.add(layerText);
		promptPanel.add(layerButton);
		
		panel = new TowerPanel(9);
		
		JLabel formatLabel = new JLabel("                       ");
		int result = panel.getMaxValue();
		resultLabel = new JLabel("最大路径数字总和为：" + result);
		promptPanel.add(formatLabel);
		promptPanel.add(resultLabel);
		
		add(promptPanel,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		
		layerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String layerString = layerText.getText().trim();
				try{
					int layer = Integer.parseInt(layerString);
					panel.setLayer(layer);
					int result = panel.getMaxValue();
					resultLabel.setText("最大路径数字总和为：" + result);
				}catch(Exception exception){
				}
			}
		});
	}
	
	public static void main(String[] args){
		ShowTowerFrame frame= new ShowTowerFrame();
		frame.setTitle("tower of number");
		frame.setSize(900,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
