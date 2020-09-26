package LoginFrame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Info.UserInfo;
import UI.DDJangFrame;	

public class Join_membership {

	String nId;
	String nPass;
	
	public Join_membership () throws IOException {

		DDJangFrame f=new DDJangFrame(new ImageIcon("sign.png"));

		TextField newID =new TextField();
		newID.setBounds(250, 450, 230, 50);
		

		TextField newPassWord=new TextField();
		newPassWord.setBounds(250, 535, 230, 50);
		newPassWord.setEchoChar('*');
		
		JButton cancle = DDJangFrame.newBtnIcon("취소", new ImageIcon("cancleIcon.png") );	
		cancle.setBounds(350, 670, 200, 100);
		JButton Signin = DDJangFrame.newBtnIcon("회원가입", new ImageIcon("BtnSignin.png"));
		Signin.setBounds(100, 670, 200, 100);
		JButton IdCheck = DDJangFrame.newBtnIcon("중복 확인", new ImageIcon("IDCheck.png"));
		IdCheck.setBounds(500, 450, 100, 50);

		IdCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nId = newID.getText().trim();
				
				File file = new File("C:/DDJ/" + newID.getText()); //파일 경로명 추가 필요

				if(nId.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요" ,"회원가입", JOptionPane.WARNING_MESSAGE);
				}
				else if(file.exists()) {
					JOptionPane.showMessageDialog(null, "같은 아이디가 존재합니다." ,"회원가입", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디입니다" ,"회원가입", JOptionPane.DEFAULT_OPTION);
				}
				}
			
				
			}); 

		Signin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(newID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요" ,"회원가입", JOptionPane.WARNING_MESSAGE);
				}
				else if(newPassWord.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요" ,"회원가입", JOptionPane.WARNING_MESSAGE);
				} else {
					nPass = newPassWord.getText().trim();

					System.out.println(nId + nPass);
					LoginWindow.USERINFO.setId(nId);
					LoginWindow.USERINFO.setPw(nPass);
					System.out.println(LoginWindow.USERINFO.getId());
					
					new SignIn(LoginWindow.USERINFO);
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다." ,"회원가입", JOptionPane.DEFAULT_OPTION);
					f.dispose();
				}
				
			}

			});

		//컴포넌트 J프레임에 추가
		f.add(newID);
		f.add(newPassWord);
		f.add(cancle); f.add(Signin); f.add(IdCheck);

	}



}



