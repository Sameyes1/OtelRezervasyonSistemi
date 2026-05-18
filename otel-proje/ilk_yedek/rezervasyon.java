import java.util.Date;

public class Rezervasyon {

    String tc;
    int odaNo;
    Date giris;
    Date cikis;

    public Rezervasyon(String tc, int odaNo, Date giris, Date cikis) {
        this.tc = tc;
        this.odaNo = odaNo;
        this.giris = giris;
        this.cikis = cikis;
    }
}
