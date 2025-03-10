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

import locadora.model.Veiculo;

public class RelatorioVeiculosPDF {

    String diretorio;
    String dataHora;


    public void gerarRelatorio(ArrayList<Veiculo> veiculos) {

        try{

        diretorio = new File(RelatorioVeiculosPDF.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
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
        String arquivoPdf = diretorio + "/Relatorio_Veiculos_" + dataHora + ".pdf"; 
        File file = new File(arquivoPdf);

        try {
      
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
    
            document.add(new Paragraph("Relatório de Veículos").setFontSize(16));

            Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

            table.addCell(new Cell().add(new Paragraph("Tipo")));
            table.addCell(new Cell().add(new Paragraph("Modelo")));
            table.addCell(new Cell().add(new Paragraph("Placa")));
            table.addCell(new Cell().add(new Paragraph("Ano")));
            table.addCell(new Cell().add(new Paragraph("Valor /Dia")));
            table.addCell(new Cell().add(new Paragraph("Status")));

            for (Veiculo v : veiculos) {

   
                table.addCell(v.getClass().getSimpleName());
                table.addCell(v.getModelo()); 
                table.addCell(v.getPlaca()).setFontSize(10); 
                table.addCell(String.format("%d", v.getAno())); 
                table.addCell(String.format("%d", v.calcularCustoLocacao())); 
                table.addCell((v.isStatus() ? "Disponível" : "Locado")); 
            }
            
            document.add(table);

            document.close();
            System.out.println("Relatório gerado com sucesso: " + arquivoPdf);

        } catch (Exception e) {
            System.err.println("Erro ao gerar o arquivo PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
