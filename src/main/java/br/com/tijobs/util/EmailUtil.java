package br.com.tijobs.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailUtil {

	@Value("${mail.smtp.password}")
	private String senha;
	@Value("${mail.smtp.starttls.username}")
	private String user;
	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.smtp.port}")
	private String port;
	@Value("${mail.smtp.auth}")
	private String auth;
	@Value("${mail.smtp.starttls.enable}")
	private String tls;

	public void enviar(String destinatario, String assunto, String texto) throws Exception {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth", auth);
		prop.put("mail.smtp.starttls.enable", tls); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, senha);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(assunto);
			message.setText(texto);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new Exception(e.getMessage(), e.getCause());
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e.getCause());
		}
	}

}
