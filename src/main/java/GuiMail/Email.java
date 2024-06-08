package GuiMail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	// Email: ubuntumail12@gmail.com
	// password : aeaz rxki yaeg pptq
	static final String from =  "ubuntumail12@gmail.com";
	static final String password =  "aeazrxkiyaegpptq";
//	static final String to = "lequangdinh0609@gmail.com";
	
	public static void gui_matkhau(String email,String username, String pass)
	{
		//Khai báo các thuộc tính 
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP host
				props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");

				// tạo Authenticator
				Authenticator auth = new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication(from, password);
					}
				};
				
				//Phiên làm việc
				Session session = Session.getInstance(props,auth);
				
				// Gửi mail 
				MimeMessage msg = new MimeMessage(session);
				try {
					msg.setFrom(new InternetAddress(from,"CTY DIEN LUC"));
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
					// Tiêu đề
					msg.setSubject("Công Ty Điện Lực - NHÓM 4 - N21CQAT01-N");
					//Nội dung
					msg.setText("Đây là tài khoản của bạn:" + "\nUsername: "+ username+ "\nPassword: "+ pass,"UTF-8");
					//Gửi
					Transport.send(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	}
	
	public static void main (String[] args)
	{
//		Email.gui_matkhau("lequangdinh0609@gmail.com","0123");
	}
}
