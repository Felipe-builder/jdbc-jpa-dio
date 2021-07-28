package part3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // 1 - Consulta
    public List<Aluno> list() {
        //Preparar lista que irá retornar alunos após consultar o banco de dados
        List<Aluno> alunos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            // Preparar consulta SQL
            String sql = "SELECT * FROM aluno";

            //Preparar statement com os parâmetros (nesta função não tem parâmetros,pois irá retornar todos os valores da tabela aluno)
            PreparedStatement statement = connection.prepareStatement(sql);

            //Executar consulta e armazenar o retorno de consulta no objeto "rs".
            ResultSet rs = statement.executeQuery();

            // Criar um objeto aluno e guardar na lista de alunos
            while (rs.next()){

                Aluno aluno = new Aluno(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("estado")
                );

                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        //Retornar todos os alunos encontrados no banco de dados
        return alunos;
    }

    // 1.1 - Consulta com filtro
    public Aluno getById(int id) {
        //Preparar objeto para receber os valores do banco de dados.
        Aluno aluno = new Aluno();

        try (Connection connection = ConnectionFactory.getConnection()){
            //Preparar consulta SQL
            String sql = "SELECT * FROM aluno WHERE id = ?";

            //Preparar statment com os parametros recebidos
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executa consulta e armazena o retorno da consulta no objeto "rs".
            ResultSet rs = stmt.executeQuery();

            // Guardar valores retornados da tabla com aluno no objeto aluno
            if (rs.next()) {
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }
        return aluno;
    }

    // 2 - Inserção
    public void create(Aluno aluno) {
        try (Connection connection = ConnectionFactory.getConnection()){
            //Preparar SQL para inserção de dados do aluno
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)";

            //Preparar Statement com os parâmetros recebidos
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());

            //Executa inserção e armazena o número de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA! Foi adicionada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção FALHOU");
            e.printStackTrace();
        }
    }

    // 3 - Delete
    public void delete(int id) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            //Preparar SQL para deletar uma linha.
            String sql = "DELETE FROM aluno WHERE id = ?";

            //Preparar statment com os parâmetros recebidos
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            // Executa delete e armazena o numero de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha(s)");
        } catch (SQLException e){
            System.out.println("Delete FALHOU");
            e.printStackTrace();
        }
    }

    // 4 - Atualizar
    public void update(Aluno aluno) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            //Preparar SQL para atualizar linha
            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());
            stmt.setInt(4, aluno.getId());

            //Executa atualização e armazena o número de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualizaçao BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha(s)");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU");
            e.printStackTrace();
        }
    }

}
