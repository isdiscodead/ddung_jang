package SelectFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import UI.DDJangFrame;

public class SelectFrame {
	public SelectFrame() {
		
		DDJangFrame fr = new DDJangFrame( new ImageIcon("choose.png") );

		fr.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// 메뉴 닫으면 전체 종료
		
		JButton toWriteBtn = DDJangFrame.newBtnIcon("기록하기", new ImageIcon("REcordIcon.png") );
		toWriteBtn.addActionListener( new SelectBtnListener() );
		toWriteBtn.setBounds(80, 325, 200, 100);
		fr.add(toWriteBtn);
		
		
		JButton toGraphBtn = DDJangFrame.newBtnIcon("통계 보기", new ImageIcon("viewGrIcon.png") );
		toGraphBtn.setBounds(80, 650, 200, 100);
		toGraphBtn.addActionListener( new SelectBtnListener() );
		fr.add(toGraphBtn);
		
	}

}
