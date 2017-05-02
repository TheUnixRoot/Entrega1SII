/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupoj.entregajsf.toPDF;

import grupoj.prentrega1.Evento;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 *
 * @author juanp
 */
public class Tester {
    public static void main(String[] args) {
        Evento ev = new Evento();
        ev.setNombre("Evento de prueba");
        PdfCreator pdf = new PdfCreator(ev);
        try {
            FileOutputStream ou = new FileOutputStream(new File("evento.pdf")) {};
            ou.write(pdf.getStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
