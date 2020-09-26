package LoginFrame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Info.UserInfo;

public class SignIn {

	public SignIn(UserInfo info) {
		String path = "C:/DDJ/" + info.getId() + "/UserInfo.sav";

		File dir = new File("C:/DDJ/");
		
		if( !dir.exists() ) {
			dir.mkdirs();
		}

		File dir2 = new File( dir , info.getId() );
		
		if( !dir2.exists() ) {
			dir2.mkdirs();
		}

		// 파일 쓰기
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream( fos );

			oos.writeObject( info );	
			System.out.println("회원가입 성공 ");

		} catch (Exception e) {
			System.out.println("회원가입 실패. ");
			e.printStackTrace();

		} finally {

			try {
				if( oos != null )
					oos.close();
				if( fos != null )
					fos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}


