/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.toPDF;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import grupoj.prentrega1.Evento;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanp
 */
public class PdfCreator {
    
    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

    private ByteArrayOutputStream baosPdf;
    
    public PdfCreator(Evento ev) {
        Document doc = new Document();
        try {
            baosPdf = new ByteArrayOutputStream();
            PdfWriter.getInstance(doc, baosPdf);
            doc.open();
            addEvent(doc, ev);
            doc.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PdfCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void addEvent(Document doc, Evento ev) throws DocumentException {
        doc.addTitle(ev.getNombre());
        Paragraph evento = new Paragraph();
        evento.add(new Paragraph(ev.getNombre(), catFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(ev.getDescripcion(), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(ev.getFecha()), subFont));
        evento.add(new Paragraph(" "));
        evento.add(new Paragraph(ev.getDonde_comprar(), smallBold));
        evento.add(new Paragraph(" "));
        doc.add(evento);
    }
    
    public byte[] getStream() {
        return baosPdf.toByteArray();
    }
}
