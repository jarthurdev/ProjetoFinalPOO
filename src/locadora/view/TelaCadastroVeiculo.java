package locadora.view;

import java.awt.*;
import javax.swing.*;
import locadora.controller.VeiculoController;
import locadora.dao.VeiculoDAO;
import locadora.model.Caminhao;
import locadora.model.Carro;
import locadora.model.Moto;
import locadora.model.Veiculo;

public class TelaCadastroVeiculo extends JDialog {

    private JTextField campoPlaca;
    private JTextField campoModelo;
    private JTextField campoAno;
    private JComboBox<String> comboTipo;
    private VeiculoController veiculoController;
    private JTextArea areaVeiculos;
    private VeiculoDAO veiculodao;

    public TelaCadastroVeiculo(JFrame parent) {
        super(parent, "Cadastro de Veículo", true);
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
        JLabel labelModelo = new JLabel("Modelo:");
        campoModelo = new JTextField();
        JLabel labelAno = new JLabel("Ano:");
        campoAno = new JTextField();
        JLabel labelTipo = new JLabel("Tipo:");
        comboTipo = new JComboBox<>(new String[]{"Carro", "Moto", "Caminhão"});
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoRemover = new JButton("Remover");
    
        areaVeiculos = new JTextArea();
        areaVeiculos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaVeiculos);
    
        panel.add(labelPlaca);
        panel.add(campoPlaca);
        panel.add(labelModelo);
        panel.add(campoModelo);
        panel.add(labelAno);
        panel.add(campoAno);
        panel.add(labelTipo);
        panel.add(comboTipo);
        panel.add(botaoCadastrar);
        panel.add(botaoRemover);
        panel.add(scrollPane);
    
        labelPlaca.setBounds(20, 20, 100, 20);
        labelPlaca.setForeground(Color.WHITE);
        campoPlaca.setBounds(120, 20, 250, 20);
        labelModelo.setBounds(20, 60, 100, 20);
        labelModelo.setForeground(Color.WHITE);
        campoModelo.setBounds(120, 60, 250, 20);
        labelAno.setBounds(20, 100, 100, 20);
        labelAno.setForeground(Color.WHITE);
        campoAno.setBounds(120, 100, 250, 20);
        labelTipo.setBounds(20, 140, 100, 20);
        labelTipo.setForeground(Color.WHITE);
        comboTipo.setBounds(120, 140, 250, 20);
        botaoCadastrar.setBounds(140, 180, 120, 30);
        botaoCadastrar.setBackground(new Color(0, 128, 0));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFocusPainted(false);
        botaoRemover.setBounds(140, 220, 120, 30);
        botaoRemover.setBackground(new Color(128, 0, 0));
        botaoRemover.setForeground(Color.WHITE);
        botaoRemover.setFocusPainted(false);
        scrollPane.setBounds(13, 300, 360, 250);
    
        botaoCadastrar.addActionListener(e -> cadastrarVeiculo());
        botaoRemover.addActionListener(e -> removerVeiculo());
    
        veiculoController.setListaVeiculos(veiculodao.carregarLista()); 
        listarVeiculos(); 
    
        setVisible(true);
    }
    

    private void cadastrarVeiculo() {
        String placa = campoPlaca.getText();
        String modelo = campoModelo.getText();
        Integer ano;

        try {
            ano = Integer.valueOf(campoAno.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipo = comboTipo.getSelectedItem().toString();
        Veiculo veiculo;

        switch (tipo) {
            case "Carro":
                veiculo = new Carro(placa, modelo, ano);
                break;
            case "Moto":
                veiculo = new Moto(placa, modelo, ano);
                break;
            case "Caminhão":
                veiculo = new Caminhao(placa, modelo, ano);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tipo de veículo inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
        }

        veiculoController.cadastrarVeiculo(veiculo);
        veiculodao.salvarLista(veiculoController.getListaVeiculos()); 
        JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");
        listarVeiculos();
    }

    private void removerVeiculo() {
        String placa = JOptionPane.showInputDialog(this, "Digite a placa do veículo para remover:");

        if (placa != null && !placa.trim().isEmpty()) {
            Veiculo veiculoRemovido = veiculoController.buscarVeiculoPorPlaca(placa);

            if (veiculoRemovido != null) {
                veiculoController.removerVeiculo(veiculoRemovido);
                veiculodao.salvarLista(veiculoController.getListaVeiculos());
                listarVeiculos(); 
                JOptionPane.showMessageDialog(this, "Veículo removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Veículo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Placa inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarVeiculos() {
        areaVeiculos.setText(""); 
        for (Veiculo veiculo : veiculoController.getListaVeiculos()) {
            areaVeiculos.append(veiculo.toString() + "\n"); 
        }
    }
}
