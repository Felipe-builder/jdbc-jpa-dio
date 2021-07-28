package part3;

import java.util.List;

public class CursoTest {
    public static void main(String[] args) {
        CursoDAO cursoDAO = new CursoDAO();

        // CONSULTA
        List<Curso> cursos = cursoDAO.list();

//        cursos.stream().forEach(System.out::println);

        // CONSULTA COM FILTRO
        Curso cursoParaConsulta = cursoDAO.getById(3);

//        System.out.println(cursoParaConsulta);

        // INSERÇÃO
        Curso cursoParaInsercao = new Curso(
                "Arquitetura de Software",
                40
        );

//        cursoDAO.create(cursoParaInsercao);

        //DELETE
//        cursoDAO.list().stream().forEach(System.out::println);
//        cursoDAO.delte(9);
//        cursoDAO.list().forEach(System.out::println);

        // UPDATE
        cursoDAO.list().stream().forEach(System.out::println);
        Curso cursoParaAtualizar = cursoDAO.getById(7);
        cursoParaAtualizar.setNome("Django");
        cursoParaAtualizar.setDuracaoHoras(5);

        cursoDAO.update(cursoParaAtualizar);

        cursoDAO.list().stream().forEach(System.out::println);

    }

}
