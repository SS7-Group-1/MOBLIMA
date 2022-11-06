package MOBLIMA.App;

import MOBLIMA.FileHelper;
import MOBLIMA.GiftVoucher;

import java.util.ArrayList;

public class GiftVouchers {
    ArrayList<GiftVoucher> gift_voucher_list;

    public GiftVouchers(){
        this.gift_voucher_list = (ArrayList<GiftVoucher>) FileHelper.read("data/giftvouchers.dat");
    }

    public void newGiftVoucher(){

    }

    public float checkGiftVoucher(){
        return 0f;
    }

    public void useGiftVoucher(){

    }
}
