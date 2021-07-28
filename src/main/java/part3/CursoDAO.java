package part3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    //Consulta
    public List<Curso> list() {
        //Preparar uma lista que irá retornar cursos após consultar o database
        List<Curso> cursos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            //Preparar consulta SQL
            String sql = "SELECT * FROM curso";

            //Preparar o statment com os parametros
            PreparedStatement statement = connection.prepareStatement(sql);

            //Executar a consulta e armazenar o retorno da consulta no objeto "resultSet"
            ResultSet resultSet = statement.executeQuery();

            //Cria um objeto aluno e guarda na lista de alunos
            while (resultSet.next()) {
                Curso curso = new Curso(resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getInt("duracao_horas")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Listagem de cursos FALHOU!");
            e.printStackTrace();
        }
        return cursos;
    }

    // Consulta por id

    public Curso getById(int id) {
        Curso curso = new Curso();

        try (Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM curso WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                curso.setId(resultSet.getInt("id"));
                curso.setNome(resultSet.getString("nome"));
                curso.setDuracaoHoras(resultSet.getInt("duracao_horas"));
            }
        }catch (SQLException e){
            System.out.println("Falha na consulta");
            e.printStackTrace();
        }
        return curso;
    }

    //Inserção
    public void create(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO curso (nome, duracao_horas) VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, curso.getNome());
            statement.setInt(2, curso.getDuracaoHoras());

            int rowsAffecteds = statement.executeUpdate();
            System.out.println("Inserção BEM SUCEDIDA! Foi adicionada " + rowsAffecteds + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção FALHOU");
            e.printStackTrace();
        }

    }

    // DELETE
    public void delte(int id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM curso WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha(s)");

        }catch (SQLException e) {
            System.out.println("ERRO Na deletação");
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(Curso curso) {
        try (Connection connection = ConnectionFactory.getConnection()){
            String sql = "UPDATE curso SET nome = ?, duracao_horas = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, curso.getNome());
            statement.setInt(2, curso.getDuracaoHoras());
            statement.setInt(3, curso.getId());

            int rowsAffected = statement.executeUpdate();

            System.out.println("Atualizaçao BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha(s)");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU");
            e.printStackTrace();
        }
    }
}
