/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vista;

// Clases para createExcelFile y readExcelFile
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Clases para sendEmail
//import java.util.*; 
//import javax.mail.*; 
//import javax.mail.internet.*; 
//import javax.activation.*; 
//import javax.mail.Session; 
//import javax.mail.Transport; 

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
        createExcelFile();
        readExcelFile();
        //sendEmailWithJavaMail("****@gmail.com");
        //sendEmailWithAngusMail();
    }
    
    public static void createExcelFile() {
        // Blank workbook 
        XSSFWorkbook workbook = new XSSFWorkbook(); 
  
        // Creating a blank Excel sheet 
        XSSFSheet sheet = workbook.createSheet("student Details"); 
  
        // Creating an empty TreeMap of string and Object][] 
        // type 
        Map<String, Object[]> data = new TreeMap<String, Object[]>(); 
  
        // Writing data to Object[] 
        // using put() method 
        data.put("1", new Object[] { "ID", "NAME", "LASTNAME" }); 
        data.put("2", new Object[] { 1, "Pankaj", "Kumar" }); 
        data.put("3", new Object[] { 2, "Prakashni", "Yadav" }); 
        data.put("4", new Object[] { 3, "Ayan", "Mondal" }); 
        data.put("5", new Object[] { 4, "Virat", "kohli" }); 
  
        // Iterating over data and writing it to sheet 
        Set<String> keyset = data.keySet(); 
  
        int rownum = 0; 
  
        for (String key : keyset) { 
  
            // Creating a new row in the sheet 
            Row row = sheet.createRow(rownum++); 
  
            Object[] objArr = data.get(key); 
  
            int cellnum = 0; 
  
            for (Object obj : objArr) { 
  
                // This line creates a cell in the next 
                //  column of that row 
                Cell cell = row.createCell(cellnum++); 
  
                if (obj instanceof String)
                    cell.setCellValue((String)obj); 
  
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj); 
            } 
        } 
  
        // Try block to check for exceptions 
        try { 
  
            // Writing the workbook 
            FileOutputStream out = new FileOutputStream(new File("gfgcontribute.xlsx")); 
            workbook.write(out); 
  
            // Closing file output connections 
            out.close(); 
  
            // Console message for successful execution of 
            // program 
            System.out.println("gfgcontribute.xlsx written successfully on disk."); 
        } 
  
        // Catch block to handle exceptions 
        catch (Exception e) { 
  
            // Display exceptions along with line number 
            // using printStackTrace() method 
            e.printStackTrace(); 
        } 
    }    
    
    public static void readExcelFile() {
        // Try block to check for exceptions 
        try {
            // Reading file from local directory 
            FileInputStream file = new FileInputStream(new File("gfgcontribute.xlsx")); 
  
            // Create Workbook instance holding reference to 
            // .xlsx file 
            XSSFWorkbook workbook = new XSSFWorkbook(file); 
  
            // Get first/desired sheet from the workbook 
            XSSFSheet sheet = workbook.getSheetAt(0); 
  
            // Iterate through each rows one by one 
            Iterator<Row> rowIterator = sheet.iterator(); 
  
            // Till there is an element condition holds true 
            while (rowIterator.hasNext()) { 
                Row row = rowIterator.next();
                // For each row, iterate through all the 
                // columns 
                Iterator<Cell> cellIterator 
                    = row.cellIterator(); 
  
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    // Checking the cell type and format 
                    // accordingly 
                    switch (cell.getCellType()) {
                    // Case 1 
                    case Cell.CELL_TYPE_NUMERIC: 
                        System.out.print( 
                            cell.getNumericCellValue() 
                            + "t"); 
                        break;
                    // Case 2 
                    case Cell.CELL_TYPE_STRING: 
                        System.out.print( 
                            cell.getStringCellValue() 
                            + "t"); 
                        break; 
                    } 
                }
                System.out.println(""); 
            }
            // Closing file output streams 
            file.close(); 
        }
        // Catch block to handle exceptions 
        catch (Exception e) {
            // Display the exception along with line number 
            // using printStackTrace() method 
            e.printStackTrace(); 
        } 
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
