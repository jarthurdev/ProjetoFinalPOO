package locadora.view;

import java.awt.*;
import javax.swing.*;

public final class TelaAdmin extends JFrame{
    
    public TelaAdmin() {

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Tela do administrador do sistema"); 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31,36,33));
        add(panel);
        
        addLabel(panel);
        cadastrarAdmin(panel);
        cadastrarGerente(panel);
        cadastrarAtendente(panel);
        setVisible(true);
    }

    public void addLabel(JPanel panel){ 
        JLabel label = new JLabel("Locadora de Veículos");
        label.setBounds(300, 50, 250, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public void cadastrarAdmin(JPanel panel){
        JButton button = new JButton("Cadastrar administrador");
        button.setBounds(300, 100, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);
    }

    public void cadastrarGerente(JPanel panel){
        JButton button = new JButton("Cadastrar gerente");
        button.setBounds(300, 150, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

    }

    public void cadastrarAtendente(JPanel panel){
        JButton button = new JButton("Cadastrar atendente");
        button.setBounds(300, 200, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);
    }

     public static void main(String[] args) {
        new TelaAdmin();
    }

}
