package locadora.relatorios;
import locadora.model.Locacao;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;

/*import java.security.ProtectionDomain;
import java.nio.file.*;*/
import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class  RelatorioLocacaoPDF {

    String diretorio;
    String dataHora;
    double valorTotal;

    // Método para gerar o relatório em formato de tabela
    public void gerarRelatorio(ArrayList<Locacao> locacoes) {

        try{
        // Gerar nome do arquivo baseado na data e hora
        diretorio = new File(RelatorioLocacaoPDF.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        diretorio = diretorio.substring(0, diretorio.lastIndexOf(File.separator));  // Remove o nome do JAR do caminho
        diretorio += "/Relatórios/";
        
        File folder = new File(diretorio);
        
        
        if (!folder.exists()) {
            folder.mkdirs(); // Cria a pasta Relatórios se não existir
            }
        }
        catch (URISyntaxException e){
            System.err.println("Erro de Entrada e Saída");
            e.printStackTrace();
        }

        dataHora = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String arquivoPdf = diretorio + "/Relatorio_Locacoes_" + dataHora + ".pdf"; // Nome do arquivo com data e hora
        File file = new File(arquivoPdf);

        try {
            // Criar o PdfWriter e o PdfDocument
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Título do relatório
            document.add(new Paragraph("Relatório de Locações").setFontSize(16));

            // Criando a tabela com 5 colunas
            Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

            // Cabeçalho da tabela
            table.addCell(new Cell().add(new Paragraph("ID da locação")));
            table.addCell(new Cell().add(new Paragraph("Nome")));
            table.addCell(new Cell().add(new Paragraph("CPF")));
            table.addCell(new Cell().add(new Paragraph("Data de Locação")));
            table.addCell(new Cell().add(new Paragraph("Data de Devolução")));
            table.addCell(new Cell().add(new Paragraph("Valor")));
            // Adicionar os dados das locações
            for (Locacao l : locacoes) {
                // Adicionar uma linha para cada locação
                valorTotal += l.getValorLocacao();

                table.addCell(String.format("%d", l.getId())); // ID
                table.addCell(l.getCliente().getNome()); // Nome do Cliente
                table.addCell(l.getCliente().getCpf()).setFontSize(10); //CPF do Cliente
                table.addCell(l.getDataLocacao().toString()); // Data de locação
                table.addCell(l.getDataDevolucao().toString()); // Data de Devolução
                table.addCell(String.format("R$ %d", l.getValorLocacao())); // Valor da Locação
            }

            table.addCell("---"); 
            table.addCell("---"); 
            table.addCell("---"); 
            table.addCell("---");
            table.addCell("---");
            table.addCell(String.format("Total: %.2f", valorTotal)); // Valor Total


            // Adicionar a tabela ao documento
            document.add(table);

            // Fechar o documento
            document.close();
            System.out.println("Relatório gerado com sucesso: " + arquivoPdf);

        } catch (Exception e) {
            System.err.println("Erro ao gerar o arquivo PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    }

