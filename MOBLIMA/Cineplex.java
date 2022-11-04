package MOBLIMA;

public enum Cineplex {
    LIDO("LIDO"),
    JEWEL("JEWEL"),
    PAYA_LEBAR_QUARTER("PAYA LEBAR QUARTER"),
    WATERWAY_POINT("WATERWAY POINT"),
    NEX("NEX"),
    SELETAR_MALL("SELETAR MALL"),
    JCUBE("JCUBE"),
    LOT_ONE("LOT ONE");

    private String name;

    Cineplex(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
