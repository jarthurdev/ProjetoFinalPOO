package locadora.relatorios;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import locadora.model.Pagamento;

public class RelatorioPagamentosPDF {


    String diretorio;
    String dataHora;
    double valorTotal;

    public void gerarRelatorio(ArrayList<Pagamento> pagamentos) {

        try{

        diretorio = new File(RelatorioPagamentosPDF.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        diretorio = diretorio.substring(0, diretorio.lastIndexOf(File.separator)); 
        diretorio += "/Relatórios/";
        
        File folder = new File(diretorio);
        
        
        if (!folder.exists()) {
            folder.mkdirs(); 
            }
        }
        catch (URISyntaxException e){
            System.err.println("Erro de Entrada e Saída");
            e.printStackTrace();
        }

        dataHora = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String arquivoPdf = diretorio + "/Relatorio_Pagamentos_" + dataHora + ".pdf"; 
        File file = new File(arquivoPdf);

        try {

            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("Relatório de Pagamentos").setFontSize(16));

            Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

            table.addCell(new Cell().add(new Paragraph("ID do Pagamento")));
            table.addCell(new Cell().add(new Paragraph("Nome")));
            table.addCell(new Cell().add(new Paragraph("CPF")));
            table.addCell(new Cell().add(new Paragraph("Data de Pagamento")));
            table.addCell(new Cell().add(new Paragraph("Método")));
            table.addCell(new Cell().add(new Paragraph("Valor")));

            for (Pagamento p : pagamentos) {

                valorTotal += p.getValor();

                table.addCell(String.format("%d", p.getId())); 
                table.addCell(p.getLocacao().getCliente().getNome()); 
                table.addCell(p.getLocacao().getCliente().getCpf()).setFontSize(10); 
                table.addCell(p.getDataPagamento().toString()); 
                table.addCell(p.getMetodo().toString()); 
                table.addCell(String.format("R$ %.2f", p.getValor())); 
            }

            table.addCell("---"); 
            table.addCell("---"); 
            table.addCell("---"); 
            table.addCell("---"); 
            table.addCell("---");
            table.addCell(String.format("Total: %.2f", valorTotal)); 

            document.add(table);

            document.close();
            System.out.println("Relatório gerado com sucesso: " + arquivoPdf);

        } catch (Exception e) {
            System.err.println("Erro ao gerar o arquivo PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

}
