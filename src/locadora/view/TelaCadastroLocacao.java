package locadora.view;

import java.awt.*;
import javax.swing.*;
import locadora.controller.VeiculoController;
import locadora.dao.VeiculoDAO;
import locadora.model.Veiculo;

public class TelaCadastroLocacao extends JDialog {

    private JTextField campoPlaca;
    private JTextField campoNomeCliente;
    private VeiculoController veiculoController;
    private JTextArea areaVeiculos;
    private VeiculoDAO veiculodao;

    public TelaCadastroLocacao(JFrame parent) {
        super(parent, "Locação", true);
        setSize(400, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);
    
        veiculoController = new VeiculoController();
        veiculodao = new VeiculoDAO();

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);
    
        JLabel labelPlaca = new JLabel("Placa:");
        campoPlaca = new JTextField();
        JLabel labelNomeCliente = new JLabel("Nome:");
        campoNomeCliente = new JTextField();
        JButton botaoRegistrar = new JButton("Registrar");

    
        areaVeiculos = new JTextArea(); // 🔴 AGORA ESTÁ INICIALIZADA ANTES DE CHAMAR listarVeiculos()
        areaVeiculos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaVeiculos);
    
        panel.add(labelPlaca);
        panel.add(campoPlaca);
        panel.add(labelNomeCliente);
        panel.add(campoNomeCliente);
        panel.add(scrollPane);
        panel.add(botaoRegistrar);
    
        labelPlaca.setBounds(20, 20, 100, 20);
        labelPlaca.setForeground(Color.WHITE);
        campoPlaca.setBounds(120, 20, 250, 20);
        labelNomeCliente.setBounds(20, 60, 100, 20);
        labelNomeCliente.setForeground(Color.WHITE);
        campoNomeCliente.setBounds(120, 60, 250, 20);
        scrollPane.setBounds(13, 300, 360, 250);
        botaoRegistrar.setBounds(120, 100, 150, 30);
        botaoRegistrar.setBackground(new Color(0, 128, 0));
    
        veiculoController.setListaVeiculos(veiculodao.carregarLista()); // 🔵 MOVIDO PARA AQUI, APÓS A INICIALIZAÇÃO DOS COMPONENTES
        listarVeiculosDisponiveis(); // 🔵 AGORA NÃO VAI CAUSAR NullPointerException
    
        setVisible(true);
    }

    private void listarVeiculosDisponiveis() {
        areaVeiculos.setText(""); // Limpa a área de texto antes de listar
        for (Veiculo veiculo : veiculoController.listarVeiculosDisponiveis()) {
            areaVeiculos.append(veiculo.toString() + "\n"); // Adiciona cada veículo à área de texto
        }
    }
}
