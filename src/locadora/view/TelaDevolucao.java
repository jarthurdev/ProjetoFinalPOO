package locadora.view;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import locadora.controller.ClienteController;
import locadora.controller.LocacaoController;
import locadora.controller.PagamentoController;
import locadora.controller.VeiculoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.model.Carro;
import locadora.model.Locacao;
import locadora.model.Moto;
import locadora.model.Pagamento;

public class TelaDevolucao extends JDialog {

    private JTextField campoId;
    private JTextField campoDatadeDevolucao;
    private JTextArea areaLocacao;
    private VeiculoController veiculoController;
    private VeiculoDAO veiculodao;
    private ClienteController clienteController;
    private LocacaoDAO locacaodao;
    private LocacaoController locacaoController;
    private PagamentoController pagamentoController;
    private Pagamento pagamento;
    private PagamentoDAO pagamentodao;

    public void carregarDados() {
        veiculoController = new VeiculoController();
        veiculodao = new VeiculoDAO();
        clienteController = new ClienteController();
        locacaoController = new LocacaoController();
        locacaodao = new LocacaoDAO();
        locacaoController.setListaLocacoes(locacaodao.carregarLista());
        pagamentoController = new PagamentoController();
        pagamentodao = new PagamentoDAO();
        pagamentoController.setListaLocacoes(pagamentodao.carregarLista());
        
    }

    public TelaDevolucao(JFrame parent) {
        super(parent, "Locação", true);
        carregarDados();
        setSize(400, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 600);
        panel.setLayout(null);
        panel.setBackground(new Color(31, 36, 33));
        add(panel);

        JLabel labelId = new JLabel("ID da locação:");
        campoId = new JTextField();
        JLabel labelDatadeDevolucao = new JLabel("Data de devolução (YYYY-MM-DD): ");
        campoDatadeDevolucao = new JTextField();
        JButton botaoRegistrar = new JButton("Registrar");

        areaLocacao = new JTextArea();
        areaLocacao.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaLocacao);

        panel.add(labelId);
        panel.add(campoId);
        panel.add(labelDatadeDevolucao);
        panel.add(campoDatadeDevolucao);
        panel.add(botaoRegistrar);
        panel.add(scrollPane);

        labelId.setBounds(20, 20, 100, 20);
        labelId.setForeground(Color.WHITE);
        campoId.setBounds(120, 20, 250, 20);
        labelDatadeDevolucao.setBounds(20, 60, 200, 20);
        labelDatadeDevolucao.setForeground(Color.WHITE);
        campoDatadeDevolucao.setBounds(220, 60, 150, 20);
        scrollPane.setBounds(20, 100, 350, 400);
        botaoRegistrar.setBounds(20, 520, 350, 30);

        botaoRegistrar.addActionListener(e -> registrarDevolucao());

        listarTodasLocacoes();
        setVisible(true);
    }

    private void listarTodasLocacoes() {
        areaLocacao.setText("");
        for (Locacao locacao : locacaoController.getListaLocacoes()) {
            areaLocacao.append(locacao.toString() + "\n");
        }
    }

    private Locacao buscarLocacaoPorId(int id) {
        for (Locacao locacao : locacaoController.getListaLocacoes()) {
            if (locacao.getId() == id) {
                return locacao;
            }
        }
        return null;
    }

    private void registrarDevolucao() {
        String idTexto = campoId.getText().trim();
        String dataDevolucaoTexto = campoDatadeDevolucao.getText().trim();

        if (idTexto.isEmpty() || dataDevolucaoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idLocacao = Integer.parseInt(idTexto);
            Locacao locacao = buscarLocacaoPorId(idLocacao);

            if (locacao != null) {
                LocalDate dataPrevista = locacao.getDataDevolucao();
                LocalDate dataRealDevolucao = LocalDate.parse(dataDevolucaoTexto, DateTimeFormatter.ISO_DATE);
                Pagamento pagamento = new Pagamento(locacao.getValorLocacao(), "Dinheiro", dataRealDevolucao ,locacao);
                pagamentoController.addPagamento(pagamento);

                double valorFinal = pagamento.calcularPagamento();
                long diferencaDias = ChronoUnit.DAYS.between(dataPrevista, dataRealDevolucao);

                if (diferencaDias > 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Devolução atrasada em " + diferencaDias + " dias!\n" +
                        "Valor atualizado com multa: R$ " + String.format("%.2f", valorFinal),
                        "Atenção", JOptionPane.WARNING_MESSAGE);
                } else if (diferencaDias < 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Devolução antecipada em " + Math.abs(diferencaDias) + " dias.\n" +
                        "Valor original: R$ " + String.format("%.2f", valorFinal),
                        "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Devolução realizada no prazo!\n" +
                        "Valor final: R$ " + String.format("%.2f", valorFinal),
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma locação encontrada com esse ID.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            pagamentodao.salvarLista(pagamentoController.getListaLocacoes());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido! Insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use YYYY-MM-DD.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
