package WriteFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Info.Money;
import Info.UserInfo;
import Info.YMD;
import Info.YMDtoCal;
import LoginFrame.Join_membership;
import LoginFrame.LoginWindow;
import UI.DDJangFrame;
import WriteMainFrame.Myframe;

public class WriteFrame extends JFrame {

	DDJangFrame wf = new DDJangFrame( new ImageIcon("Write.png"));

	JRadioButton deposit;
	JRadioButton withdraw;
	JButton input;
	JTextField jt;
	JButton confirm;
	JButton cancle;
	YMD ymd;
	JComboBox<String> cateInCombo;
	JComboBox<String> cateOutCombo;
	JComboBox<String> years;
	JComboBox<String> months;
	JComboBox<String> days;
	Myframe mf;
	YMDtoCal ycal = new YMDtoCal() ;

	Money money = new Money();
	
	String[] cateOut = {"식비", "의료/건강", "미용", "교통", "문화/여가", "여행", 
			"교육", "생활", "경조사", "주거/통신", "기타"};
	String[] cateIn = {"월급", "용돈", "기타"};
	String[] year = {"Year 선택","2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"};
	String[] month = {"Month 선택","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String[] day = {"Day 선택","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
			"20","21","22","23","24","25","26","27","28","29","30","31"};
	// 카테고리, 연도, 월, 일 배열
	// 타 클래스에 선언 하면 드롭박스에서 배열 인식이 되지 않아 직접 선언하였습니다. 

