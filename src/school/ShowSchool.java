package school;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowSchool extends JFrame {

	private SchoolPanel school;
	private JLabel resultLabel;
	
	public ShowSchool() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("NAME", "ʳ��");
		item.put("X", "75");
		item.put("Y", "50");
		item.put("isSupermarket", "false");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("NAME", "һ��");
		item.put("X", "50");
		item.put("Y", "210");
		item.put("isSupermarket", "false");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("NAME", "����");
		item.put("X", "75");
		item.put("Y", "275");
		item.put("isSupermarket", "false");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("NAME", "��ѡ��1");
		item.put("X", "400");
		item.put("Y", "245");
		item.put("isSupermarket", "true");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("NAME", "��ѡ��2");
		item.put("X", "450");
		item.put("Y", "100");
		item.put("isSupermarket", "true");
		list.add(item);

		item = new HashMap<String, Object>();
		item.put("NAME", "����");
		item.put("X", "700");
		item.put("Y", "100");
		item.put("isSupermarket", "false");
		list.add(item);

		JLabel lable = new JLabel("ѧУ����ͼ��");
		JPanel panel1 = new JPanel();
		panel1.add(lable);

		school = new SchoolPanel(list);

		JPanel panel2 = new JPanel();
		JButton button = new JButton("�ҳ����е����λ��");
		JLabel formetLabel = new JLabel("            ");
		resultLabel = new JLabel("");
		panel2.add(button);
		panel2.add(formetLabel);
		panel2.add(resultLabel);

		add(panel1, BorderLayout.NORTH);
		add(school, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<Building> list = school.getBuilds();
				int[][] path = school.getPath();
				List<Integer> market = school.getSupermarket();
				int index = 0;
				int maxWeight = 0;
				
				// ����һ������
				for(int i=0; i<market.size(); i++){
					String name = "����ѡ�����" + (i+1);
					AnotherFrame anotherFrame = new AnotherFrame(list,path,market.get(i),name);
					if(i==0){
						maxWeight = anotherFrame.getPanel().getFinalWeight();
						index = 0;
					}else{
						int currentWeight = anotherFrame.getPanel().getFinalWeight();
						if(maxWeight < currentWeight){
							maxWeight = currentWeight;
							index = i;
						}
					}
				}
				String resultString = "���λ��Ϊ��" + "�����" + (index+1);
				resultLabel.setText(resultString);
			}
		});
	}

	public static void main(String[] args) {
		ShowSchool frame = new ShowSchool();
		frame.setTitle("school");
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

// ��һ������
class AnotherFrame extends JFrame {
	private SelectPlacePanel panel;
	
	public AnotherFrame(List<Building> builds, int[][] path, int supermarket,String name) {
		init(builds,path,supermarket,name);
	}
	
	private void init(List<Building> builds, int[][] path, int supermarket,String name){
		panel = new SelectPlacePanel(builds, path, supermarket);
		add(panel,BorderLayout.CENTER);
		setTitle(name);
		setSize(900, 500);
		setVisible(true);
	}

	public SelectPlacePanel getPanel() {
		return panel;
	}

	public void setPanel(SelectPlacePanel panel) {
		this.panel = panel;
	}
}
