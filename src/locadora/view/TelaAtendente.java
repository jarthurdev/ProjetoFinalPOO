package locadora.view;

import java.awt.*;
import javax.swing.*;

public final class TelaAtendente extends JFrame{
    
    public TelaAtendente() {

        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Tela do Atendente");
        setResizable(false);

        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31,36,33));
        add(panel);
        
        addLabel(panel);
        registrarLocacao(panel);
        registrarDevolucao(panel);
        setVisible(true);
    }

    public void addLabel(JPanel panel){ 
        JLabel label = new JLabel("Bem-vindo, atendente!");
        label.setBounds(90, 50, 300, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
    }

    public void registrarLocacao(JPanel panel){
        JButton button = new JButton("Registrar locação");
        button.setBounds(100, 100, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

        button.addActionListener(l -> new TelaCadastroLocacao(this));
    }

    public void registrarDevolucao(JPanel panel){
        JButton button = new JButton("Registrar devolução");
        button.setBounds(100, 150, 200, 30);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

        button.addActionListener(l -> new TelaDevolucao(this));

    }

}
