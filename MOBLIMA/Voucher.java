package MOBLIMA;

import java.io.Serializable;

public class Voucher implements Serializable {
    private String voucherCode;
    private float amount;
    private int maxUses;
    private boolean valid;

    public Voucher(){
        this.valid = true;
    }

    public Voucher(String voucherID, float amount, int maxUses){
        this.voucherCode = voucherID;
        this.amount = amount;
        this.valid = false;
        this.maxUses = maxUses;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    public void useVoucher(){
        if(this.maxUses > 0){
            this.maxUses--;
            if(maxUses == 0){
                this.valid = false;
            }
        }
    }

    public Boolean isValid() {
        if(this.maxUses > 0){
            return true;
        }
        else return this.maxUses != 0;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public int getMaxUses(){
        return this.maxUses;
    }

    public void setValidity(Boolean valid) {
        this.valid = valid;
    }

    public Boolean unlimitedUses(){
        return this.maxUses == -1;
    }

    @Override
    public String toString() {
        return "GiftVoucher{" +
                "voucherID='" + voucherCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}
