package locadora.relatorios;
import locadora.model.Locacao;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class  RelatorioLocacaoPDF {

    String diretorio;
    String dataHora;
    double valorTotal;


    public void gerarRelatorio(ArrayList<Locacao> locacoes) {

        try{
 
        diretorio = new File(RelatorioLocacaoPDF.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
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
        String arquivoPdf = diretorio + "/Relatorio_Locacoes_" + dataHora + ".pdf"; 
        File file = new File(arquivoPdf);

        try {
        
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

     
            document.add(new Paragraph("Relatório de Locações").setFontSize(16));


            Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();


            table.addCell(new Cell().add(new Paragraph("ID da locação")));
            table.addCell(new Cell().add(new Paragraph("Nome")));
            table.addCell(new Cell().add(new Paragraph("CPF")));
            table.addCell(new Cell().add(new Paragraph("Data de Locação")));
            table.addCell(new Cell().add(new Paragraph("Data de Devolução")));
            table.addCell(new Cell().add(new Paragraph("Valor")));

            for (Locacao l : locacoes) {

                valorTotal += l.getValorLocacao();

                table.addCell(String.format("%d", l.getId())); 
                table.addCell(l.getCliente().getNome());
                table.addCell(l.getCliente().getCpf()).setFontSize(10); 
                table.addCell(l.getDataLocacao().toString()); 
                table.addCell(l.getDataDevolucao().toString()); 
                table.addCell(String.format("R$ %.2f", l.getValorLocacao())); 
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

