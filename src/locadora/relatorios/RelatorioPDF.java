package locadora.relatorios;
import locadora.model.Pagamento;

import java.nio.file.Path;
import java.nio.file.Paths;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;

import java.security.ProtectionDomain;
import java.nio.file.*;
import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class  RelatorioPDF {

    String diretorio;
    String dataHora;

    // Método para gerar o relatório em formato de tabela
    public void gerarRelatorio(ArrayList<Pagamento> pagamentos) {

        try{
        // Gerar nome do arquivo baseado na data e hora
        diretorio = new File(RelatorioPDF.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
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
            document.add(new Paragraph("Relatório de Locações de Veículos").setFontSize(16));

            // Criando a tabela com 5 colunas
            Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();

            // Cabeçalho da tabela
            table.addCell(new Cell().add(new Paragraph("Nome")));
            table.addCell(new Cell().add(new Paragraph("Modelo de Carro")));
            table.addCell(new Cell().add(new Paragraph("Placa")));
            table.addCell(new Cell().add(new Paragraph("Data de Locação")));
            table.addCell(new Cell().add(new Paragraph("Valor")));

            // Adicionar os dados das locações
            for (Pagamento p : pagamentos) {
                // Adicionar uma linha para cada locação
                table.addCell(p.getLocacao().getCliente().getNome()); // Nome do Cliente
                table.addCell(p.getLocacao().getVeiculo().getModelo()); // Modelo do Carro
                table.addCell(p.getLocacao().getVeiculo().getPlaca()); // Placa do Carro
                table.addCell(p.getLocacao().getDataLocacao().toString()); // Data da Locação
                table.addCell(String.format("R$ %.2f", p.getValor())); // Valor da Locação
            }

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

