package locadora.view;

import java.awt.*;
import javax.swing.*;

public class TelaAluguel extends JDialog{

    private JTextField campoPlaca;
    private JTextField campoModelo;
    private JTextField campoAno;

    public TelaAluguel(JFrame parent) {
 
        super(parent, "Alugar Veículo", true);
        setSize(300, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);

        JPanel painelAluguel = new JPanel();
        painelAluguel.setBounds(0, 0, 300, 300);
        painelAluguel.setLayout(null);
        painelAluguel.setBackground(new Color(31,36,33));
        add(painelAluguel);

        JLabel labelPlaca = new JLabel("Placa:");
        campoPlaca = new JTextField();
        JLabel labelModelo = new JLabel("Modelo:");
        campoModelo = new JTextField();
        JLabel labelAno = new JLabel("Ano:");
        campoAno = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar");

        painelAluguel.add(labelPlaca);
        painelAluguel.add(campoPlaca);
        painelAluguel.add(labelModelo);
        painelAluguel.add(campoModelo);
        painelAluguel.add(labelAno);
        painelAluguel.add(campoAno);
        painelAluguel.add(botaoConfirmar);

        labelPlaca.setBounds(20, 20, 100, 20);
        labelPlaca.setForeground(Color.WHITE);
        labelPlaca.setFont(new Font("Arial", Font.BOLD, 20));
        campoPlaca.setBounds(120, 20, 150, 20);
        labelModelo.setBounds(20, 60, 100, 20);
        labelModelo.setForeground(Color.WHITE);
        labelModelo.setFont(new Font("Arial", Font.BOLD, 20));
        campoModelo.setBounds(120, 60, 150, 20);
        labelAno.setBounds(20, 100, 100, 20);
        labelAno.setForeground(Color.WHITE);
        labelAno.setFont(new Font("Arial", Font.BOLD, 20));
        campoAno.setBounds(120, 100, 150, 20);
        botaoConfirmar.setBounds(80, 150, 120, 30);

        botaoConfirmar.addActionListener(e -> {
            String placa = campoPlaca.getText();
            String modelo = campoModelo.getText();
            String ano = campoAno.getText();
    
            JOptionPane.showMessageDialog(this, "Veículo alugado:\nPlaca: " + placa + "\nModelo: " + modelo + "\nAno: " + ano);
            dispose();
            
            // Fecha a janela após confirmar
        });


        setVisible(true); // Torna a tela de aluguel visível
    
    }
}
