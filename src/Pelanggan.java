public class Pelanggan {
    private String nomorPelanggan;
    private String nama;
    private String pin;
    private double saldo;
    private int percobaanGagal;
    private boolean diblokir;

    public Pelanggan(String nomorPelanggan, String nama, String pin, double saldo) {
        this.nomorPelanggan = nomorPelanggan;
        this.nama = nama;
        this.pin = pin;
        this.saldo = saldo;
        this.percobaanGagal = 0;
        this.diblokir = false;
    }
    public String getNomorPelanggan() {
        return this.nomorPelanggan;
    }
    public String getNama() {
        return this.nama;
    }
    public double getSaldo() {
        return this.saldo;
    }
    public boolean isDiblokir() {
        return this.diblokir;
    }
    public boolean autentikasi(String inputNomor, String inputPin) {
        if (this.diblokir) {
            System.out.println("Akun telah diblokir dan tidak dapat digunakan.");
            return false;
        }
        if (this.nomorPelanggan.equals(inputNomor) && this.pin.equals(inputPin)) {
            this.percobaanGagal = 0;
            return true;
        } else {
            this.percobaanGagal = this.percobaanGagal + 1;
            if (this.percobaanGagal >= 3) {
                this.diblokir = true;
                System.out.println("Autentikasi gagal 3 kali. Akun diblokir.");
            } else {
                System.out.println("Nomor pelanggan atau PIN salah. Percobaan ke-" + this.percobaanGagal);
            }
            return false;
        }
    }
    public double hitungCashback(double totalBelanja) {
        if (this.nomorPelanggan.length() != 10) {
            return 0;
        }
        String kode = this.nomorPelanggan.substring(0, 2);
        if (kode.equals("38")) {
            if (totalBelanja > 1000000) {
                return totalBelanja * 0.05;
            } else {
                return 0;
            }
        } else if (kode.equals("56")) {
            if (totalBelanja > 1000000) {
                return totalBelanja * 0.07;
            } else {
                return totalBelanja * 0.02;
            }
        } else if (kode.equals("74")) {
            if (totalBelanja > 1000000) {
                return totalBelanja * 0.10;
            } else {
                return totalBelanja * 0.05;
            }
        } else {
            return 0;
        }
    }
    public void beli(String inputNomor, String inputPin, double totalBelanja) {
        if (!autentikasi(inputNomor, inputPin)) {
            return;
        }
        if (totalBelanja <= 0) {
            System.out.println("Nominal pembelian tidak valid.");
            return;
        }
        double cashback = hitungCashback(totalBelanja);
        double saldoAkhir = this.saldo - totalBelanja + cashback;
        if (saldoAkhir < 10000) {
            System.out.println("Transaksi gagal. Saldo pasca transaksi kurang dari Rp 10.000.");
        } else {
            this.saldo = saldoAkhir;
            System.out.println("Pembelian berhasil (" + this.nama + "). Cashback: Rp " + cashback);
            System.out.println("Saldo saat ini: Rp " + this.saldo);
        }
    }
    public void topUp(String inputNomor, String inputPin, double nominal) {
        if (!autentikasi(inputNomor, inputPin)) {
            return;
        }
        if (nominal <= 0) {
            System.out.println("Nominal top up tidak valid.");
        } else {
            this.saldo = this.saldo + nominal;
            System.out.println("Top up berhasil (" + this.nama + "). Saldo saat ini: Rp " + this.saldo);
        }
    }
}