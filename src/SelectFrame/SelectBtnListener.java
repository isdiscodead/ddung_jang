package SelectFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GraphFrame.GraphFrame;
import LoginFrame.LoginWindow;
import WriteFrame.WriteFrame;

public class SelectBtnListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		String btn = e.getActionCommand(); 

		switch ( btn ) {
		
		case "기록하기":
			System.out.println("기록하기 버튼");
			WriteFrame wf = new WriteFrame(LoginWindow.USERINFO);
			break;

		case "통계 보기":
			try {
				System.out.println("통계 보기 버튼");
				GraphFrame gf = new GraphFrame(LoginWindow.MONEYLIST);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "통계를 만들 정보가 없습니다." ,"ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			break;
		}
	}

}
