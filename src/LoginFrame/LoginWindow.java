package LoginFrame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Info.Money;
import Info.UserInfo;
import SelectFrame.SelectFrame;
import UI.DDJangFrame;

public class LoginWindow {
	public static UserInfo USERINFO = new UserInfo();
	public static ArrayList<Money> MONEYLIST = new ArrayList<Money>();
	
	public LoginWindow() {
		DDJangFrame f=new DDJangFrame(new ImageIcon("login.png"));
		
		
		TextField inputID = new TextField(); //아이디 입력 공간 생성 inputID
		inputID.setBounds(250, 350, 230, 50);

		TextField inputPW = new TextField(); //비밀번호 입력 공간 생성 inputPW
		inputPW.setBounds(250, 435, 230, 50);
		inputPW.setEchoChar('*');
		
		JButton btnLogin = DDJangFrame.newBtnIcon("로그인", new ImageIcon("BtnLogin.png") );	
		btnLogin.setBounds(225, 550, 200, 100);
		JButton btnSignin = DDJangFrame.newBtnIcon("회원가입", new ImageIcon("BtnSignin.png"));
		btnSignin.setBounds(225, 700, 200, 100);
		
		//컴포넌트 J프레임에 넣기 
		f.add(inputID);
		f.add(inputPW); 
		f.add(btnLogin);
		f.add(btnSignin);

		//회원가입 버튼 누르면 새로운창 생성 
		btnSignin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Join_membership();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//로그인 버튼 구현
		btnLogin.addActionListener(new ActionListener() {
			
			private UserInfo ui;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = "C:/DDJ/"+ inputID.getText().trim() +"/UserInfo.sav";
				
				File f1 = new File(path);
				
				if( f1.exists() ) {

					FileInputStream fis = null;
					ObjectInputStream ois = null;
					
					try {
						
						fis = new FileInputStream(path);
						ois = new ObjectInputStream(fis);
						
						ui = (UserInfo) ois.readObject();
						
						if(inputPW.getText().equals(ui.getPw())) {
							LoginWindow.USERINFO.setId(ui.getId());
							LoginWindow.USERINFO.setPw(ui.getPw());
							
							System.out.println(ui.getId() + "회원 님 로그인 성공");
							
							f.dispose();
							
							SelectFrame sf = new SelectFrame() ;
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							inputPW.setText("");
						}
						
						
						
					} catch (Exception e1) {
						System.out.println("로드실패");
						
					} finally {
						
						try {
							
							if(ois != null)
								ois.close();
							
							if(fis != null)
								fis.close();
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}//finally
					
				} else {
					JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					inputID.setText("");
					inputPW.setText("");
				}
				
			}
			
		});

	}
}

	 
