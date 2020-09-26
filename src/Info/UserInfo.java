package Info;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable{
	
	//유저 정보 세팅 클래스
	private String id;
	private String pw;
	
	public UserInfo() {
		this.id = "";
		this.pw = "";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
}