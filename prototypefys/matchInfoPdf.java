/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypefys;

import cataloog.FoundLuggage;
import cataloog.LostLuggage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author IS-109-2
 */
public class matchInfoPdf {

    matchInfoPdf() {

    }
    private matchInformatie matchinfo = new matchInformatie();

    /**
     *
     * @param person a instance of the LostLuggage class
     * @param foundInfo a instance of the FoundLuggage class
     */
    public void maakMatchPdf(LostLuggage person, FoundLuggage foundInfo) {
        try {

            File pdfTemplate = new File("src\\resources\\pdf\\MatchInformation.pdf");

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
            contentStream.moveTextPositionByAmount(130, 615);
            contentStream.drawString(person.getFirstName());
            contentStream.endText();

            // this content stream places the insertion of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(122, 601);
            contentStream.drawString(person.getInsertion());
            contentStream.endText();

            // this content stream places the last name of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(130, 587);
            contentStream.drawString(person.getLastName());

            contentStream.endText();

            // this content stream places the first phone numberer of the 
            // customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(158, 574);
            contentStream.drawString(matchinfo.getOwnerinfo(person.getOwnerid(), 5));
            contentStream.endText();

            // this content stream places the second phone numberer of the 
            // customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(158, 561);
            contentStream.drawString(matchinfo.getOwnerinfo(person.getOwnerid(), 6));
            contentStream.endText();

            // this content stream places the email of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(108, 548);
            contentStream.drawString(matchinfo.getOwnerinfo(person.getOwnerid(), 7));
            contentStream.endText();

            // this content stream places the adress of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(118, 534);
            contentStream.drawString(matchinfo.getAddressInfo(person.getOwnerid(), 2));
            contentStream.endText();

            // this content stream places the city of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(100, 521);
            contentStream.drawString(matchinfo.getAddressInfo(person.getOwnerid(), 4));
            contentStream.endText();

            // this content stream places the zipcode of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(120, 507);
            contentStream.drawString(matchinfo.getAddressInfo(person.getOwnerid(), 3));
            contentStream.endText();

            // this content stream places the country of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(118, 493);
            contentStream.drawString(matchinfo.getAddressInfo(person.getOwnerid(), 5));
            contentStream.endText();

            // this content stream places the additional notes of the customer in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(90, 470);
            contentStream.drawString(matchinfo.getOwnerinfo(person.getOwnerid(), 7));
            contentStream.endText();
            
            
            // this content stream places the airport name where 
            // the bagagage was reported lost in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(115, 655);
            contentStream.drawString(person.getAirport());
            contentStream.endText();
            
            // this content stream places the time lost in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(125, 668);
            contentStream.drawString(new SimpleDateFormat("HH.mm.ss").format(new Date()));
            contentStream.endText();

            // this content stream places the date lost in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(125, 681);
            contentStream.drawString(person.getDateLost());
            contentStream.endText();

            // this content stream places the labelnumber in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(369, 681);
            contentStream.drawString(Integer.toString(person.getLabelnr()));
            contentStream.endText();

            // this content stream places the flightnumber in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(370, 668);
            contentStream.drawString(Integer.toString(person.getFlightnr()));
            contentStream.endText();

            // this content stream places the destination in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(360, 655);
            contentStream.drawString(person.getDestination());
            contentStream.endText();

            // this content stream places the date found in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(360, 616);
            contentStream.drawString(foundInfo.getDateFound());
            contentStream.endText();
            
            // this content stream places the time found in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(360, 602);
            contentStream.drawString(foundInfo.getTimeFound());
            contentStream.endText();
            
            // this content stream places the airport where the luggage is 
            // found in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(370, 588);
            contentStream.drawString(foundInfo.getAirport());
            contentStream.endText();
           
            
            
            // this content stream places the item name in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(332, 547);
            contentStream.drawString(person.getItemname());
            contentStream.endText();

            // this content stream places the brand of the lost luggage in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(336, 534);
            contentStream.drawString(person.getBrand());
            contentStream.endText();

            // this content stream places the color of the lost luggage in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(332, 520);
            contentStream.drawString(person.getColors());
            contentStream.endText();

            // this content stream places the additional notes in the pdf
            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.moveTextPositionByAmount(310, 498);
            contentStream.drawString(person.getDescription());
            contentStream.endText();

            // Make sure that the content stream is closed:
            contentStream.close();

            
           
            
            // Save the results and ensure that the document is properly closed:
            document.save("src\\resources\\pdf\\filledinConfirmation\\FilledMatchInformation.pdf");
            document.close();

        } catch (Exception ex) {
            System.out.println("failed to create a match information form ");
            System.out.println(ex);
        }
    }

}
