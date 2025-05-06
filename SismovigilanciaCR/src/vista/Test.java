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

import control.ValidadorDatos;
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
        ValidadorDatos validador = new ValidadorDatos();
        
        System.out.println("Pruebas validarDatosSismo");
        testSismo(validador);
        
        System.out.println("\nPruebas validarCorreo");
        testCorreo(validador);
        
        System.out.println("\nPruebas validarTelefono");
        testTelefono(validador);
        
        System.out.println("\nPruebas validarFecha");
        testFecha(validador);
        
        System.out.println("\nPruebas validarHora");
        testHora(validador);
        
//        Excel excel = new Excel("excelTest.xlsx");
//        excel.obtenerDatos();
//        System.out.println(excel.getDatos());
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
    
    private static void testSismo(ValidadorDatos v) {
        boolean resultado1 = v.validarDatosSismo(10.5, 5.5, 9.5, -84.5, "San José");
        System.out.println("Caso valido: " + (resultado1 ? "OK" : "F en el chat"));
        
        boolean resultado2 = v.validarDatosSismo(-1, 5.5, 9.5, -84.5, "San José");
        System.out.println("Profundidad negativa: " + (resultado2 ? "OK" : "F en el chat"));
        
        boolean resultado3 = v.validarDatosSismo(10.5, 5.5, 91, 181, "San José");
        System.out.println("Coordenadas invalidas: " + (resultado3 ? "OK" : "F en el chat"));
    }
    
    private static void testCorreo(ValidadorDatos v) {
        System.out.println("correo@dominio.com: " + 
            (v.validarCorreo("correo@dominio.com") ? "OK" : "F en el chat"));
        
        System.out.println("correodominio.com: " + 
            (v.validarCorreo("correodominio.com") ? "OK" : "F en el chat"));
            
        System.out.println("correo@dominio: " + 
            (v.validarCorreo("correo@dominio") ? "OK" : "F en el chat"));
    }
    
    private static void testTelefono(ValidadorDatos v) {
        System.out.println("+506 1234-5678: " + 
            (v.validarTelefono("+506 1234-5678") ? "OK" : "F en el chat"));
            
        System.out.println("abc123: " + 
            (v.validarTelefono("abc123") ? "OK" : "F en el chat"));
            
        System.out.println("22225555: " + 
            (v.validarTelefono("22225555") ? "OK" : "F en el chat"));
    }
    
    private static void testFecha(ValidadorDatos v) {
        System.out.println("12-05-2023: " + 
            (v.validarFecha("12-05-2023") ? "OK" : "F en el chat"));
            
        System.out.println("31-13-2020: " + 
            (v.validarFecha("31-13-2020") ? "OK" : "F en el chat"));
            
        System.out.println("2023-05-12: " + 
            (v.validarFecha("2023-05-12") ? "OK" : "F en el chat"));
    }
    
    private static void testHora(ValidadorDatos v) {
        System.out.println("23:59:59: " + 
            (v.validarHora("23:59:59") ? "OK" : "F en el chat"));
            
        System.out.println("24:00:00: " + 
            (v.validarHora("24:00:00") ? "OK" : "F en el chat"));
            
        System.out.println("12.30.45: " + 
            (v.validarHora("12.30.45") ? "OK" : "F en el chat"));
    }
    
}
