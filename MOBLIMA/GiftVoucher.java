package MOBLIMA;

public class GiftVoucher {
    private String voucherID;
    private float amount;
    private boolean redeemed;

    public GiftVoucher(String voucherID, float amount){
        this.voucherID = voucherID;
        this.amount = amount;
        this.redeemed = false;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(Boolean redeemed) {
        this.redeemed = redeemed;
    }

    @Override
    public String toString() {
        return "GiftVoucher{" +
                "voucherID='" + voucherID + '\'' +
                ", amount=" + amount +
                ", redeemed='" + redeemed + '\'' +
                '}';
    }
}
