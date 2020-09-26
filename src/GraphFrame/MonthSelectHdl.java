package GraphFrame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;

import Info.Money;

public class MonthSelectHdl implements ItemListener {

	static int month = Calendar.getInstance().get(Calendar.MONTH);	// 디폴트는 해당 달
	GraphPanel gp;
	ArrayList<Money> m;
	
	public void setGraphPhnel( GraphPanel gp, ArrayList<Money> m) {
		this.gp  = gp;
		this.m = m;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		String str = (String)e.getItem();
		
		switch (str) {
		
		case "월 선택" :
			System.out.println("아무 달도 선택하지 않으셨습니다. ");
			break;

		case "1" :
			System.out.println("1월 통계 생성");
			this.month = 0;
			break;
			
		case "2" :
			this.month = 1;
			break;
			
		case "3" :
			this.month = 2;
			break;
			
		case "4" :
			this.month = 3;
			break;
			
		case "5" :
			this.month = 4;
			break;
			
		case "6" :
			this.month = 5;
			break;
			
		case "7" :
			this.month = 6;
			break;
			
		case "8" :
			this.month = 7;
			break;
			
		case "9" :
			this.month = 8;
			break;
			
		case "10" :
			this.month = 9;
			break;
			
		case "11" :
			this.month = 10;
			break;
			
		case "12" :
			this.month = 11;
			break;
			
			
			
		}
		
		gp.graphInfo(m);
	}

	public int getMonth() {
		return month;
	}

}
