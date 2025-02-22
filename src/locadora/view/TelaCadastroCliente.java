package locadora.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import locadora.controller.ClienteController;
import locadora.model.Cliente;

public class TelaCadastroCliente extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextArea areaListaClientes;
    private ClienteController clienteController;

    public TelaCadastroCliente() {
        // Inicializa o controlador
        clienteController = new ClienteController();

        // Configuração da tela
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Cadastro de Cliente");

        // Painel de fundo
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 450, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);

        // Labels e campos de entrada
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(80, 50, 100, 30);
        labelNome.setForeground(Color.WHITE);
        panel.add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(150, 50, 200, 30);
        panel.add(campoNome);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(80, 100, 100, 30);
        labelCpf.setForeground(Color.WHITE);
        panel.add(labelCpf);

        campoCpf = new JTextField();
        campoCpf.setBounds(150, 100, 200, 30);
        panel.add(campoCpf);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(80, 150, 100, 30);
        labelTelefone.setForeground(Color.WHITE);
        panel.add(labelTelefone);

        campoTelefone = new JTextField();
        campoTelefone.setBounds(150, 150, 200, 30);
        panel.add(campoTelefone);

        JLabel labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(80, 200, 100, 30);
        labelEmail.setForeground(Color.WHITE);
        panel.add(labelEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(150, 200, 200, 30);
        panel.add(campoEmail);

        // Botão de cadastrar
        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(50, 250, 150, 30);
        botaoCadastrar.setBackground(new Color(0, 128, 0));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(botaoCadastrar);

        // Botão de listar clientes
        JButton botaoListar = new JButton("Listar Clientes");
        botaoListar.setBounds(220, 250, 150, 30);
        botaoListar.setBackground(new Color(0, 0, 128));
        botaoListar.setForeground(Color.WHITE);
        botaoListar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoListar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(botaoListar);

        // Área de exibição de clientes
        areaListaClientes = new JTextArea();
        areaListaClientes.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaListaClientes);
        scrollPane.setBounds(50, 300, 340, 200);
        panel.add(scrollPane);

        // Ação do botão de cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        // Ação do botão de listar
        botaoListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        setVisible(true);
    }

    // Método para cadastrar clientes
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

        // Limpa os campos
        campoNome.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

    // Método para listar clientes
    private void listarClientes() {
        areaListaClientes.setText(clienteController.toString());
    }

    public static void main(String[] args) {
        new TelaCadastroCliente();
    }
}
