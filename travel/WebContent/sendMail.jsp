<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="travel.SMTPAuthenticator"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setCharacterEncoding("EUC-KR");
 
String subject = request.getParameter("subject");
String content = request.getParameter("content");

Properties p = new Properties();
 
p.put("mail.smtp.host","smtp.naver.com");
 
p.put("mail.smtp.port", "465");
p.put("mail.smtp.starttls.enable", "true");
p.put("mail.smtp.auth", "true");
p.put("mail.smtp.debug", "true");
p.put("mail.smtp.socketFactory.port", "465");
p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
p.put("mail.smtp.socketFactory.fallback", "false");

 
try{
    Authenticator auth = new SMTPAuthenticator();
    Session ses = Session.getInstance(p, auth);
     
    ses.setDebug(true);
     
    MimeMessage msg = new MimeMessage(ses); 
    msg.setSubject(subject); 
     
    Address fromAddr = new InternetAddress("legotravel@naver.com");
    msg.setFrom(fromAddr); 
    Address toAddr = new InternetAddress("parkbyungtae91@naver.com");
    msg.addRecipient(Message.RecipientType.TO, toAddr); 
     
    msg.setContent(content, "text/html;charset=EUC-KR"); 
     
    Transport.send(msg); 
} catch(Exception e){
    e.printStackTrace();
    out.println("<script>alert('전송실패');history.back();</script>");
  
    return;
}
 
out.println("<script>alert('전송완료!!');location.href='mailForm.jsp';</script>");

%>
