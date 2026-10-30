public class Revista extends ItemBiblioteca {

    public Revista(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoDias() {
        return 7;
    }

    @Override
    public double getMultaPorDia() {
        return 1.00;
    }
}
