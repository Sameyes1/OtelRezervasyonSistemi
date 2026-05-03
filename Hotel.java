import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {
    private HashMap<String, Customer> Musteriler; // tc girildiginde musterinin butun bilgilerini alicagiz
    private HashMap<String, Room> Odalar; // oda no girildiginde oda hakkinda bilgileri alicagiz
    private ArrayList<Reservation> rezervasyonlar;

    public Hotel() {
        this.Musteriler = new HashMap<>(); // hafizada Hashmapleri Olusturuyor
        this.Odalar = new HashMap<>();
        this.rezervasyonlar = new ArrayList<>();
    }

    public void MusteriEkle(Customer yeniMusteri) {
        String tc = yeniMusteri.getMusteriTC(); // musterinin tcsini getter ile alip key yapicagiz
        this.Musteriler.put(tc, yeniMusteri); // musterinin tc sini hashmap de key yapiyoruz

    }

    public void OdaEkle(Room yeniOda) {
        String odano = yeniOda.getOdaNo();
        this.Odalar.put(odano, yeniOda);

    }

    public void RezervasyonYap(String MusteriTC, String IstenenOdaNo, LocalDate BaslangicTarihi, LocalDate BitisTarihi)

    {
        Customer musteri = Musteriler.get(MusteriTC);
        Room oda = Odalar.get(IstenenOdaNo);
        // Eğer yanlış TC veya Oda No girildiyse sistemi durdur
        if (musteri == null || oda == null) {
            System.out.println("Hata: Sistemde böyle bir müşteri veya oda bulunamadı!");
            return; 
        }
        if (oda.getTakvim().isOverlapping(BaslangicTarihi, BitisTarihi )) {
            // Eğer true (dolu) dönerse:
            System.out.println("Maalesef, " + oda.getOdaNo() + " numaralı oda bu tarihlerde DOLU!");
        } else {
            //Eğer false  dönerse: Rezervasyonu oluştur ve takvime ekle
            Reservation yeniRezervasyon = new Reservation(musteri, oda, BaslangicTarihi, BitisTarihi);
            oda.getTakvim().insert(yeniRezervasyon);
            System.out.println("Başarılı: " + musteri.getTamAd() + " için " + oda.getOdaNo() + " numaralı odaya rezervasyon yapıldı!");
        }
    }

    
}
