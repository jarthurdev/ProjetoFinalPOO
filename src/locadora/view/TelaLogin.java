package locadora.view;

import java.awt.*;
import javax.swing.*;

public class TelaLogin extends JFrame{

    public TelaLogin() {

        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 400);
        panel.setLayout(null);
        panel.setBackground(new Color(31,36,33));

        add(panel);
        addLabel(panel);
        addFieldLogin(panel);
        setVisible(true);
    }

    public void addLabel(JPanel panel){ 
        JLabel label = new JLabel("Bem-vindo!");
        label.setBounds(140, 50, 250, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public void addFieldLogin(JPanel panel){

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setBounds(40, 100, 100, 20);
        labelLogin.setForeground(Color.WHITE);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField login = new JTextField("");
        login.setBounds(100, 100, 200, 25);
        login.setBackground(new Color(240,240,240));
        login.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(40, 150, 100, 20);
        labelSenha.setForeground(Color.WHITE);
        labelSenha.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField senha = new JTextField("");
        senha.setBounds(100, 150, 200, 25);
        senha.setBackground(new Color(240,240,240));
        senha.setFont(new Font("Arial", Font.BOLD, 20));


        JButton submit = new JButton("Entrar");
        submit.setBounds(100, 200, 200, 30);
        submit.setBackground(new Color(0, 128, 0));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(labelLogin);
        panel.add(login);
        panel.add(labelSenha);
        panel.add(senha);
        panel.add(submit);

        submit.addActionListener(e -> {
            if(login.getText().equals("admin") && senha.getText().equals("admin")){
                submit.setEnabled(true);
                new TelaAdmin();
                dispose();
            } else if(login.getText().equals("gerente") && senha.getText().equals("gerente")){
                submit.setEnabled(true);
                new TelaGerente();
                dispose();
            } else if(login.getText().equals("atendente") && senha.getText().equals("atendente")){
                submit.setEnabled(true);
                new TelaAtendente();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
                login.setText(""); // Limpa o campo de login
                senha.setText(""); // Limpa o campo de senha
            }
        });
    }

     public static void main(String[] args) {
        new TelaLogin();
    }

}
