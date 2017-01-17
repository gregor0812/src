/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Koen Hengsdijk
 */
public class StatisticsPDF {
    
    StatisticsPDF(){
    }
    
    public void makeStatistics(){
        
        try {

            File pdfTemplate = new File("src\\resources\\pdf\\StatisticsYear.pdf");

            // Create a document and add a page to it
            PDDocument document = new PDDocument().load(pdfTemplate);
            PDPage page = document.getPage(0);

// Create a new font object selecting one of the PDF base fonts
            PDFont font = PDType1Font.HELVETICA;
            

            // Start a new content stream which will "hold" 
            // the to be created content
            PDPageContentStream contentStream
                = new PDPageContentStream(document, page, true, true);

            // this content stream places the firstname of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(200, 600);
            contentStream.drawString("lost januari");
            contentStream.endText();

            // this content stream places the insertion of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(104, 524);
            contentStream.drawString("lost februari");
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            
           
            // this content stream places the last name of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(112, 503);
            contentStream.drawString("lost march");
           
            contentStream.endText();

            // this content stream places the first phone numberer of the 
            // customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(140, 481);
            contentStream.drawString("lost april");
            contentStream.endText();

            // this content stream places the second phone numberer of the 
            // customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(140, 459);
            contentStream.drawString("lost may");
            contentStream.endText();

            // this content stream places the email of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(85, 438);
            contentStream.drawString("lost june");
            contentStream.endText();

            // this content stream places the adress of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(96, 416);
            contentStream.drawString("lost juli");
            contentStream.endText();

            // this content stream places the city of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(78, 395);
            contentStream.drawString("lost august");
            contentStream.endText();

            // this content stream places the zipcode of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(98, 367);
            contentStream.drawString("lost september");
            contentStream.endText();

            // this content stream places the country of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(95, 340);
            contentStream.drawString("lost october");
            contentStream.endText();

            // this content stream places the airport name
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(330, 206);
            contentStream.drawString("lost november");
            contentStream.endText();

            // this content stream places the name of the employee in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(374, 233);
            contentStream.drawString("lost december");
            contentStream.endText();

            // this content stream places the time in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(320, 260);
            contentStream.drawString("found januari");
            contentStream.endText();

            // this content stream places the date in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(320, 287);
            contentStream.drawString("found februari");
            contentStream.endText();

            // this content stream places the labelnumber in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(364, 552);
            contentStream.drawString("found march");
            contentStream.endText();

            // this content stream places the flightnumber in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(364, 524);
            contentStream.drawString("found april");
            contentStream.endText();

            // this content stream places the destination in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(355, 497);
            contentStream.drawString("found may");
            contentStream.endText();

            // this content stream places the item name in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(320, 417);
            contentStream.drawString("found june");
            contentStream.endText();

            // this content stream places the brand of the lost luggage in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(322, 389);
            contentStream.drawString("found july");
            contentStream.endText();

            // this content stream places the color of the lost luggage in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(320, 362);
            contentStream.drawString("found august");
            contentStream.endText();

            // this content stream places the additional notes in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(378, 335);
            contentStream.drawString("found september");
            contentStream.endText();
            
              // this content stream places the additional notes in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(378, 300);
            contentStream.drawString("found october");
            contentStream.endText();
            
              // this content stream places the additional notes in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(378, 280);
            contentStream.drawString("found november");
            contentStream.endText();
            
              // this content stream places the additional notes in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(378, 260);
            contentStream.drawString("found december");
            contentStream.endText();
            

            // Make sure that the content stream is closed:
            contentStream.close();
            
            // this file chooser is used to select a directory to save to pdf
//            JFileChooser f = new JFileChooser();
//            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            f.showSaveDialog(null);
//
//            System.out.println(f.getCurrentDirectory());
//            System.out.println(f.getSelectedFile());
//            

            // Save the results and ensure that the document is properly closed:
            document.save("D:\\STATISTICS\\test.pdf");
            document.close();

        } catch (Exception ex) {
            System.out.println("exception 2 ");
            System.out.println(ex);
        }
        
        
    }
    
}
