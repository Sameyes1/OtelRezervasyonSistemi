import java.time.LocalDate;

public class IntervalTreeNode {
    Reservation reservation;
    LocalDate maxCikis; // Bu düğümün altındaki tüm dalların en geç çıkış tarihi
    IntervalTreeNode left, right;

    public IntervalTreeNode(Reservation reservation) {
        this.reservation = reservation;
        // Başlangıçta en geç çıkış tarihi, kendi çıkış tarihidir
        this.maxCikis = reservation.getBitisTarihi(); 
        this.left = null;
        this.right = null;
    }
}