package locadora.view;

import java.awt.*;
import javax.swing.*;
import locadora.controller.ClienteController;
import locadora.controller.LocacaoController;
import locadora.controller.VeiculoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.Locacao;

import java.util.ArrayList;

public class TelaDevolucao extends JDialog {

    private JTextField campoId;
    private JTextField campoDatadeDevolucao;
    private VeiculoController veiculoController;
    private JTextArea areaLocacao;
    private VeiculoDAO veiculodao;
    private ClienteController clienteController;
    private LocacaoDAO locacaodao;
    private LocacaoController locacaoController;
    private ArrayList<Locacao> locacoes;

    public void carregarDados(){

        veiculoController = new VeiculoController();
        veiculodao = new VeiculoDAO();
        clienteController = new ClienteController();
        locacaoController = new LocacaoController();
        locacaodao = new LocacaoDAO();
        locacaodao.carregarDados(locacaoController);
    
        locacoes = locacaoController.getListaLocacoes();
    }




    public TelaDevolucao(JFrame parent) {
       
        super(parent, "Locação", true);
        carregarDados();
        setSize(400, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);

        

        // Criação do painel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);

        // Criação dos componentes
        JLabel labelId = new JLabel("ID da locação:");
        campoId = new JTextField();

        JLabel labelDatadeDevolucao = new JLabel("Data de devolução \n (YYYY-MM-DD): ");
        campoDatadeDevolucao = new JTextField();

        JButton botaoRegistrar = new JButton("Registrar");
        
        areaLocacao = new JTextArea(); // Área de texto para exibir veículos
        areaLocacao.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaLocacao);

        // Adicionando componentes ao painel
        panel.add(labelId);
        panel.add(campoId);
        panel.add(labelDatadeDevolucao);
        panel.add(campoDatadeDevolucao);
        panel.add(botaoRegistrar);
        panel.add(scrollPane);

        // Definindo posições dos componentes
        labelId.setBounds(20, 20, 100, 20);
        labelId.setForeground(Color.WHITE);
        campoId.setBounds(120, 20, 250, 20);
        labelDatadeDevolucao.setBounds(20, 60, 200, 20);
        labelDatadeDevolucao.setForeground(Color.WHITE);
        campoDatadeDevolucao.setBounds(270, 60, 100, 20);
        scrollPane.setBounds(20, 100, 350, 400);
        botaoRegistrar.setBounds(20, 520, 350, 30);

        // Ação do botão Registrar
        botaoRegistrar.addActionListener(e -> {});

        listarTodasLocacoes();

        // Tornando a tela visível
        setVisible(true);

     
       // Lista todas as locações ao iniciar a tela
    }

    // Método para listar os veículos disponíveis (status == true)
    private void listarTodasLocacoes() {
        areaLocacao.setText(""); // Limpa a área de texto antes de listar
        for (Locacao locacao : locacaoController.getListaLocacoes()) {
            areaLocacao.append(locacao.toString() + "\n"); // Adiciona cada veículo à área de texto
        }
    }
}
