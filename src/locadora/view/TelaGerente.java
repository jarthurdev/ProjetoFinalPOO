package locadora.view;

import java.awt.*;
import javax.swing.*;

public final class TelaGerente extends JFrame{
    
    public TelaGerente() {
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Tela do Gerente");
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31,36,33));

        add(panel);
        
        addLabel(panel);
        cadastrarVeiculo(panel);
        cadastrarCliente(panel);
        visualizarRelatorio(panel);
        setVisible(true);
    }

    public void addLabel(JPanel panel){ 
        JLabel label = new JLabel("Bem-vindo, gerente!");
        label.setBounds(100, 50, 250, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public void cadastrarVeiculo(JPanel panel){
        JButton button = new JButton("Cadastrar veículo");
        button.setBounds(100, 100, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);
        
        button.addActionListener(l -> new TelaCadastroVeiculo(this));
    }

    public void cadastrarCliente(JPanel panel){
        JButton button = new JButton("Cadastrar cliente");
        button.setBounds(100, 150, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

        button.addActionListener(l -> new TelaCadastroCliente(this));
    }

    public void visualizarRelatorio(JPanel panel){
        JButton button = new JButton("Visualizar relatório");
        button.setBounds(100, 200, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

        button.addActionListener(l -> new TelaRelatorios());
    }


    
    public static void main(String[] args) {
        new TelaGerente();
    }
}
