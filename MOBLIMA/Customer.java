package MOBLIMA;

public class Customer {
    private String name;
    private String phone;
    private String email;
    private boolean isSenior;
    private boolean isChild;

    public Customer(String name,String phone, String email, boolean isSenior,boolean isChild){
        this.name = name;
        this.phone=phone;
        this.email=email;
        this.isSenior=isSenior;
        this.isChild=isChild;

    }

    public boolean isChild() {
        return isChild;
    }

    public boolean isSenior() {
        return isSenior;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", isSenior=" + isSenior +
                ", isChild=" + isChild +
                '}';
    }
}
