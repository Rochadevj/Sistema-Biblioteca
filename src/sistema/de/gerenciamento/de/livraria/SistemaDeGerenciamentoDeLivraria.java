package sistema.de.gerenciamento.de.livraria;

import java.util.ArrayList;
import java.util.List;

// Classe Livro
class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private double preco;

    public Livro(String titulo, String autor, String isbn, double preco, int par1) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.preco = preco;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public double getPreco() { return preco; }

    int getEstoque() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getEstoque() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

// Classe Estoque
class Estoque {
    List<Livro> livros = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public int getQuantidade(Livro livro) {
        int quantidade = 0;
        for (Livro l : livros) {
            if (l.equals(livro)) quantidade++;
        }
        return quantidade;
    }

    public void atualizarEstoque(Livro livro, int quantidade) {
        // Lógica para atualizar o estoque
    }
}

// Interface de Pagamento
interface Pagamento {
    boolean processarPagamento(double valor);
}

// Implementação de Pagamento em Cartão
class PagamentoCartao implements Pagamento {
    private double valorPago;

    public PagamentoCartao(double valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public boolean processarPagamento(double valor) {
        // Implementação de pagamento com cartão
        return valorPago >= valor;
    }
}

// Padrão Strategy para calcular o valor da venda
interface CalculadoraDeVenda {
    double calcularTotal(double preco, int quantidade);
}

// Implementação padrão de calculadora de vendas
class CalculadoraDeVendaPadrao implements CalculadoraDeVenda {
    @Override
    public double calcularTotal(double preco, int quantidade) {
        return preco * quantidade;
    }
}

// Factory para criação de vendas
class VendaFactory {
    public static Venda criarVenda(Livro livro, int quantidade, CalculadoraDeVenda calculadora) {
        return new Venda(livro, quantidade, calculadora);
    }
}

// Classe Venda
class Venda {
    private Livro livro;
    private int quantidade;
    private double valorTotal;

    public Venda(Livro livro, int quantidade, CalculadoraDeVenda calculadora) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.valorTotal = calculadora.calcularTotal(livro.getPreco(), quantidade);
    }

    public double getValorTotal() { return valorTotal; }
}

// Interface Notificacao
interface Notificacao {
    void enviarNotificacao(String mensagem);
}

// Implementação de Notificação por Email
class NotificacaoEmail implements Notificacao {
    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Enviando notificação por Email: " + mensagem);
    }
}

// Implementação de Notificação por SMS
class NotificacaoSMS implements Notificacao {
    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Enviando notificação por SMS: " + mensagem);
    }
}

// Classe Usuario (base abstrata para SRP)
abstract class Usuario {
    protected String nome;
    protected String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}

// Classe Colaborador
class Colaborador extends Usuario {
    private String cargo;

    public Colaborador(String nome, String email, String cargo) {
        super(nome, email);
        this.cargo = cargo;
    }

    public void cadastrarLivro(Estoque estoque, Livro livro) {
        estoque.adicionarLivro(livro);
    }
}

// Classe Cliente
class Cliente extends Usuario {
    public Cliente(String nome, String email) {
        super(nome, email);
    }

    public void comprarLivro(Livro livro, int quantidade, Estoque estoque, Pagamento pagamento, Notificacao notificacao) {
        if (estoque.getQuantidade(livro) >= quantidade) {
            CalculadoraDeVenda calculadora = new CalculadoraDeVendaPadrao();
            Venda venda = VendaFactory.criarVenda(livro, quantidade, calculadora);
            if (pagamento.processarPagamento(venda.getValorTotal())) {
                estoque.atualizarEstoque(livro, -quantidade);
                notificacao.enviarNotificacao("Compra realizada com sucesso para " + livro.getTitulo());
            } else {
                notificacao.enviarNotificacao("Falha no pagamento.");
            }
        } else {
            notificacao.enviarNotificacao("Estoque insuficiente para " + livro.getTitulo());
        }
    }
}

public class SistemaDeGerenciamentoDeLivraria {
    public static void main(String[] args) {
        LivroDAO livroDAO = new LivroDAO();

        // Adiciona um novo livro
        Livro novoLivro = new Livro("Java: Como Programar", "Deitel & Deitel", "123456789", 150.00, 10);
        livroDAO.adicionarLivro(novoLivro);

        // Lista todos os livros
        List<Livro> livros = livroDAO.listarLivros();
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo() + ", Estoque: " + livro.getEstoque());
        }

        // Atualiza o estoque de um livro
        livroDAO.atualizarEstoque(1, 5);

        // Remove um livro
        livroDAO.removerLivro(1);
    }
}


