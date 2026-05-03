public class Customer {
    private String MusteriTC;
    private String TamAd;
    private String TelNo;

    public Customer(String MusteriTC, String TamAd, String TelNo) {
        this.MusteriTC = MusteriTC;
        this.TamAd = TamAd;
        this.TelNo = TelNo;

    }

    public String getTamAd() {
        return this.TamAd;
    }

    public String getMusteriTC() {
        return this.MusteriTC;
    }
}
