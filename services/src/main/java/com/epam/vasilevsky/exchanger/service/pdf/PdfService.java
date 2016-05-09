package com.epam.vasilevsky.exchanger.service.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfService {

	public static final String RESULT = "hello.pdf";

//	public static void main(String[] args) throws DocumentException, IOException {
//		new PdfService().createPdf(RESULT,"Hi People");
//	}

	public void createPdf(String filename, String text) throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		document.add(new Paragraph(text));
		document.close();
	}
	
	/*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        Document document = new Document();
        try {
            document.open();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment;filename=downloadname.pdf");
 
            document.add(new Paragraph("SLA: "));
            OutputStream os = response.getOutputStream();
 
            PdfWriter.getInstance(document, response.getOutputStream());
            Boolean a = document.isOpen();
            os.flush();
            os.close();
            document.close();
            
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } 
*/
}
