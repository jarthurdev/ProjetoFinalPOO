package locadora.view;

import java.awt.*;
import javax.swing.*;
import locadora.dao.*;
import locadora.relatorios.*;

public class TelaRelatorios extends JDialog {
    
    private RelatorioLocacaoPDF locacaoPDF;
    private RelatorioVeiculosPDF veiculosPDF;
    private RelatorioPagamentosPDF pagamentosPDF;
    private LocacaoDAO locacaoDAO;
    private VeiculoDAO veiculoDAO;
    private PagamentoDAO pagamentoDAO;

    public void carregarDados() {
        locacaoPDF = new RelatorioLocacaoPDF();
        veiculosPDF = new RelatorioVeiculosPDF();
        pagamentosPDF = new RelatorioPagamentosPDF();
        locacaoDAO = new LocacaoDAO();
        veiculoDAO = new VeiculoDAO();
        pagamentoDAO = new PagamentoDAO();
    }

    public TelaRelatorios(JFrame parent) {
        super(parent, "Gerar Relatórios", true);
        carregarDados();
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);

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
        estilizarBotao(button);
        panel.add(button);
        
        button.addActionListener(l -> veiculosPDF.gerarRelatorio(veiculoDAO.carregarLista()));
    }

    private void gerarRelatorioFaturamento(JPanel panel) {
        JButton button = new JButton("Faturamento mensal");
        button.setBounds(100, 150, 200, 30);
        estilizarBotao(button);
        panel.add(button);
        
        button.addActionListener(l -> pagamentosPDF.gerarRelatorio(pagamentoDAO.carregarLista()));
    }

    private void gerarRelatorioClientes(JPanel panel) {
        JButton button = new JButton("Clientes e locações");
        button.setBounds(100, 200, 200, 30);
        estilizarBotao(button);
        panel.add(button);
        
        button.addActionListener(l -> locacaoPDF.gerarRelatorio(locacaoDAO.carregarLista()));
    }

    private void estilizarBotao(JButton button) {
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusable(false);
    }
}
