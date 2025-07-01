package negocio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import persistencia.FisioterapeutaDAO;

public class Relatorio {

    public void relatorioFisioterapeutas() throws SQLException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));
            document.open();
            ArrayList<Fisioterapeuta> vetFisioterapeuta = new FisioterapeutaDAO().listarAtivos();
            for (int i = 0; i < vetFisioterapeuta.size(); i++) {
                document.add(new Paragraph(vetFisioterapeuta.get(i).getNome()));
            }
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
}
