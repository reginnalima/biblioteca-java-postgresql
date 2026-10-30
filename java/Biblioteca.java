public class Biblioteca {

    private final ItemBiblioteca[] acervo;
    private int totalItens = 0;

    public Biblioteca(int capacidade) {
        this.acervo = new ItemBiblioteca[capacidade];
    }

    public boolean adicionarItem(ItemBiblioteca item) {
        if (totalItens >= acervo.length) {
            System.out.println("Acervo cheio: não foi possível cadastrar " + item.getTitulo());
            return false;
        }
        acervo[totalItens++] = item;
        return true;
    }

    private ItemBiblioteca buscarPorCodigo(String codigo) {
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i].getCodigo().equals(codigo)) {
                return acervo[i];
            }
        }
        return null;
    }

    public boolean emprestar(Usuario usuario, String codigoItem) {
        ItemBiblioteca item = buscarPorCodigo(codigoItem);

        if (item == null) {
            System.out.println("RECUSADO: item " + codigoItem + " não existe.");
            return false;
        }
        if (!item.isDisponivel()) {
            System.out.println("RECUSADO: \"" + item.getTitulo() + "\" já está emprestado.");
            return false;
        }
        if (!usuario.podeEmprestar()) {
            System.out.println("RECUSADO: " + usuario.getNome() + " atingiu o limite de "
                    + usuario.getLimiteItens() + " itens.");
            return false;
        }

        item.marcarEmprestado();
        usuario.registrarEmprestimo();
        System.out.println("OK: " + usuario.getNome() + " pegou \"" + item.getTitulo()
                + "\" (prazo de " + item.getPrazoDias() + " dias).");
        return true;
    }

    // Retorna a multa a pagar (0.0 se devolvido no prazo ou se a devolução for inválida).
    public double devolver(Usuario usuario, String codigoItem, int diasAtraso) {
        ItemBiblioteca item = buscarPorCodigo(codigoItem);

        if (item == null || item.isDisponivel()) {
            System.out.println("RECUSADO: devolução inválida para o item " + codigoItem + ".");
            return 0.0;
        }

        item.marcarDevolvido();
        usuario.registrarDevolucao();
        double multa = item.calcularMulta(diasAtraso);
        System.out.printf("OK: %s devolveu \"%s\". Multa: R$ %.2f%n",
                usuario.getNome(), item.getTitulo(), multa);
        return multa;
    }

    // Um único laço, sem nenhum if verificando o tipo: o polimorfismo decide.
    public void listarAcervo() {
        System.out.println("=== ACERVO ===");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(acervo[i]);
        }
    }
}
