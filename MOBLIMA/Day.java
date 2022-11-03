package MOBLIMA;

public enum Day {
    WEEKDAY("WEEKDAY"),
    WEEKEND("WEEKEND"),
    PUBLIC_HOLIDAY("PUBLIC_HOLIDAY");

    private String day;

    Day(String day){
        this.day=day;
    }

    @Override
    public String toString() {
        return "MOBLIMA.Day{" +
                "day='" + day + '\'' +
                '}';
    }
}
