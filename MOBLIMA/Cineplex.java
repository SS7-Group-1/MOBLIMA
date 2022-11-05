package MOBLIMA;

public enum Cineplex {
    LIDO("Lido"),
    JEWEL("Jewel"),
    PAYA_LEBAR_QUARTER("Paya Lebar Quarter"),
    WATERWAY_POINT("Waterway Point"),
    NEX("nex"),
    SELETAR_MALL("Seletar Mall"),
    JCUBE("JCube"),
    LOT_ONE("Lot One");

    private final String name;

    Cineplex(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
