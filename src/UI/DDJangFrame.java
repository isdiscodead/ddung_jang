package UI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class DDJangFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public DDJangFrame( ImageIcon backgroundImage ) {

		// 프레임 기본 설정
		setSize(650, 900);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setBackground(Color.WHITE);
		setTitle("뚱장");
		
		// 아이콘 변경
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("icon.png");
        setIconImage(icon);
        
		//x버튼 종료
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		// 백그라운드 설정
		JLayeredPane title = new JLayeredPane() {
			
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) {
					g.drawImage(backgroundImage.getImage(), 0, 0, null);
					super.paintComponent(g);
					setOpaque(false);	
				}
				
			};
			
		
		setContentPane(title);
		title.setBounds(0, 0, getWidth(), getHeight());
		
		// 로그아웃 버튼 
		JButton logout = new JButton("종료", new ImageIcon ("exitIcon.png"));
		
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		logout.setContentAreaFilled(false);
		
		logout.setBounds( 530, 20, 100, 100);
	
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		title.add(logout);
		
		// 폰트 설정
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		setFont(font);

	}

		// 버튼 아이콘 등록하는 메서드 
		public static JButton newBtnIcon(String name, ImageIcon img) {
			JButton button = new JButton(name, img);

			// 버튼에 이미지 넣고 이미지만 남기기
			button.setBorderPainted(false);
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);

			return button;
		}

	}
