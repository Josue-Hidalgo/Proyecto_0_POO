/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vista;

// Clases para sendEmail
//import java.util.*; 
//import javax.mail.*; 
//import javax.mail.internet.*; 
//import javax.activation.*; 
//import javax.mail.Session; 
//import javax.mail.Transport;

import modelo.Excel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author javie
 */
public class Test {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Excel excel = new Excel("excelTest.xlsx");
        System.out.println(excel.getListaSismos());
        excel.setCelda(0, 0, "Hola");
        excel.guardarDatos();
        //sendEmailWithJavaMail("****@gmail.com");
        //sendEmailWithAngusMail();
    }
    
//    public static void sendEmailWithJavaMail(String recipient) throws Exception {
//        System.out.println("Preparing to send mail");
//        Properties properties = new Properties();
//        
//        properties.put("mail.smtp.auth", true);
//        properties.put("mail.smtp.starttls.enable", true);
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.transport.protocl", "smtmp");
//        properties.put("mail.smtp.port", 587);
//        
//        String myAccountEmail = "*****@gmail.com";
//        String password = "*****";
//        
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAunthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//        
//        Message message = prepareMessage(session, myAccountEmail, recipient);
//        
//        Transport.send(message);
//        System.out.println("Message sent succesfully");
//    }
    
//    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//            message.setSubject("My first Email from Java App");
//            message.setText("Hey there, \n Look my email");
//            return message;
//        }
//        catch (Exception ex) {
//            
//        }
//        return null;
//    }
    
//    public static void sendEmailWithAngusMail() throws Exception {
//        Properties prop = new Properties();
//        prop.put("mail.smtp.auth", true);
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
//        prop.put("mail.smtp.port", "25");
//        prop.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");
//        
//        Session session = Session.getInstance(prop, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("javi0409@estudiantec.cr", "}UGK#z!QPGTVDr10");
//            }
//        });
//        
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("javi0409@estudiantec.cr"));
//        message.setRecipients(
//          Message.RecipientType.TO, InternetAddress.parse("javi0409@estudiantec.cr"));
//        message.setSubject("Mail Subject");
//
//        String msg = "This is my first email using JavaMailer";
//
//        MimeBodyPart mimeBodyPart = new MimeBodyPart();
//        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(mimeBodyPart);
//
//        message.setContent(multipart);
//
//        Transport.send(message);
//        System.out.println("Message sent");
//    }
}
