public abstract class ItemBiblioteca {

    private final String codigo;
    private final String titulo;
    private boolean disponivel = true;

    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Prazo e multa variam por tipo de item: cada subclasse define os seus.
    public abstract int getPrazoDias();

    public abstract double getMultaPorDia();

    public double calcularMulta(int diasAtraso) {
        return diasAtraso > 0 ? diasAtraso * getMultaPorDia() : 0.0;
    }

    // Sem setDisponivel() público: só a Biblioteca (mesmo pacote) altera o estado,
    // como consequência de emprestar() e devolver().
    void marcarEmprestado() {
        this.disponivel = false;
    }

    void marcarDevolvido() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-28s %-8s prazo: %2d dias | multa: R$ %.2f/dia | %s",
                codigo, titulo, getClass().getSimpleName(), getPrazoDias(), getMultaPorDia(),
                disponivel ? "disponível" : "emprestado");
    }
}
