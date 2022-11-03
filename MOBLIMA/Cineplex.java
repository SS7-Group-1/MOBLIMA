package MOBLIMA;

public enum Cineplex {
    GOLDEN_VILLAGE("GOLDEN_VILLAGE"),
    CATHAY("CATHAY"),
    SHAW("SHAW");

    private String name;
    Cineplex(String name){
        this.name = name;

    }

    @Override
    public String toString() {
        return this.name;
    }
}
