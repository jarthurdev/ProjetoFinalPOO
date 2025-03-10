package locadora.view;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import locadora.controller.ClienteController;
import locadora.controller.LocacaoController;
import locadora.controller.VeiculoController;
import locadora.dao.ClienteDAO;
import locadora.dao.LocacaoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.Cliente;
import locadora.model.Locacao;
import locadora.model.Veiculo;

public class TelaCadastroLocacao extends JDialog {

    private JTextField campoPlaca;
    private JTextField campoNomeCliente;
    private JTextField campoDatadeDevolucao;
    private JTextField campoDatadeLocacao;
    private VeiculoController veiculoController; 
    private JTextArea areaVeiculos;
    private JTextArea areaClientes;
    private VeiculoDAO veiculodao; 
    private ClienteController clienteController;
    private LocacaoDAO locacaodao;
    private LocacaoController locacaoController;
    private ClienteDAO clientedao;

    public TelaCadastroLocacao(JFrame parent) {
        super(parent, "Locação", true);
        setSize(400, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);

        veiculoController = new VeiculoController(); 
        veiculodao = new VeiculoDAO(); 
        clienteController = new ClienteController();
        locacaoController = new LocacaoController();
        locacaodao = new LocacaoDAO();
        locacaoController.setListaLocacoes(locacaodao.carregarLista());
        clientedao = new ClienteDAO();

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);

        JLabel labelPlaca = new JLabel("Placa:");
        campoPlaca = new JTextField();
        JLabel labelNomeCliente = new JLabel("Nome:");
        campoNomeCliente = new JTextField();
        JLabel labelDatadeDevolucao = new JLabel("Data de devolução \n (YYYY-MM-DD): ");
        campoDatadeDevolucao = new JTextField();
        JLabel labelDatadeLocacao = new JLabel("Data de locação \n (YYYY-MM-DD): ");
        campoDatadeLocacao = new JTextField();
        JButton botaoRegistrar = new JButton("Registrar");
        JLabel labelDisponiveis = new JLabel("Veículos disponíveis:");
        JLabel labelClientes = new JLabel("Clientes:");
        
        areaVeiculos = new JTextArea(); 
        areaClientes = new JTextArea(); 
        areaClientes.setEditable(false);
        JScrollPane scrollPaneV = new JScrollPane(areaVeiculos);
        JScrollPane scrollPaneC = new JScrollPane(areaClientes);

        panel.add(labelPlaca);
        panel.add(campoPlaca);
        panel.add(labelNomeCliente);
        panel.add(campoNomeCliente);
        panel.add(campoDatadeDevolucao);
        panel.add(labelDatadeDevolucao);
        panel.add(labelDatadeLocacao);
        panel.add(campoDatadeLocacao);
        panel.add(scrollPaneV);
        panel.add(scrollPaneC);
        panel.add(botaoRegistrar);
        panel.add(labelDisponiveis);
        panel.add(labelClientes);

        labelPlaca.setBounds(20, 20, 100, 20);
        labelPlaca.setForeground(Color.WHITE);
        campoPlaca.setBounds(120, 20, 250, 20);
        labelNomeCliente.setBounds(20, 60, 100, 20);
        labelNomeCliente.setForeground(Color.WHITE);
        campoNomeCliente.setBounds(120, 60, 250, 20);

        labelDatadeDevolucao.setBounds(20, 140, 200, 20);
        labelDatadeDevolucao.setForeground(Color.WHITE);
        campoDatadeDevolucao.setBounds(250, 140, 100, 20);

        labelDatadeLocacao.setBounds(20, 100, 200, 20);
        labelDatadeLocacao.setForeground(Color.WHITE);
        campoDatadeLocacao.setBounds(250, 100, 100, 20);

        scrollPaneV.setBounds(13, 300, 360, 100);
        scrollPaneC.setBounds(13, 440, 360, 100);
        botaoRegistrar.setBounds(120, 200, 150, 30);
        botaoRegistrar.setBackground(new Color(0, 128, 0));
        botaoRegistrar.setForeground(Color.WHITE);
        botaoRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoRegistrar.setFocusPainted(false);
        labelDisponiveis.setBounds(20, 280, 200, 20);
        labelDisponiveis.setForeground(Color.WHITE);
        labelClientes.setBounds(20, 420, 200, 20);
        labelClientes.setForeground(Color.WHITE);

        veiculoController.setListaVeiculos(veiculodao.carregarLista()); 
        listarVeiculosDisponiveis(); 

        clienteController.setListaClientes(clientedao.carregarLista()); 
        for (Cliente cliente : clienteController.getListaClientes()) {
            areaClientes.append(cliente.toString() + "\n \n");
        }

        botaoRegistrar.addActionListener(e -> {
            if (campoPlaca.getText().isEmpty() || campoNomeCliente.getText().isEmpty() || campoDatadeDevolucao.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String placa = campoPlaca.getText();
            Veiculo veiculo = veiculoController.buscarVeiculoPorPlaca(placa);
            LocalDate dataDevolucao = LocalDate.parse(campoDatadeDevolucao.getText());
            LocalDate datadeLocacao = LocalDate.parse(campoDatadeLocacao.getText());
            Cliente cliente = clienteController.buscarCliente(campoNomeCliente.getText());

            if (veiculo != null && cliente != null) {
                veiculoController.alterarStatusVeiculoPorPlaca(placa); 
                veiculodao.salvarLista(veiculoController.getListaVeiculos()); 
                listarVeiculosDisponiveis(); 
                JOptionPane.showMessageDialog(this, "Veículo registrado com sucesso!");

                locacaoController.adicionarLocacao(new Locacao(veiculo, cliente, datadeLocacao, dataDevolucao));

                locacaodao.salvarLista(locacaoController.getlistaLocacoes());
            } else {
                JOptionPane.showMessageDialog(this, "Veículo ou cliente não encontrado!");
            }
        });

        setVisible(true);
    }

    private void listarVeiculosDisponiveis() {
        areaVeiculos.setText(""); 
        for (Veiculo veiculo : veiculoController.listarVeiculosDisponiveis()) {
            areaVeiculos.append(veiculo.toString() + "\n");
        }
    }
}
