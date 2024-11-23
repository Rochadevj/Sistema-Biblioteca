/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.de.gerenciamento.de.livraria;

/**
 *
 * @author user
 */




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaDetalhesLivro extends JFrame {
    // Componentes da GUI para os detalhes do livro
    private JTextField nomeLivroField, categoriaField, autorField;
    private JTextArea descricaoArea;
    private JComboBox<String> statusComboBox;
    private JButton salvarButton, cancelarButton;
    
    public JanelaDetalhesLivro() {
        // Configurações da janela
        setTitle("Informações do Livro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel principal com GridLayout para os campos
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        // Campos de texto
        nomeLivroField = new JTextField();
        categoriaField = new JTextField();
        autorField = new JTextField();
        descricaoArea = new JTextArea(5, 20);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        
        // ComboBox para o status
        statusComboBox = new JComboBox<>(new String[] {"Ativo", "Inativo"});
        
        // Botões de ação
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");
        
        // Adicionando os componentes ao painel principal
        mainPanel.add(new JLabel("Nome do Livro:"));
        mainPanel.add(nomeLivroField);
        
        mainPanel.add(new JLabel("Categoria:"));
        mainPanel.add(categoriaField);
        
        mainPanel.add(new JLabel("Autor:"));
        mainPanel.add(autorField);
        
        mainPanel.add(new JLabel("Descrição:"));
        mainPanel.add(new JScrollPane(descricaoArea));
        
        mainPanel.add(new JLabel("Status do Produto:"));
        mainPanel.add(statusComboBox);
        
        // Painel inferior com botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelarButton);
        buttonPanel.add(salvarButton);
        
        // Adiciona o painel principal e o painel de botões à janela
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Ações dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarLivro();
            }
        });
        
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    // Método para salvar o livro
    private void salvarLivro() {
        String nome = nomeLivroField.getText();
        String categoria = categoriaField.getText();
        String autor = autorField.getText();
        String descricao = descricaoArea.getText();
        String status = (String) statusComboBox.getSelectedItem();
        
        // Exibir confirmação de salvamento
        JOptionPane.showMessageDialog(this, "Livro salvo com sucesso:\n" +
                "Nome: " + nome + "\n" +
                "Categoria: " + categoria + "\n" +
                "Autor: " + autor + "\n" +
                "Descrição: " + descricao + "\n" +
                "Status: " + status);
    }

    public static void main(String[] args) {
        JanelaDetalhesLivro detalhesLivro = new JanelaDetalhesLivro();
        detalhesLivro.setVisible(true);
    }
}
