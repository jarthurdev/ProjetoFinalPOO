package locadora.view;

import java.awt.*;
import javax.swing.*;
import locadora.controller.ClienteController;
import locadora.model.Cliente;

public class TelaCadastroCliente extends JDialog {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private ClienteController clienteController;
    private JTextArea areaClientes;

    public TelaCadastroCliente(JFrame parent) {
        super(parent, "Cadastro de Cliente", true);
        setSize(400, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);

        clienteController = new ClienteController();

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();
        JLabel labelCpf = new JLabel("CPF:");
        campoCpf = new JTextField();
        JLabel labelTelefone = new JLabel("Telefone:");
        campoTelefone = new JTextField();
        JLabel labelEmail = new JLabel("E-mail:");
        campoEmail = new JTextField();
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoListar = new JButton("Listar Clientes");
        JButton botaoRemover = new JButton("Remover Cliente");
        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaClientes);

        panel.add(labelNome);
        panel.add(campoNome);
        panel.add(labelCpf);
        panel.add(campoCpf);
        panel.add(labelTelefone);
        panel.add(campoTelefone);
        panel.add(labelEmail);
        panel.add(campoEmail);
        panel.add(botaoCadastrar);
        panel.add(botaoListar);
        panel.add(botaoRemover);
        panel.add(scrollPane);

        labelNome.setBounds(20, 20, 100, 20);
        labelNome.setForeground(Color.WHITE);
        campoNome.setBounds(120, 20, 250, 20);
        labelCpf.setBounds(20, 60, 100, 20);
        labelCpf.setForeground(Color.WHITE);
        campoCpf.setBounds(120, 60, 250, 20);
        labelTelefone.setBounds(20, 100, 100, 20);
        labelTelefone.setForeground(Color.WHITE);
        campoTelefone.setBounds(120, 100, 250, 20);
        labelEmail.setBounds(20, 140, 100, 20);
        labelEmail.setForeground(Color.WHITE);
        campoEmail.setBounds(120, 140, 250, 20);
        botaoCadastrar.setBounds(140, 180, 120, 30);
        botaoCadastrar.setBackground(new Color(0, 128, 0));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFocusPainted(false);
        botaoListar.setBounds(140, 220, 120, 30);
        botaoListar.setBackground(new Color(0, 0, 128));
        botaoListar.setForeground(Color.WHITE);
        botaoListar.setFocusPainted(false);
        botaoRemover.setBounds(140, 260, 120, 30);
        botaoRemover.setBackground(new Color(128, 0, 0));
        botaoRemover.setForeground(Color.WHITE);
        scrollPane.setBounds(13, 300, 360, 250);

        botaoCadastrar.addActionListener(e -> cadastrarCliente());
        botaoListar.addActionListener(e -> listarClientes());
        botaoRemover.addActionListener(e -> removerCliente());

        setVisible(true);
    }

    private void cadastrarCliente() {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String email = campoEmail.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, cpf, telefone, email);
        clienteController.cadastrarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        listarClientes();
    }

    private void listarClientes() {
        areaClientes.setText(clienteController.toString());
    }

    private void removerCliente() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente para remover:");
        if (nome != null && !nome.trim().isEmpty()) {
            boolean removido = clienteController.removerCliente(nome);
            if (removido) {
                JOptionPane.showMessageDialog(this, "Cliente removido com sucesso!");
                listarClientes();
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}