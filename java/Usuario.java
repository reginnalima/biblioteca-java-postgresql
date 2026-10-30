public abstract class Usuario {

    private final String nome;
    private int quantidadeEmprestada = 0;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    // Limite de itens simultâneos varia por tipo de usuário.
    public abstract int getLimiteItens();

    public boolean podeEmprestar() {
        return quantidadeEmprestada < getLimiteItens();
    }

    void registrarEmprestimo() {
        quantidadeEmprestada++;
    }

    void registrarDevolucao() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d/%d itens", nome, getClass().getSimpleName(),
                quantidadeEmprestada, getLimiteItens());
    }
}
