package MOBLIMA;

public enum MovieType {
    threeD("3D"),
    twoD("2D");

    private final String type;

    MovieType(String type){
        this.type = type;
    }

@Override
    public String toString(){
        return type;
    }
}
