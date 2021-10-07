package net.gupisoft.iuris.reports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class MarcaDAgua {

	public ByteArrayOutputStream manipulatePdf(ByteArrayOutputStream baos) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(baos.toByteArray());
        int n = reader.getNumberOfPages();
        
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        
        PdfStamper stamper = new PdfStamper(reader, result);
        stamper.setRotateContents(false);
        
        // image watermark
        String imageFile = "src/main/resources/static/vendors/img/logoIara.png";
        Image img = Image.getInstance(imageFile);
        float w = img.getScaledWidth()/2;
        float h = img.getScaledHeight()/2;
        
        Image imgTopo =  Image.getInstance(imageFile);
        float wTopo = img.getScaledWidth()/6;
        float hTopo = img.getScaledHeight()/6;
        
        // transparency
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.2f);
        PdfGState gs2 = new PdfGState();
        gs2.setFillOpacity(1f);
        
        // properties
        PdfContentByte over;
        Rectangle pagesize;
        float x, y;
        float xTopo=230, yTopo=725;
        
        // loop over every page
        for (int i = 1; i <= n; i++) {
            pagesize = reader.getPageSize(i);
            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = stamper.getOverContent(i);
            over.saveState();
            over.setGState(gs1);
            over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
            over.setGState(gs2);
            over.addImage(imgTopo, wTopo, 0, 0, hTopo, xTopo, yTopo);
            over.restoreState();
        }
        
        stamper.close();
        reader.close();
        return result;
    }
}
