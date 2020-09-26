package WriteMainFrame;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import LoginFrame.LoginWindow;
import UI.DDJangFrame;
import WriteFrame.WriteFrame;

public class Myframe{

	TextArea preview;
	DDJangFrame jf;

	public Myframe() {

		//이미지 설정
		ImageIcon back = new ImageIcon("WriteMain.png");
		jf = new DDJangFrame(back);	

		// 경로 설정
		String path = "C:/DDJ/" + LoginWindow.USERINFO.getId() +"/money.txt";
		
		//중단 미리보기 , 스크롤바, 텍스트필드
				preview = new TextArea("", 0, 0,
						TextArea.SCROLLBARS_VERTICAL_ONLY);
				preview.setBounds(75, 250, 500,300);
				
				File f1 = new File(path);
				
				if( f1.exists() ) {
					
				}
					FileInputStream fis = null;
					ObjectInputStream ois = null;
					
					try {
						
						fis = new FileInputStream(path);
						ois = new ObjectInputStream(fis);
						
						this.preview = (TextArea) ois.readObject();
						System.out.println("로드 성공");
					} catch (Exception e) {
						System.out.println("로드 실패");
						
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
				
				jf.add(preview);

		// 저장하기  버튼
		JButton save = DDJangFrame.newBtnIcon("저장하기", new ImageIcon("saveIcon.png"));
		save.setBounds(75, 580, 200, 100);
		jf.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("저장 버튼이 눌렸습니다.");
				
				File dir = new File("C:/DDJ");

				if(!dir.exists()) {
					dir.mkdirs();
				} else {
					
					ObjectOutputStream oos = null;
					FileOutputStream fos = null;

					try {

						fos = new FileOutputStream(path);
						oos = new ObjectOutputStream(fos);

						oos.writeObject(preview);

						JOptionPane.showMessageDialog(null, "저장되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						e1.printStackTrace();

					}finally {
						try {

							if(oos != null) {
								oos.close();
							}
							if(fos != null) {
								fos.close();
							}

						}catch(Exception e1) {

						}
					}

				}

			}

		});


		// 추가하기 버튼
		JButton close = DDJangFrame.newBtnIcon("추가하기", new ImageIcon("moreIcon.png"));
		close.setBounds(375, 580, 200, 100);
		jf.add(close);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				WriteFrame wf = new WriteFrame(LoginWindow.USERINFO);
			}
		});



	}

	public void setPreText() {
		for(int i = LoginWindow.MONEYLIST.size() - 1 ; i >= 0 ; i--) {
			preview.append(LoginWindow.MONEYLIST.get(i).date.get(Calendar.YEAR) + 
					"/" + (LoginWindow.MONEYLIST.get(i).date.get(Calendar.MONTH) ) + 
					"/" + LoginWindow.MONEYLIST.get(i).date.get(Calendar.DATE) + 
					"\n" + LoginWindow.MONEYLIST.get(i).amount + "원 " +
					"\n" + "분류: " + LoginWindow.MONEYLIST.get(i).category +
					"\n" + "------------------" + "\n");
		}
	}

	//Myframe


}