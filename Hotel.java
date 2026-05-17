import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

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
            musteri.rezervasyonSayisiniArttir(); // VIP sistemi için sayacı artır
            System.out.println("Başarılı: " + musteri.getTamAd() + " için " + oda.getOdaNo() + " numaralı odaya rezervasyon yapıldı!");
        }
    }
    // Fatura Hesaplama ve VIP İndirim Mantığı
    public double faturaHesapla(Reservation res) {
        // Kalınan gün sayısını hesapla
        long gunSayisi = ChronoUnit.DAYS.between(res.getBaslangicTarihi(), res.getBitisTarihi());
        
        // Temel tutarı oda ücreti ile çarp
        double toplamTutar = gunSayisi * res.getOda().getUcret();
        Customer musteri = res.getCustomer();

        System.out.println("Fatura Detayı: " + musteri.getTamAd() + " | Oda: " + res.getOda().getOdaNo());
        System.out.println("Kalınacak Gün: " + gunSayisi + " gün | Gecelik Ücret: " + res.getOda().getUcret() + " TL");
        System.out.println("İndirimsiz Toplam Tutar: " + toplamTutar + " TL");

        // VIP İndirim Sistemi (3'ten fazla rezervasyon kontrolü)
        if (musteri.getRezervasyonSayisi() > 3) {
            double indirimMiktari = toplamTutar * 0.10; // %10 İndirim
            toplamTutar = toplamTutar - indirimMiktari;
            System.out.println("Tebrikler! 3'ten fazla rezervasyon yaptığınız için %10 VIP indirimi kazandınız.");
            System.out.println("İndirim Tutarı: -" + indirimMiktari + " TL");
        }

        System.out.println("Ödenecek Net Tutar: " + toplamTutar + " TL\n");
        return toplamTutar;
    }

    
}
