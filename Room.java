public class Room {
    private String OdaNo;
    private int Kapasite;
    private int Ucret;
    private boolean DoluMu;

    public Room(String OdaNo,int Kapasite,int Ucret,boolean DoluMu ){
        this.OdaNo=OdaNo;
        this.Kapasite=Kapasite;
        this.Ucret=Ucret;
        this.DoluMu=DoluMu;
    }
    public String getOdaNo(){
        return OdaNo;
    }
    public boolean DoluMu(){
        return DoluMu;
    }
    public void setAvailable(boolean DoluMu) {
        this.DoluMu = DoluMu;
    }
    //Odanın kendi akıllı takvimi
    private IntervalTree takvim; 

    public Room(String odaNo, int kapasite, int ucret) {
        this.OdaNo = odaNo;
        this.Kapasite = kapasite;
        this.Ucret = ucret;
        // Oda oluşturulduğu an, takvimi de bomboş bir şekilde duvara asılır
        this.takvim = new IntervalTree(); 
    }

    // Dışarıdan takvime ulaşabilmek için
    public IntervalTree getTakvim() {
        return takvim;
    }

    public int getUcret() {
        return this.Ucret;
    }
}
