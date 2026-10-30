public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        // Cadastro do acervo
        biblioteca.adicionarItem(new Livro("L001", "Java: Como Programar"));
        biblioteca.adicionarItem(new Livro("L002", "Banco de Dados Relacional"));
        biblioteca.adicionarItem(new Livro("L003", "Estruturas de Dados"));
        biblioteca.adicionarItem(new Revista("R001", "Superinteressante"));
        biblioteca.adicionarItem(new Revista("R002", "Galileu"));

        // Cadastro de usuários
        Aluno aluno = new Aluno("Maria");
        Professor professor = new Professor("Carlos");

        biblioteca.listarAcervo();

        System.out.println("\n=== EMPRÉSTIMOS ===");
        // Aluno (limite 3): três empréstimos bem-sucedidos...
        biblioteca.emprestar(aluno, "L001");
        biblioteca.emprestar(aluno, "L002");
        biblioteca.emprestar(aluno, "R001");
        // ...e o quarto é recusado por limite atingido.
        biblioteca.emprestar(aluno, "L003");

        // Professor (limite 5) consegue pegar o mesmo item que o aluno não conseguiu.
        biblioteca.emprestar(professor, "L003");

        System.out.println("\n=== DEVOLUÇÃO ===");
        // Revista devolvida com 3 dias de atraso: 3 x R$ 1,00
        biblioteca.devolver(aluno, "R001", 3);

        System.out.println();
        biblioteca.listarAcervo();
        System.out.println("\n" + aluno);
        System.out.println(professor);
    }
}
