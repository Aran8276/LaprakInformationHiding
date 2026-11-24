import java.util.Scanner;

public class SwalayanTiny {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pelanggan p1 = new Pelanggan("265150601111020", "zahran", "6767", 1500000);
        Pelanggan p2 = new Pelanggan("6767676767", "toyota", "1234", 2000000);
        Pelanggan p3 = new Pelanggan("1236547890", "cyberleek", "1234", 3000000);
        int pilihan = 0;

        do {
            System.out.println("1. Pembelian");
            System.out.println("2. Top Up");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();

            if (pilihan == 1 || pilihan == 2) {
                System.out.print("Masukkan Nomor Pelanggan: ");
                String nomor = input.next();
                System.out.print("Masukkan PIN: ");
                String pin = input.next();

                Pelanggan aktif = null;
                if (nomor.equals(p1.getNomorPelanggan())) {
                    aktif = p1;
                } else if (nomor.equals(p2.getNomorPelanggan())) {
                    aktif = p2;
                } else if (nomor.equals(p3.getNomorPelanggan())) {
                    aktif = p3;
                }

                if (aktif == null) {
                    System.out.println("Nomor pelanggan tidak ditemukan.");
                } else if (pilihan == 1) {
                    System.out.print("Masukkan Total Pembelian: ");
                    double totalBelanja = input.nextDouble();
                    aktif.beli(nomor, pin, totalBelanja);
                } else if (pilihan == 2) {
                    System.out.print("Masukkan Nominal Top Up: ");
                    double nominal = input.nextDouble();
                    aktif.topUp(nomor, pin, nominal);
                }
            } else if (pilihan != 0) {
                System.out.println("Menu tidak valid.");
            }
        } while (pilihan != 0);

        input.close();
    }
}