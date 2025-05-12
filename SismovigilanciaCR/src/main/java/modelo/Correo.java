/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author javie
 */
public class Correo {
    
    public Correo() {
    }
    
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        // Configuraciones para la conexión SMTP
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        // Autenticación
        final String usuario = "leejavier62@gmail.com"; 
        final String contraseña = "**** **** **** ****";
        Session sesion = Session.getInstance(propiedades,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, contraseña);
                    }
                });

        try {
            // Crear el mensaje
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            // Enviar el mensaje
            Transport.send(mensaje);

            System.out.println("Correo enviado exitosamente!");

        } 

        catch (MessagingException e) {
        e.printStackTrace();
        }
    }
}
