import java.time.LocalDate;

public class IntervalTree {
    private IntervalTreeNode root;

    // 1. REZERVASYON EKLEME
    //
    public void insert(Reservation res) {
        root = insertRecursive(root, res);
        // ağacımızı oluşturuyoruz
    }

    private IntervalTreeNode insertRecursive(IntervalTreeNode node, Reservation res) {
        if (node == null)
            return new IntervalTreeNode(res);// eğer node boşsa oraya yerleşiyor

        // Giriş tarihine göre sola veya sağa ekle
        if (res.getBaslangicTarihi().isBefore(node.reservation.getBaslangicTarihi())) {
            node.left = insertRecursive(node.left, res);
            // tarihleri kıyaslıyoruz eğer baktığımız yerdeki tarih bizim tarihimizden
            // büyükse biz sola gidiyoruz
        } else {
            node.right = insertRecursive(node.right, res);
        }
        // yine tarihkeri kıyasladık bizim tarihimiz büyükse sola koyuyoruz

        // Düğüm eklenirken yukarı doğru maxCikis değerlerini güncelle
        if (node.maxCikis.isBefore(res.getBitisTarihi())) {
            node.maxCikis = res.getBitisTarihi();
        }
        /*
         * Bu maks çıkış mantığında şimdi bi müşterimiz var diyelim mayısın 13 ü ile 17
         * si arasında kalıcak
         * diğer müşteri de 20 si ile 24 ü arasında kalıcak burda bizim maks çıkışımız
         * 24 mayıs
         * şimdi eğer başka birisi gelip 26 mayıs boş mu diye sorduğunda sorgu yapmadan
         * direkt maks çıkışa bakılacak
         * ve boş olduğu anlaşılacak çünkü 24 mayıstan sonra bütün tarihler kesinlikle
         * boş
         */

        return node;
    }

    // 2. ÇAKIŞMA KONTROLÜ (OVERLAP CHECK)
    public boolean isOverlapping(LocalDate start, LocalDate end) {
        return checkOverlap(root, start, end);
        /*
         * bize verilen başlama bitiş tarihlerine roota bakar ve
         * odanın boş olup olmadığını söyler
         */
    }

    private boolean checkOverlap(IntervalTreeNode node, LocalDate start, LocalDate end) {
        if (node == null)
            return false;

        // Klasik çakışma kuralı: (Yeni Giriş < Mevcut Çıkış) VE (Yeni Çıkış > Mevcut
        // Giriş)
        if (start.isBefore(node.reservation.getBitisTarihi()) &&
                end.isAfter(node.reservation.getBaslangicTarihi())) {
            return true;
        }

        // Eğer sol dal boş değilse ve solun max çıkış tarihi bizim girişimizden
        // sonraysa;
        // sol tarafta çakışma ihtimali vardır, sola git.
        if (node.left != null && node.left.maxCikis.isAfter(start)) {
            return checkOverlap(node.left, start, end);
        }

        // Değilse sağa bak
        return checkOverlap(node.right, start, end);
    }
}