	public WriteFrame( UserInfo userinfo ) {
		wf.setTitle("기록하기");
		
		years = new JComboBox<String>(year); // 연도 콤보 박스 생성
		years.setBounds(150, 360, 80, 30); // 연도 콤보 박스 사이즈
		years.addActionListener(new ActionListener() { //키 액션 리스너 생성

			@Override
			public void actionPerformed(ActionEvent e) {
				String selYear = years.getSelectedItem().toString(); // 선택된 Years의 연도 가져오기 (String Name)
				if(years.getSelectedItem().toString().equalsIgnoreCase("Year 선택")) { // Year가 숫자로 선택되지 않았을 때 노출되는 에러 메시지
					JOptionPane.showMessageDialog(null, "년도를 정확히 선택해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					ymd = new YMD(); //ymd 클래스 변수에 값 세팅 전, 새로운 객체를 생성하여 이전 값을 덮어씌우는 것을 방지
					ymd.setSelYear(selYear); //ymd 클래스에 선언되어 있는 변수에 값 세팅
				}
			}
		});

		months = new JComboBox<String>(month); //월 콤보 박스 생성
		months.setBounds(240, 360, 100, 30); //월 콤보박스 사이즈
		months.addActionListener(new ActionListener() { // 키 액션 리스너 생성

			@Override
			public void actionPerformed(ActionEvent e) { 
				String selMonth = months.getSelectedItem().toString(); //선택된 Months의 월 가져오기(String Name)
				if(months.getSelectedItem().toString().equalsIgnoreCase("Month 선택")) { // Month가 숫자로 선택되지 않았을 때 노출되는 에러 메시지
					JOptionPane.showMessageDialog(null, "월을 정확히 선택해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					 //ymd 클래스 변수에 값 세팅 전, 새로운 객체를 생성하여 이전 값을 덮어씌우는 것을 방지
					ymd.setSelMonth(selMonth);//ymd 클래스에 선언되어 있는 변수에 값 세팅
				}
			}
		});

		days = new JComboBox<String>(day); //Day 콤보 박스 생성
		days.setBounds(350, 360, 80, 30);//Day 콤보 박스 사이즈
		days.addActionListener(new ActionListener() { //키 액션 리스너 생성

			@Override
			public void actionPerformed(ActionEvent e) {
				String selDays = days.getSelectedItem().toString(); //선택된 Days의 월 가져오기(String Name)
				if(days.getSelectedItem().toString().equalsIgnoreCase("Day 선택")) {// day가 숫자로 선택되지 않았을 때 노출되는 에러 메시지
					JOptionPane.showMessageDialog(null, "날짜를 정확히 선택해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					 //ymd 클래스 변수에 값 세팅 전, 새로운 객체를 생성하여 이전 값을 덮어씌우는 것을 방지
					ymd.setSelDay(selDays);//ymd 클래스에 선언되어 있는 변수에 값 세팅
				}

			}
		});

		input = DDJangFrame.newBtnIcon("확인", new ImageIcon("okIcon_small.png")); //버튼에 이미지 삽입
		input.setBounds(320, 550, 100, 50); //버튼 사이즈
		input.addActionListener(new ActionListener() { // input 키 액션 리스너 생성 

			@Override
			public void actionPerformed(ActionEvent e) {
				if(deposit.isSelected() == true) { // 입금, 지출 라디오 버튼 선택 여부 확인
					if(jt.getText().isEmpty()) { //Text필드에 값이 있는지, 없는지 확인
						JOptionPane.showMessageDialog(null, "금액을 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE); //Text필드에 값이 없을 때 노출되는 알림
					}
					else if(years.getSelectedItem().toString().equalsIgnoreCase("Year 선택") ||  // 년, 월, 일이 선택되었는지 확인
							months.getSelectedItem().toString().equalsIgnoreCase("Month 선택") || 
							days.getSelectedItem().toString().equalsIgnoreCase("Day 선택")) {
						JOptionPane.showMessageDialog(null, "날짜를 확인해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);// 년, 월, 일이 선택되지 않고 Input이 
						// 눌렸을 경우 노출되는 알림
					}
					else {
						try {
							String deposit = jt.getText(); // Text 필드에 입력된 값 가져오기
							money.amount = Integer.parseInt(deposit); // 수입 값 넣기
							System.out.println(deposit);
							int a = Integer.parseInt(jt.getText()); // Integer.ParseInt로 변환 시도 하여, 숫자가 아닌, 문자열일 경우 Catch에서 알림 메시지 노출
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "숫자만 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE); //문자열일 경우 Catch에서 알림 메시지 노출
						}
					}

				}		
				else if(withdraw.isSelected() == true){ //지출 라디오 버튼이 선택되어 있는지 확인
					if(jt.getText().isEmpty()) { //금액 입력 Text필드가 비어 있는지 확인
						JOptionPane.showMessageDialog(null, "금액을 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE); //비어 있을 때 노출되는 알림
					}
					else if(years.getSelectedItem().toString().equalsIgnoreCase("Year 선택") ||  // 년, 월, 일이 선택되었는지 확인
							months.getSelectedItem().toString().equalsIgnoreCase("Month 선택") || 
							days.getSelectedItem().toString().equalsIgnoreCase("Day 선택")) {
						JOptionPane.showMessageDialog(null, "날짜를 확인해주세요", "알림", JOptionPane.INFORMATION_MESSAGE); // 년, 월, 일이 선택되지 않고 Input이 
						// 눌렸을 경우 노출되는 알림
					}
					else {
						try {
							String withdraw = jt.getText(); // 텍스트 필드에 입력된 값 가져오기
							money.amount = - Integer.parseInt(withdraw); // 지출 값 넣기
							System.out.println("-" + withdraw);
							int a = Integer.parseInt(jt.getText()); // Integer.ParseInt로 변환 시도 하여, 숫자가 아닌, 문자열일 경우 Catch에서 알림 메시지 노출
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "숫자만 입력해주세요", "알림", JOptionPane.INFORMATION_MESSAGE);//문자열일 경우 Catch에서 알림 메시지 노출
						}
					}
				}		
				else {
					JOptionPane.showMessageDialog(null, "분류를 먼저 선택해주세요", "알림", JOptionPane.INFORMATION_MESSAGE); //카테고리가 선택되지 않았을 때 노출되는 알림
				}
			}
		});

		deposit = new JRadioButton("수입"); // 수입 라디오 버튼
		deposit.setBounds(305, 460, 100, 30); // 버튼 사이즈
		deposit.addItemListener(new ItemListener() { //키 액션 리스너 생성

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(deposit.isSelected() == true) { //라디오 버튼 선택 여부 확인
					cateInCombo.setVisible(true); // 수입 라디오 버튼이 선택되었을 때 수입 카테고리 콤보박스 노출
				}
				else {
					cateInCombo.setVisible(false); // 수입 라디오 버튼이 선택되지 않았을 때 수입 카테고리 콤보박스 비노출
				}
			}
		});

		withdraw = new JRadioButton("지출"); // 지출 라디오 버튼
		withdraw.setBounds(420, 460, 100, 30); //버튼 사이즈
		withdraw.addItemListener(new ItemListener() { //키 액션 리스너 생성

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(withdraw.isSelected() == true) { // 라디오 버튼 선택 여부 확인
					cateOutCombo.setVisible(true);// 지출 라디오 버튼이 선택되었을 때 수입 카테고리 콤보박스 노출
				}
				else {
					cateOutCombo.setVisible(false);// 지출 라디오 버튼이 선택되지 않았을 때 수입 카테고리 콤보박스 비노출
				}

			}
		});
		ButtonGroup bg = new ButtonGroup(); //라디오 버튼을 그룹으로 묶어 두 개가 동시에 선택되지 않도록 세팅
		bg.add(deposit);
		bg.add(withdraw);

		jt = new JTextField(10); // 금액 입력 텍스트 필드 생성
		jt.setBounds(150, 450, 150, 50); //텍스트 필드 사이즈
		jt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) { //엔터키 입력 감지
					input.doClick(); // 텍스트 필드에 값 입력 후 엔터 입력 시 Input 버튼 실행 
					jt.setText(""); // 입력버튼 누를 시 텍스트 필드 클리어
					jt.requestFocus(); //키보드 입력 커서 텍스트 필드에 고정
				}
			}
		});

