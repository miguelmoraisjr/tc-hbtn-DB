package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO{

    private Connection connection;

    public ClienteDAOImpl(){}


    @Override
    public Connection connect(String urlConexao) {
        connection = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:sqlite_database_marco_2022.db");
        } catch (ClassNotFoundException e) {
            System.err.println("Ocorreu uma falha ao utilizar o driver");
        } catch (SQLException e) {
            System.err.println("Não foi possível abrir conexão com o banco!");
        }
        return connection;
    }

    @Override
    public void createTable(String urlConexao) {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE IF NOT EXISTS cliente (");
        sql.append("id integer PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome text NOT NULL, ");
        sql.append("idade integer, ");
        sql.append("cpf text NOT NULL, ");
        sql.append("rg text ");
        sql.append(")");
        try{
            connection = connect(urlConexao);
            Statement stm = connection.createStatement();
            stm.execute(sql.toString());
        } catch (SQLException e) {
            System.err.println("Não foi possível criar a tabela");
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, idade, cpf, rg) VALUES(?,?,?,?)";
        try{
            connection = connect(url_conexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("Não foi possível inserir na tabela");
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clienteList = new ArrayList<>();
        try{
            connection = connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Cliente cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("nome")
                        , resultSet.getInt("idade"), resultSet.getString("cpf"), resultSet.getString("rg"));
                clienteList.add(cliente);
            }
            for (Cliente client : clienteList){
                System.out.println(client);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possível trazer os clientes da tabela");
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        String sql = "UPDATE cliente SET nome=?, idade=? WHERE id=?";
        try {
            connection = connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, idade);
            stmt.setInt(3, id);
            stmt.execute();

        } catch (SQLException e) {
            System.err.println("Não foi possível alterar cliente na tabela");
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            connection = connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {
            System.err.println("Não foi possível excluir cliente na tabela");
        }
    }
}
