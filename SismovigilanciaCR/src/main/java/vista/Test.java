/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package vista;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

import modelo.Excel;

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
        excel.extraerSismosDeArchivo();
        System.out.println(excel.getSismos());
        String[] param = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        excel.agregarSismo(param);
        System.out.println(excel.getSismos());
        excel.guardarDatos();
        //System.out.println(excel.getSismos());
    }
}
