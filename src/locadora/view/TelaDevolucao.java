package locadora.view;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import locadora.controller.LocacaoController;
import locadora.controller.PagamentoController;
import locadora.controller.VeiculoController;
import locadora.dao.LocacaoDAO;
import locadora.dao.PagamentoDAO;
import locadora.dao.VeiculoDAO;
import locadora.exceptions.LocacaoIdInexistenteException;
import locadora.model.Locacao;
import locadora.model.Pagamento;

public class TelaDevolucao extends JDialog {

    private JTextField campoId;
    private JTextField campoDatadeDevolucao;
    private JTextArea areaLocacao;
    private LocacaoDAO locacaodao;
    private LocacaoController locacaoController;
    private PagamentoController pagamentoController;
    private PagamentoDAO pagamentodao;
    private VeiculoController veiculoController;
    private VeiculoDAO veiculoDAO;

    public void carregarDados() {
        locacaoController = new LocacaoController();
        locacaodao = new LocacaoDAO();
        locacaoController.setListaLocacoes(locacaodao.carregarLista());
        pagamentoController = new PagamentoController();
        pagamentodao = new PagamentoDAO();
        pagamentoController.setListaLocacoes(pagamentodao.carregarLista());
        veiculoController = new VeiculoController();
        veiculoDAO = new VeiculoDAO();
        veiculoController.setListaVeiculos(veiculoDAO.carregarLista());
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

    private String selecionarTipoPagamento() {
        String[] opcoes = {"Pix", "Débito", "Crédito", "Dinheiro"};
        int escolha = JOptionPane.showOptionDialog(
            this,
            "Escolha o tipo de pagamento",
            "Selecionar Tipo de Pagamento",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        if (escolha >= 0) {
            return opcoes[escolha];
        } else {
            return null;
        }
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
            Locacao locacao = locacaoController.buscarLocacaoPorId(idLocacao);

            if (locacao != null) {
                LocalDate dataPrevista = locacao.getDataDevolucao();
                LocalDate dataRealDevolucao = LocalDate.parse(dataDevolucaoTexto, DateTimeFormatter.ISO_DATE);

                String tipoPagamento = selecionarTipoPagamento();

                if (tipoPagamento != null) {
                    Pagamento pagamento = new Pagamento(locacao.getValorLocacao(), tipoPagamento, dataRealDevolucao, locacao);
                    pagamento.setId(pagamentoController.getId());
                    
                    double valorFinal = pagamento.calcularPagamento();
                    long diferencaDias = ChronoUnit.DAYS.between(dataPrevista, dataRealDevolucao);

                    pagamentoController.addPagamento(pagamento);
                    pagamentodao.salvarLista(pagamentoController.getListaLocacoes());
                    veiculoController.buscarVeiculoPorPlaca((locacao.getVeiculo().getPlaca())).setStatus(true);;
                    veiculoDAO.salvarLista(veiculoController.getListaVeiculos());
                    locacaoController.removerLocacao(locacao);
                    locacaodao.salvarLista(locacaoController.getListaLocacoes());
                    listarTodasLocacoes();

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
                    JOptionPane.showMessageDialog(this, "Nenhum tipo de pagamento selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma locação encontrada com esse ID.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (LocacaoIdInexistenteException a) {
            JOptionPane.showMessageDialog(this, a.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido! Insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
        }catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use YYYY-MM-DD.", "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }

}
