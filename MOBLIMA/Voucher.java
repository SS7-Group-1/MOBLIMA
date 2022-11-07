package MOBLIMA;

import java.io.Serializable;

/**
 * A class that represents vouchers to be used when purchasing movie tickets
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class Voucher implements Serializable {
    /**
     * unique code to identify that it is a voucher
     */
    private String voucherCode;
    /**
     * value of the voucher
     */
    private float amount;
    /**
     * maximum usage that the voucher can be used
     */
    private int maxUses;
    /**
     * to denote if the voucher is still valid for use
     */
    private boolean valid;

    /**
     * creates a new Voucher with the valid flag set to true
     */
    public Voucher(){
        this.valid = true;
    }

    /**
     * creates a new Voucher with voucherCode, amount, maxUses initialised to the respective arguments passed in
     * @param voucherID unique code to identify that it is a voucher
     * @param amount value of the voucher
     * @param maxUses maximum usage that the voucher can be used
     * initialises the valid flag to false
     */
    public Voucher(String voucherID, float amount, int maxUses){
        this.voucherCode = voucherID;
        this.amount = amount;
        this.valid = false;
        this.maxUses = maxUses;
    }

    /**
     * Accessor for the voucherCode of the voucher
     * @return voucherCode: unique code to identify that it is a voucher
     */
    public String getVoucherCode() {
        return voucherCode;
    }

    /**
     * Mutator fo the voucherCode of the voucher
     * @param voucherCode unique code to identify that it is a voucher
     */
    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    /**
     * Accessor for the amount of the voucher
     * @return amount: value of the voucher
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Mutator for the amount of the voucher
     * @param amount value of the voucher
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * keep tracks of the remaining uses left for the voucher
     * if maxUses == 0, the valid flag for the voucher would be declared as false and the voucher would not be usable
     */
    public void useVoucher(){
        if(this.maxUses > 0){
            this.maxUses--;
            if(maxUses == 0){
                this.valid = false;
            }
        }
    }

    /**
     * checks if the voucher is still valid for use
     * @return boolean value that denotes if voucher is still valid for use
     */
    public Boolean isValid() {
        if(this.maxUses > 0){
            return true;
        }
        else return this.maxUses != 0;
    }

    /**
     * Mutator for the maximum usage that the voucher can be used
     * @param maxUses maximum usage that the voucher can be used
     */
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    /**
     * Accessor for the maximum usage that the voucher can be used
     * @return maxUses: maximum usage that the voucher can be used
     */
    public int getMaxUses(){
        return this.maxUses;
    }

    /**
     * Mutator for the valid flag of the voucher
     * @param valid to denote if the voucher is still valid for use
     */
    public void setValidity(Boolean valid) {
        this.valid = valid;
    }

    /**
     *
     * @return boolean value to denote if voucher has unlimited uses
     */
    public Boolean unlimitedUses(){
        return this.maxUses == -1;
    }


}