		cateInCombo = new JComboBox<String>(cateIn); //수입 카테고리 콤보박스 생성
		cateInCombo.setBounds(150, 560, 100, 30); //콤보박스 사이즈
		cateInCombo.setVisible(false); // 라디오 버튼 선택 여부에 따라 카테고리를 노출시켜야 하기 떄문에 기본값을 미노출로 설정

		cateInCombo.addActionListener(new ActionListener() { //키 액션 리스너 생성
			@Override
			public void actionPerformed(ActionEvent e) {
				String selCategory = (String) cateInCombo.getSelectedItem(); // 콤보박스에서 선택한 카테고리 String 값으로 추출
				money.category = selCategory;
			}
		});

		cateOutCombo = new JComboBox<String>(cateOut); //지출 카테고리 콤보박스 생성
		cateOutCombo.setBounds(150, 560, 100, 30); // 콤보박스 사이즈
		cateOutCombo.setVisible(false); // 라디오 버튼 선택 여부에 따라 카테고리를 노출시켜야 하기 떄문에 기본값을 미노출로 설정
		cateOutCombo.addActionListener(new ActionListener() { //키 액션 리스너 생성
			@Override
			public void actionPerformed(ActionEvent e) {
				String selCategory = (String) cateOutCombo.getSelectedItem(); // 콤보박스에서 선택한 카테고리 String 값으로 추출
				money.category = selCategory;
			}
		});

		confirm = DDJangFrame.newBtnIcon("완료", new ImageIcon("confirm.png")); //화면 하단 확인 버튼 생성 및 이미지 삽입
		confirm.setBounds(50, 650, 200, 200); // 확인 버튼 사이즈
		confirm.addActionListener(new ActionListener() { //키 액션 리스너 생성
			@Override
			public void actionPerformed(ActionEvent e) { // 화면 하단 확인 버튼
				Calendar cal = ycal.YMDtoCal(ymd);
				money.date = cal;
				System.out.println(money.amount + money.category + money.date.get(Calendar.YEAR) + 
						money.date.get(Calendar.MONTH) + money.date.get(Calendar.DATE));
				int index = LoginWindow.MONEYLIST.size() ;
				LoginWindow.MONEYLIST.add(index, money);	// moneyList에 추가
				money = new Money();	// 초기화
				wf.dispose();	// 기록하기 프레임 닫기
				mf = new Myframe();	// 기록 메인 프레임 호출
				mf.setPreText();
			}
		});

		cancle = DDJangFrame.newBtnIcon("취소", new ImageIcon("cancleIcon.png")); //취소 버튼 생성
		cancle.setBounds(390, 650, 200, 200); //취소 버튼 사이즈
		cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //취소 버튼 클릭 시 종료
				wf.dispose();
			}
		});

		wf.add(years);
		wf.add(months);
		wf.add(days);
		wf.add(jt);
		wf.add(deposit);
		wf.add(withdraw);
		wf.add(cateInCombo);
		wf.add(cateOutCombo);
		wf.add(input);
		wf.add(confirm);
		wf.add(cancle);
	}

}

