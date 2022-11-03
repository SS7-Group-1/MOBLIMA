package MOBLIMA;

public enum MovieType {
    threeD("3D"),
    twoD("2D");

    private String type;

    MovieType(String typ){
        this.type = typ;
    }

@Override
    public String toString(){
        return type;
    }
}
