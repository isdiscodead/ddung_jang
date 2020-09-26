package Info;

import java.util.Calendar;

public class YMDtoCal {
	
	public Calendar YMDtoCal(YMD date) {
		Calendar cal = Calendar.getInstance();
		
		int year = Integer.parseInt(date.getSelYear());
		String monthStr = date.getSelMonth();
		int day = Integer.parseInt(date.getSelDay());
		int month = 0;
		
		switch (monthStr) {
		case "January":
			month = 1;
			break;
		case "February":
			month = 2;
			break;
		case "March":
			month = 3;
			break;
		case "April":
			month = 4;
			break;
		case "May":
			month = 5;
			break;
		case "June":
			month = 6;
			break;
		case "July":
			month = 7;
			break;
		case "August":
			month = 8;
			break;
		case "September":
			month = 9;
			break;
		case "October":
			month = 10;
			break;
		case "November":
			month = 11;
			break;
		case "December":
			month = 12;
			break;
		}
		
		cal.set(year, month, day);
		
		return cal;
	}
	
}
