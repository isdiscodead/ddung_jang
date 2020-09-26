package GraphFrame;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Info.Money;
import UI.DDJangFrame;

public class GraphFrame extends JFrame {
	
	//ArrayList<Money> moneyInfo;
	
	public GraphFrame( ArrayList<Money> moneyInfo) {
		
		//this.moneyInfo = moneyInfo;
		
		// 배경화면이 들어간 프레임 생성
		DDJangFrame graphFrame = new DDJangFrame( new ImageIcon("test.png") ); 
		
		// 나머지 컨텐츠들을 담을 패널
		JPanel pane = new JPanel();
		pane.setBackground(Color.WHITE);
		pane.setLayout(null);
		pane.setBounds(0, 200, 650, 900);
		pane.setVisible(true);
		
	    // choice 추가
		GraphPanel graphPanel = new GraphPanel(moneyInfo);
	    Choice selectMonth = graphPanel.selectMonth();
	    pane.add(selectMonth);
	    
		// 그래프 추가
		graphPanel.graphInfo(moneyInfo);
		graphPanel.setBounds(0, 0, 350, 500);
		pane.add(graphPanel);
		
	    // 버튼 추가
	    JButton closeBtn = DDJangFrame.newBtnIcon("닫기", new ImageIcon("closeIcon.png"));
	    closeBtn.setBounds(225, 700, 200, 100);
	    closeBtn.setVisible(true);
	    closeBtn.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				graphFrame.dispose();
				
			}
		});
	    
	    graphFrame.add(closeBtn);
	    graphFrame.add(pane);
	    
	}
	
}
