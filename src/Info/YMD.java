package Info;

public class YMD {
	//년,월,일 세팅 클래스
	private String selYear;
	private String selMonth;
	private String selDay;

	public void setSelYear(String selYear) {
		this.selYear = selYear;
		System.out.println(selYear);
	}
	
	public String getSelYear() {
		return selYear;
	}

	public void setSelMonth(String selMonth) {
		this.selMonth = selMonth;
		System.out.println(selMonth);
	}

	public String getSelMonth() {
		return selMonth;
	}

	
	public void setSelDay(String selDay) {
		this.selDay = selDay;
		System.out.println(selDay);
	}
	public String getSelDay() {
		return selDay;
	}

}
