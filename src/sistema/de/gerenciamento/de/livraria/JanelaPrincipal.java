// Crie um novo arquivo chamado JanelaPrincipal.java
package sistema.de.gerenciamento.de.livraria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame {
    private Estoque estoque;
    
    // Componentes da GUI
    private JTextField tituloField, autorField, isbnField, precoField;
    private JButton cadastrarLivroButton, verEstoqueButton, comprarLivroButton;

    public JanelaPrincipal() {
        estoque = new Estoque();
        
        // Configurações da janela principal
        setTitle("Sistema de Gerenciamento de Livraria");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        
        // Criação dos componentes
        tituloField = new JTextField();
        autorField = new JTextField();
        isbnField = new JTextField();
        precoField = new JTextField();
        cadastrarLivroButton = new JButton("Cadastrar Livro");
        verEstoqueButton = new JButton("Ver Estoque");
        comprarLivroButton = new JButton("Comprar Livro");
        
        // Adiciona os componentes à janela
        add(new JLabel("Título:"));
        add(tituloField);
        add(new JLabel("Autor:"));
        add(autorField);
        add(new JLabel("ISBN:"));
        add(isbnField);
        add(new JLabel("Preço:"));
        add(precoField);
        add(cadastrarLivroButton);
        add(verEstoqueButton);
        add(comprarLivroButton);
        
        // Adiciona ação aos botões
        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
        
        verEstoqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verEstoque();
            }
        });

        comprarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarLivro();
            }
        });
    }
    
    // Método para cadastrar um livro
    private void cadastrarLivro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        double preco = Double.parseDouble(precoField.getText());
        
        Livro livro = new Livro(titulo, autor, isbn, preco, 10);
        estoque.adicionarLivro(livro);
        
        JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
    }
    
    // Método para visualizar o estoque de um livro
    private void verEstoque() {
        String titulo = tituloField.getText();
        
        for (Livro livro : estoque.livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                int quantidade = estoque.getQuantidade(livro);
                JOptionPane.showMessageDialog(this, "Estoque do livro '" + titulo + "': " + quantidade);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não encontrado.");
    }
    
    // Método para comprar um livro
    private void comprarLivro() {
        String titulo = tituloField.getText();
        
        for (Livro livro : estoque.livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                Cliente cliente = new Cliente("Cliente Padrão", "cliente@email.com");
                Pagamento pagamento = new PagamentoCartao(livro.getPreco());
                Notificacao notificacao = new NotificacaoEmail();
                
                cliente.comprarLivro(livro, 1, estoque, pagamento, notificacao);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não encontrado ou estoque insuficiente.");
    }

    public static void main(String[] args) {
        // Inicia a aplicação
        JanelaPrincipal janela = new JanelaPrincipal();
        janela.setVisible(true);
    }
}
