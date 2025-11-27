/* 
 * Ad Soyad: Muhammed Eren Yaprakcı
 * Öğrenci No: 250542006
 * Proje Adı: Sinema Bileti Fiyat Hesaplama Sistemi
 * Açıklama: Bu program, sinema bileti fiyatını kullanıcının
 *           girdiği bilgilere göre hesaplar ve bilet detaylarını gösterir.
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Sinema Bileti Hesaplama Sistemi ---");

        System.out.print("Haftanın Günü (1:Pzt, 2:Sal, 3:Çar, 4:Per, 5:Cum, 6:Cmt, 7:Paz): ");
        int gun = scanner.nextInt();

        System.out.print("Seans Saati (0-23 arası tam sayı, örn: 14): ");
        int saat = scanner.nextInt();

        System.out.print("Yaşınız: ");
        int yas = scanner.nextInt();

        System.out.println("Meslek Seçiniz (1: Öğrenci, 2: Öğretmen, 3: Diğer): ");
        int meslek = scanner.nextInt();

        System.out.println("Film Türü (1: 2D, 2: 3D, 3: IMAX, 4: 4DX): ");
        int filmTuru = scanner.nextInt();

        double bazFiyat = calculateBasePrice(gun, saat);
        double indirimOrani = calculateDiscount(yas, meslek, gun);
        double formatEkstra = getFormatExtra(filmTuru);
        double sonFiyat = calculateFinalPrice(bazFiyat, indirimOrani, formatEkstra);

        generateTicketInfo(gun, saat, yas, meslek, filmTuru, sonFiyat, indirimOrani);
        
        scanner.close();
    }

    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftaSonu) {
            return matine ? 55.0 : 85.0;
        } else {
            return matine ? 45.0 : 65.0;
        }
    }

    public static double calculateDiscount(int yas, int meslek, int gun) {
        if (yas < 12) return 0.25;
        if (yas >= 65) return 0.30;

        double indirim = 0.0;

        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) {
                    indirim = 0.20;
                } else {
                    indirim = 0.15;
                }
                break;
            case 2: // Öğretmen
                if (gun == 3) {
                    indirim = 0.35;
                }
                break;
            case 3: // Diğer
                indirim = 0.0;
                break;
            default:
                System.out.println("Hatalı meslek kodu!");
        }
        return indirim;
    }

    public static double getFormatExtra(int filmTuru) {
        double ekstra = 0.0;
        switch (filmTuru) {
            case 1: // 2D
                ekstra = 0.0;
                break;
            case 2: // 3D
                ekstra = 25.0;
                break;
            case 3: // IMAX
                ekstra = 35.0;
                break;
            case 4: // 4DX
                ekstra = 50.0;
                break;
            default:
                System.out.println("Hatalı film türü!");
        }
        return ekstra;
    }

    public static double calculateFinalPrice(double bazFiyat, double indirimOrani, double ekstra) {
        double indirimliFiyat = bazFiyat - (bazFiyat * indirimOrani);
        return indirimliFiyat + ekstra;
    }

    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int tur, double fiyat, double indirimOrani) {
        System.out.println("\n--- BİLET DETAYI ---");
        
        String gunIsmi = "";
        switch(gun) {
            case 1:
                gunIsmi="Pazartesi";
                break;
            case 2:
                gunIsmi="Salı";
                break;
            case 3:
                gunIsmi="Çarşamba";
                break;
            case 4:
                gunIsmi="Perşembe";
                break;
            case 5:
                gunIsmi="Cuma";
                break;
            case 6:
                gunIsmi="Cumartesi";
                break;
            case 7:
                gunIsmi="Pazar";
                break;
            default:
                gunIsmi="Hatalı Gün";
        }
        
        System.out.println("Gün/Saat: " + gunIsmi + " / " + saat + ":00");
        System.out.println("Uygulanan İndirim: %" + (int)(indirimOrani * 100));
        
        String turAd = "";
        if(tur == 1){
            turAd = "2D";
        } else if(tur == 2){
            turAd = "3D";
        } else if(tur == 3){
            turAd = "IMAX";
        } else if(tur == 4){
            turAd = "4DX";
        } else{
            turAd = "Hatalı Tür";
        }
        System.out.println("Format: " + turAd);
        
        System.out.printf("TOPLAM TUTAR: %.2f TL\n", fiyat);
        System.out.println("--------------------");
    }
}
