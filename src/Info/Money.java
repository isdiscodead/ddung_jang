package Info;


import java.io.Serializable;
import java.util.Calendar;

public class Money implements Serializable {
	
	public int amount;
	public String category ;
	public Calendar date ;
	
	public Money() {
		this.amount = 0;
		this.category = "기타";
		this.date = Calendar.getInstance();
	}
	
	public Money(int amount, String category, Calendar date) {
		this.amount = amount;
		this.category = category;
		this.date = date;
	}
	
}
