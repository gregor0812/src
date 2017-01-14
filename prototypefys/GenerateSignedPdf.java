/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author Koen Hengsdijk
 */
public class GenerateSignedPdf {

    public GenerateSignedPdf() {
    }

    public void MakePdf() {
        
        try {
        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
 
// Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" 
        // the to be created content
        PDPageContentStream contentStream =
            new PDPageContentStream(document, page);

        // Define a text content stream using the selected font, 
        //moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("employee name: ");
        contentStream.endText();
        
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("date: ");
        contentStream.endText();
        
        
        
        
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 600);
        contentStream.drawString("Hello World");
        contentStream.endText();
        
        
        
        
        
        
        // Make sure that the content stream is closed:
        contentStream.close();
        
        // Save the results and ensure that the document is properly closed:
        document.save("D:\\software\\Hello World.pdf");
        document.close();
        
        } catch (Exception ex){
            System.out.println("exception 2 ");
            System.out.println(ex);
        }
               

    }

}
