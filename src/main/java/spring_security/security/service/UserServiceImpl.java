package spring_security.security.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import spring_security.security.entity.User;
import spring_security.security.repository.StudentRepo;
import spring_security.security.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepo userRepo;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public User saveUser(User user, String url) {

		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);

		user.setEnable(false);
		user.setVerificationCode(UUID.randomUUID().toString());

		User newuser = userRepo.save(user);

		if (newuser != null) {
			sendEmail(newuser, url);
		}

		return newuser;
	}

		// @Override
	public void sendEmail(User user, String url) {
		String from = "alaminshiblu53@gmail.com";
		String to = user.getEmail();
		String subject = "Account Verfication";
		String content = """
			<div style="width:100%; color:white; padding:50px 40px; background:blueviolet;">
				<h1>Dear [[name]],</h1><br>\
                Please click the link below to verify your registration:<br>\
				If you think it is not you, Just ignore it.
                <h3><a style="background:blue;border-radius:3px;color:white; height:40px; width:250px;" href="[[URL]]" target="_self">VERIFY</a></h3>\
                Thank you,<br>\
                MD Sibli Hossain\
			</div>
                """;

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setFrom(from, "Spring Security");
			helper.setTo(to);
			helper.setSubject(subject);

			content = content.replace("[[name]]", user.getName());
			String siteUrl = url + "/verify?code=" + user.getVerificationCode();

			System.out.println(siteUrl);

			content = content.replace("[[URL]]", siteUrl);

			helper.setText(content, true);

			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public boolean verifyAccount(String verificationCode) {

		User user = userRepo.findByVerificationCode(verificationCode);

		if (user == null) {
			return false;
		} else {

			user.setEnable(true);
			user.setVerificationCode(null);

			userRepo.save(user);

			return true;
		}

	}

	@Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}

	

	



}
