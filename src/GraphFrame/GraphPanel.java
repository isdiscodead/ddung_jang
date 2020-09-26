package GraphFrame;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;

import Info.Category;
import Info.Money;

public class GraphPanel extends JPanel {

	
	ArrayList<Money> moneyInfo;
	
	public GraphPanel( ArrayList<Money> moneyInfo ) {
		this.moneyInfo = moneyInfo;
		// TODO Auto-generated constructor stub
	}
	
	// 그래프를 담을 패널 생성 + 통계 정보에 필요한 변수 생성
	JPanel graphPanel = new JPanel();
	int[] arcArr = null;
	HashMap<String, Integer> cateMap = new HashMap<String, Integer>();
	Calendar cal = Calendar.getInstance();
	int month = 0;
	MonthSelectHdl monthHdler = new MonthSelectHdl();
	
	// 통계 정보를 불러올 월을 선택하는 메서드
	public Choice selectMonth() {
		
		monthHdler.setGraphPhnel(this, moneyInfo);
		
		Choice selectMonth = new Choice();
		selectMonth.add("월 선택");
		selectMonth.add("1");selectMonth.add("2");selectMonth.add("3");
		selectMonth.add("4");selectMonth.add("5");selectMonth.add("6");
		selectMonth.add("7");selectMonth.add("8");selectMonth.add("9");
		selectMonth.add("10");selectMonth.add("11");selectMonth.add("12");
		
		selectMonth.setBounds(430, 50, 130, 70);
		
		selectMonth.addItemListener( monthHdler );
		
		this.month = monthHdler.getMonth();
		
		return selectMonth;

	}
	
	
	// 통계 정보를 만드는 메서드
	public void graphInfo(ArrayList<Money> moneyInfo) {

		// 돈 사용량이 0원일 경우 return;
		if( moneyInfo.get(0) == null) {
			System.out.println("불러올 사용량이 없습니다. ");
			
			return;
		}

		// 사용할 데이터의 인덱스 arrlist, 전체 돈 사용량
		ArrayList<Integer> indexArr = new ArrayList<Integer>();
		int totalM = 0 ;

		
		System.out.println("선택월:" + monthHdler.getMonth());
		
		// 해당하는 달의 데이터만 가져오기 + 돈 사용량의 전체 합을 구한다.
		for(int i = 0 ; i < moneyInfo.size() ; i++ ) {
			
			if ( moneyInfo.get(i).amount < 0) {
				
				if ( (moneyInfo.get(i).date.get(Calendar.MONTH) -1 == monthHdler.getMonth() )
						&& ( moneyInfo.get(i).date.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) ) {
					indexArr.add(i);
					totalM += moneyInfo.get(i).amount;
				}
			}

		}
		
		repaint();
		System.out.println(totalM + "사용");
		
		// ----------------------------------------------------------------

		// 카테고리에 따라 돈의 사용량을 분류한다. 
		Category cate = new Category();

		// 그러기 위한 카테고리 - 총액 해쉬맵 cateMap
		cateMap.put("sum", totalM);	// 위 구역에서 구한 총 금액도 해쉬맵에 넣어준다

		for(int i = 0 ; i < cate.cateOut.length ; ++i) {
			cateMap.put( cate.cateOut[i] , 0); 	// 카테고리별 총액은 기본적으로 0으로 초기화시킨다 @.@
		}

		// 본격적인 분류 시작,,,
		for( int i =  0 ; i < indexArr.size() ; i++ ) {
			for(int j = 0 ; j < cate.cateOut.length ; j++ ) {
				if( moneyInfo.get(indexArr.get(i)).category.contains( cate.cateOut[j])) {
					System.out.println(indexArr.get(i) + "번 " + cate.cateOut[j] + " 카테고리");
					int val = cateMap.get( cate.cateOut[j] ) ;	// 해당 카테고리명의 key를 통해 밸류값을 불러온다.
					val += moneyInfo.get(indexArr.get(i)).amount ;	// 사용 금액만큼 밸류값 증가
					cateMap.put(cate.cateOut[j], val);
				}
			}
		}

		// 여기까지도 총액이 0인 카테고리의 맵은 삭제해주자!
		cateMap.values().removeAll(Collections.singleton(0));
		System.out.println(cateMap);

		// 각각 카테고리별 금액의 비중을 구하자
		// 카테고리가 몇 개 들어올지 모르니 배열을 cateMap 크기로 만들기
		int[] arcArr = new int [ cateMap.size() ];

		// 비중 구할 때 쓸 key set을 만든다 (귀찮지만 이게 한계인듯)
		Set<String> set = cateMap.keySet();
		Iterator<String> iter = set.iterator();

		// 비중을 구해서 넣는다 ㅎㅎ,,
		for (int i = 0 ; i < arcArr.length ; ++i) {
			int rate =  cateMap.get(iter.next());
			if ( rate != cateMap.get("sum") ) {	// 가져온 금액 != 전체 금액
				arcArr[i] = (int) 360.0 * rate / cateMap.get("sum");
				System.out.println(arcArr[i]);
			}
		}

		this.arcArr = arcArr;
		
	}
	
	// 그래프를 그리는 메서드
	@Override
	public void paint(Graphics g) {	
		super.paint(g);
		
		// 기본 설정
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setFont(new Font("굴림" , Font.BOLD , 18) );

		// 그래프를 그리기 위한 변수
		int start = 0;
		int end = 0;
		Color color = new Color(0, 134, 255, 40);	// 기본 컬러
		int ligter = 40;

		// 범례 출력을 위한 변수
		Set<String> set = cateMap.keySet();
		Iterator<String> iter = set.iterator();
		String cateName;
		int height = 0;
		int rate = 0;

		// 총 지출 출력
		g.drawString((monthHdler.month + 1)+ "월 총 지출", 20, 20);
		String sumStr = "";
		sumStr += cateMap.get("sum");
		g.drawString(sumStr, 20, 40);
			
		// 그래프 그리기
		for( int i  = 0 ; i < arcArr.length ; ++i) {
			if ( i != arcArr.length - 1) {	// 마지막이 아니면~~
				end = arcArr[i];
				g.setColor(color);
				g.fillArc(45, 40, 300, 300, start, end);
				ligter += 35;
				color = new Color(0, 134, 255, ligter);
				start += arcArr[i];
			} else {
				end = arcArr[i];
				g.setColor(color);
				g.fillArc(45, 40, 300, 300, start, 360 - start );
			}
			
			// 범례 출력
			cateName = iter.next();
			
			if (  ! cateName.equals("sum")) {
				// 범례 출력하는 y축 좌표를 늘린다 (글자가 겹치지 않게 함)
				height += 25;
				// 비율을 구한다.
				rate = (int)( arcArr[i] / 3.6 );
				if( rate == 0) {
					rate = 100;
				}
				// 출력
				g.drawString( cateName + " : " + rate + "%  " + cateMap.get(cateName) +" 원", 60, 350 + height);
			}
		}
		
		System.out.println("그래프 생성 완료");
		
	}

}

