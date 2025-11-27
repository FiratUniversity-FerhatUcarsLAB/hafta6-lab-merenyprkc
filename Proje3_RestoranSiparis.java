/* 
 * Ad Soyad: Muhammed Eren Yaprakcı
 * Öğrenci No: 250542006
 * Proje Adı: Restoran Sipariş ve Fiyat Hesaplama Sistemi
 * Açıklama: Bu program, bir restoran sipariş sistemini simüle eder.
 *           Kullanıcıdan sipariş bilgilerini alır, fiyatları hesaplar,
 *           indirimleri uygular ve toplam tutarı gösterir.
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Sipariş Sistemi ---");

        System.out.println("ANA YEMEKLER: 1-Tavuk(85) 2-Kebap(120) 3-Levrek(110) 4-Mantı(65) 0-İstemiyorum");
        System.out.print("Seçiminiz: ");
        int anaYemekSecim = scanner.nextInt();

        System.out.println("BAŞLANGIÇLAR: 1-Çorba(25) 2-Humus(45) 3-Börek(55) 0-İstemiyorum");
        System.out.print("Seçiminiz: ");
        int baslangicSecim = scanner.nextInt();

        System.out.println("İÇECEKLER: 1-Kola(15) 2-Ayran(12) 3-M.Suyu(35) 4-Limonata(25) 0-İstemiyorum");
        System.out.print("Seçiminiz: ");
        int icecekSecim = scanner.nextInt();

        System.out.println("TATLILAR: 1-Künefe(65) 2-Baklava(55) 3-Sütlaç(35) 0-İstemiyorum");
        System.out.print("Seçiminiz: ");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat kaç? (0-23): ");
        int saat = scanner.nextInt();

        System.out.print("Hafta içi mi? (Evet: true, Hayır: false): ");
        boolean haftaIci = scanner.nextBoolean();

        System.out.print("Öğrenci misiniz? (Evet: true, Hayır: false): ");
        boolean ogrenci = scanner.nextBoolean();

        double anaYemekFiyat = getMainDishPrice(anaYemekSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        if (isHappyHour(saat) && icecekFiyat > 0) {
            System.out.println("Bilgi: Happy Hour saati! İçecekte %20 indirim uygulandı.");
            icecekFiyat = icecekFiyat * 0.80; 
        }

        double toplamTutar = anaYemekFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        boolean comboVarMi = isComboOrder(anaYemekFiyat > 0, icecekFiyat > 0, tatliFiyat > 0);
        
        boolean gecerliOgrenci = ogrenci && haftaIci;

        double indirimMiktari = calculateDiscount(toplamTutar, comboVarMi, gecerliOgrenci, saat);
        double odenecekTutar = toplamTutar - indirimMiktari;
        
        double bahsis = calculateServiceTip(odenecekTutar);

        System.out.println("\n--- HESAP DÖKÜMÜ ---");
        System.out.println("Ürün Toplamı: " + toplamTutar + " TL");
        if(comboVarMi) System.out.println("- Combo Menü İndirimi (%15)");
        if(gecerliOgrenci) System.out.println("- Öğrenci İndirimi (%10)");
        if(toplamTutar > 200) System.out.println("- Sepet Tutarı İndirimi (%10)");
        
        System.out.println("Toplam İndirim: -" + indirimMiktari + " TL");
        System.out.println("-------------------------");
        System.out.println("ÖDENECEK TUTAR: " + odenecekTutar + " TL");
        System.out.println("Önerilen Bahşiş (%10): " + bahsis + " TL");
        
        scanner.close();
    }

    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1:
                return 85.0;
            case 2:
                return 120.0;
            case 3:
                return 110.0;
            case 4:
                return 65.0;
            default:
                return 0.0;
        }
    }

    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1:
                return 25.0;
            case 2:
                return 45.0;
            case 3:
                return 55.0;
            default:
                return 0.0;
        }
    }

    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1:
                return 15.0;
            case 2:
                return 12.0;
            case 3:
                return 35.0;
            case 4:
                return 25.0;
            default:
                return 0.0;
        }
    }

    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1:
                return 65.0;
            case 2:
                return 55.0;
            case 3:
                return 35.0;
            default:
                return 0.0;
        }
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }

    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double toplamIndirim = 0.0;

        if (combo) {
            toplamIndirim += tutar * 0.15;
        }

        if (tutar > 200) {
            toplamIndirim += tutar * 0.10;
        }

        if (ogrenci) {
            toplamIndirim += tutar * 0.10;
        }

        return toplamIndirim;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
