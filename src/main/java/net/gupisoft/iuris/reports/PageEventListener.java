package net.gupisoft.iuris.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PageEventListener extends PdfPageEventHelper {

	Font ffont = new Font(Font.FontFamily.UNDEFINED, 9, Font.ITALIC);
    
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        
//        Phrase header = new Phrase("NFI Assessoria", ffont);
        
        Phrase footer = new Phrase("______________________________________________________________________________");
        Phrase footer1 = new Phrase("Condomínio Jardim Ipanema, quadra 09, casa 04 ", ffont);
        Phrase footer2 = new Phrase("Sobradinho - DF | CEP 73.092-903 ", ffont);
        Phrase footer3 = new Phrase("Fone: (61) 9 9994-2999 / (62) 9 9299-5697", ffont);
        
//        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//                header,
//                (document.right() - document.left()) / 2 + document.leftMargin(),
//                document.top() + 20, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer3,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 40, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer2,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 28, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer1,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 16, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                footer,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 2, 0);
        
        
        PdfPTable table = new PdfPTable(1);
        //table.setWidths(new int[]{24, 24, 2});
		table.setTotalWidth(500);
		table.getDefaultCell().setFixedHeight(20);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		//table.addCell(new Phrase(String.format("Página %d de 8", writer.getPageNumber()), ffont));
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);
		//table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		//table.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), ffont));
		//PdfPCell cell = new PdfPCell(new Phrase("8"));
		//cell.setBorder(Rectangle.BOTTOM);
		//table.addCell(cell);
		PdfContentByte canvas = writer.getDirectContent();
		canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
		table.writeSelectedRows(0, -1, 36, 30, canvas);
		canvas.endMarkedContentSequence();
    }
    
    
}