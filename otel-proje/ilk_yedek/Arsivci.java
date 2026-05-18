import java.util.*;

public class Arsivci {

    private HashMap<String, List<Rezervasyon>> musteriGecmisi;
    private HashMap<Integer, List<Rezervasyon>> odaTakvim;

    public Arsivci() {
        musteriGecmisi = new HashMap<>();
        odaTakvim = new HashMap<>();
    }

    public void rezervasyonEkle(String tc, int odaNo, Date giris, Date cikis) {

        Rezervasyon r = new Rezervasyon(tc, odaNo, giris, cikis);

        musteriGecmisi.putIfAbsent(tc, new ArrayList<>());
        musteriGecmisi.get(tc).add(r);

        odaTakvim.putIfAbsent(odaNo, new ArrayList<>());
        odaTakvim.get(odaNo).add(r);
    }

    public List<Integer> doluOlmayanOdalar(List<Integer> tumOdalar, Date baslangic, Date bitis) {

        List<Integer> bosOdalar = new ArrayList<>();

        for (int oda : tumOdalar) {
            boolean dolu = false;

            List<Rezervasyon> liste = odaTakvim.getOrDefault(oda, new ArrayList<>());

            for (Rezervasyon r : liste) {
                if (r.giris.before(bitis) && baslangic.before(r.cikis)) {
                    dolu = true;
                    break;
                }
            }

            if (!dolu) {
                bosOdalar.add(oda);
            }
        }

        return bosOdalar;
    }

    public List<Rezervasyon> musteriGecmisiGetir(String tc) {
        return musteriGecmisi.getOrDefault(tc, new ArrayList<>());
    }

    public List<Rezervasyon> odaTakvimiGetir(int odaNo) {

        List<Rezervasyon> liste = odaTakvim.getOrDefault(odaNo, new ArrayList<>());

        liste.sort(Comparator.comparing(r -> r.giris));

        return liste;
    }
}
