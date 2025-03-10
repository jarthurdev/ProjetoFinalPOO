package locadora.view;

import locadora.dao.*;
import locadora.relatorios.*;
import java.awt.*;
import javax.swing.*;

public class TelaRelatorios extends JFrame {
    
    RelatorioLocacaoPDF locacaoPDF;
    RelatorioVeiculosPDF veiculosPDF;
    RelatorioPagamentosPDF pagamentosPDF;
    LocacaoDAO locacaoDAO;
    VeiculoDAO veiculoDAO;
    PagamentoDAO pagamentoDAO;

    public void carregarDados(){

        locacaoPDF = new RelatorioLocacaoPDF();
        veiculosPDF = new RelatorioVeiculosPDF();
        pagamentosPDF = new RelatorioPagamentosPDF();
        locacaoDAO = new LocacaoDAO();
        veiculoDAO = new VeiculoDAO();
        pagamentoDAO = new PagamentoDAO();

    }

    public TelaRelatorios() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Gerar Relatórios");
        setResizable(false);

        carregarDados();

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);
        
        addLabel(panel);
        gerarRelatorioVeiculos(panel);
        gerarRelatorioFaturamento(panel);
        gerarRelatorioClientes(panel);
        
        setVisible(true);
    }

    private void addLabel(JPanel panel) { 
        JLabel label = new JLabel("Escolha um relatório:");
        label.setBounds(100, 50, 250, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label);
    }

    private void gerarRelatorioVeiculos(JPanel panel) {
        JButton button = new JButton("Veículos locados");
        button.setBounds(100, 100, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        panel.add(button);
        
        button.addActionListener(l -> veiculosPDF.gerarRelatorio(veiculoDAO.carregarLista()));
    }

    private void gerarRelatorioFaturamento(JPanel panel) {
        JButton button = new JButton("Faturamento mensal");
        button.setBounds(100, 150, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        panel.add(button);
        
        button.addActionListener(l -> pagamentosPDF.gerarRelatorio(pagamentoDAO.carregarLista()));
    }

    private void gerarRelatorioClientes(JPanel panel) {
        JButton button = new JButton("Clientes e locações");
        button.setBounds(100, 200, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
        panel.add(button);
        
        button.addActionListener(l -> locacaoPDF.gerarRelatorio(locacaoDAO.carregarLista()));
    }
    
    /* 
    private void gerarRelatorio(String tipo) {
        JOptionPane.showMessageDialog(this, "Gerando relatório: " + tipo, "Relatório", JOptionPane.INFORMATION_MESSAGE);
    }
*/

}
