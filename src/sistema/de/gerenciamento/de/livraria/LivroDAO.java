/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package sistema.de.gerenciamento.de.livraria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO Livro (titulo, autor, isbn, preco, estoque) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setDouble(4, livro.getPreco());
            stmt.setInt(5, livro.getEstoque());
            stmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("isbn"),
                    rs.getDouble("preco"),
                    rs.getInt("estoque")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public void atualizarEstoque(int livroId, int novaQuantidade) {
        String sql = "UPDATE Livro SET estoque = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, livroId);
            stmt.executeUpdate();
            System.out.println("Estoque atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerLivro(int livroId) {
        String sql = "DELETE FROM Livro WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livroId);
            stmt.executeUpdate();
            System.out.println("Livro removido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
