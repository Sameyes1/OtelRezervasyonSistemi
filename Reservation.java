import java.time.LocalDate;

public class Reservation {

    private Customer Musteri;
    private Room Oda;
    private LocalDate BaslangicTarihi;
    private LocalDate BitisTarihi;

    public Reservation(Customer Musteri, Room Oda, LocalDate BaslangicTarihi, LocalDate BitisTarihi) {
        this.Musteri = Musteri;
        this.Oda = Oda;
        this.BaslangicTarihi = BaslangicTarihi;
        this.BitisTarihi= BitisTarihi;
    }

    public Customer getCustomer() {
        return this.Musteri;
    }

    public Room getOda() {
        return this.Oda;
    }

    public LocalDate getBaslangicTarihi() {
        return this.BaslangicTarihi;
    }

    public LocalDate getBitisTarihi() {
        return this.BitisTarihi;
    }

}