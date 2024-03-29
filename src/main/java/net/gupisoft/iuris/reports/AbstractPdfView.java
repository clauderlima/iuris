package net.gupisoft.iuris.reports;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AbstractPdfView extends AbstractView {
	
	public AbstractPdfView() {
		initView();
	}
	
	

	private void initView() {
		setContentType("application/pdf");
	}
	
	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}
	
	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		
		Document document = new Document(PageSize.A4);
		float marginLeft = 50;
		float marginRight = 50;
		float marginTop = 125;
		float marginBottom = 70;
		document.setMargins(marginLeft, marginRight, marginTop, marginBottom);
		
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		prepareWriter(model, writer, request);
		buildPdfMetadata(model, document, request);
		
		document.open();
		buildPdfDocument(model, document, writer, request, response);
		document.close();
		MarcaDAgua ma = new MarcaDAgua();
		
		writeToResponse(response, ma.manipulatePdf(baos));
	}
	
	protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request) throws DocumentException {
		writer.setViewerPreferences(getViewerPreferences());
	}
	
	protected int getViewerPreferences() {
		return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
	}
	
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
	}
	
	protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}
