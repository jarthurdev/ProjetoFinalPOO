package locadora.view;

import java.awt.*;
import javax.swing.*;

public final class TelaGerente extends JFrame{
    
    public TelaGerente() {

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Tela do Gerente");
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
        JLabel label = new JLabel("Locadora de Veículos");
        label.setBounds(300, 50, 250, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public void cadastrarVeiculo(JPanel panel){
        JButton button = new JButton("Cadastrar veículo");
        button.setBounds(300, 100, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);
    }

    public void cadastrarCliente(JPanel panel){
        JButton button = new JButton("Cadastrar cliente");
        button.setBounds(300, 150, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

    }

    public void visualizarRelatorio(JPanel panel){
        JButton button = new JButton("Visualizar relatório");
        button.setBounds(300, 200, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

    }

     public static void main(String[] args) {
        new TelaGerente();
    }

}
