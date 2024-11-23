-- Criação do banco de dados
CREATE DATABASE LivrariaDB;

-- Usando o banco de dados criado
USE LivrariaDB;

-- Tabela para armazenar informações dos livros
CREATE TABLE Livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL
);

-- Tabela para armazenar informações dos clientes
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabela para registrar vendas
CREATE TABLE Venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT,
    id_cliente INT,
    quantidade INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_livro) REFERENCES Livro(id),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id)
);

use LivrariaDBcliente

SELECT * FROM Cliente;
