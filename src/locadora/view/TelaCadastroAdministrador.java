package locadora.view;

import java.awt.*;
import javax.swing.*;
import locadora.controller.FuncionarioController;
import locadora.dao.FuncionarioDAO;
import locadora.model.Administrador;

public class TelaCadastroAdministrador extends JDialog {

    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmarSenha;
    private FuncionarioController funcionarioController;
    private FuncionarioDAO funcionarioDAO;

    public TelaCadastroAdministrador(JFrame parent) {
        super(parent, "Cadastro de Administrador", true);
        funcionarioController = new FuncionarioController();
        funcionarioDAO = new FuncionarioDAO();
        
        // Configurações básicas da tela
        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);

        // Painel de fundo
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 300);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);

        // Componentes de interface
        JLabel labelLogin = new JLabel("Login:");
        campoLogin = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();
        JLabel labelConfirmarSenha = new JLabel("Confirmar Senha:");
        campoConfirmarSenha = new JPasswordField();
        JButton botaoCadastrar = new JButton("Cadastrar");

        // Adicionando componentes ao painel
        panel.add(labelLogin);
        panel.add(campoLogin);
        panel.add(labelSenha);
        panel.add(campoSenha);
        panel.add(labelConfirmarSenha);
        panel.add(campoConfirmarSenha);
        panel.add(botaoCadastrar);

        // Definindo o posicionamento e tamanho dos componentes
        labelLogin.setBounds(20, 20, 100, 20);
        labelLogin.setForeground(Color.WHITE);
        campoLogin.setBounds(120, 20, 250, 20);
        labelSenha.setBounds(20, 60, 100, 20);
        labelSenha.setForeground(Color.WHITE);
        campoSenha.setBounds(120, 60, 250, 20);
        labelConfirmarSenha.setBounds(20, 100, 150, 20);
        labelConfirmarSenha.setForeground(Color.WHITE);
        campoConfirmarSenha.setBounds(170, 100, 200, 20);
        botaoCadastrar.setBounds(20, 140, 350, 30);

        // Ação do botão Cadastrar
        botaoCadastrar.addActionListener(e -> cadastrarAdministrador());

        // Tornando a tela visível
        setVisible(true);
    }

    private void cadastrarAdministrador() {
        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();
        String confirmarSenha = new String(campoConfirmarSenha.getPassword()).trim();

        // Validação dos campos
        if (login.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chamando o método do controller para cadastrar o administrador
            funcionarioController.cadastrarAdministrador(login, senha, "Administrador");

            // Salvando a lista de funcionários no arquivo JSON
            funcionarioDAO.salvarLista(funcionarioController.getFuncionarios()); 

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Administrador cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Fechando a tela
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar administrador.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
