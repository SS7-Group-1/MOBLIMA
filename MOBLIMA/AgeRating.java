package MOBLIMA;

public enum AgeRating {
    G("G"),PG("PG"),PG13("PG13"),NC16("NC16"),M18("M18"),R21("R21");

    private String ageRating;

    AgeRating(String age){
        this.ageRating=age;

    }

    @Override
    public String toString() {
        return ageRating;
    }
}
