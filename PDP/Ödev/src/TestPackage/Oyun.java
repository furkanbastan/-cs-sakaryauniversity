package TestPackage;

import java.util.Random;

public class Oyun {
    public void OyunuBaslat(Koloni[] koloniler)
    {
        
        //Savaşın kaç turda biteceğini bilmediğimiz için sonsuz bir döngü kuruyoruz.
        for (int tur = 1; ; tur++)
        {
            //Her turda popülasyon %20 aartar ve yemek stoğu popülasyonun 2 katı azalır ödevde böyle istenmiş
            this.PopulasyonArttirYemekStoguAzalt(koloniler);

            //Burada savaş olur her koloni diğer kolonilerle savaşmış olur
            for (int x = 0; x < koloniler.length; x++) 
            {
                if(koloniler[x].getYasiyor() == false) continue;

                for (int y = x+1; y < koloniler.length; y++) 
                {
                    //Ölü bir koloni varsa savaştırmıyoruz
                    if(koloniler[y].getYasiyor() == false) continue;

                    int s1 = koloniler[x].Savas();
                    int s2 = koloniler[y].Savas();
                    int oran = (Math.abs(s1-s2)/1000)*100;

                    if(s1 == s2){ // Eğer savaş değerleri eşitse popülasyona göre karşılaşma olur
                        PopulasyonKarsilastir(koloniler[x], koloniler[y], oran);
                    } 
                    else if (s1 < s2) Savastir(koloniler[x], koloniler[y], oran);
                    else Savastir(koloniler[y], koloniler[x], oran);
                }
            }
            this.TuruYazdir(koloniler, tur);

            //Eğer sadece 1 adet yaşayan koloni varsa o koloni savaşı kazanmıştır ve tur biter
            if(Sadece1Yasayan(koloniler)) break;
        }
    }

    public void Savastir(Koloni koloni1, Koloni koloni2, int oran) {
        int aktarmaMiktari = koloni1.getYemekStogu()*oran;
        koloni1.setPopulasyon((koloni1.getPopulasyon()*(100-oran))/100);  //mesela %10 azaltmak yerine %90 ı olarak ayarlıyorum çünkü aynı şey
        koloni1.setYemekStogu((koloni1.getYemekStogu()*(100-oran))/100);
        koloni2.setYemekStogu(koloni2.getYemekStogu() + aktarmaMiktari);

        //Kaybetme ve kazanma sayıları güncelleştirilir
        koloni1.setKaybetme(koloni1.getKaybetme()+1);
        koloni2.setKazanma(koloni2.getKazanma()+1);

        //KoloniOldur fonksiyonda populasyon ve yemek stogu değerleri kontrol edilerek koloni öldürülür
        this.KoloniOldur(koloni1);
        this.KoloniOldur(koloni2);
    }

    public void PopulasyonKarsilastir(Koloni koloni1, Koloni koloni2, int oran) {
        if(koloni1.getPopulasyon() == koloni2.getPopulasyon()){
            Random random = new Random();
            if(random.nextInt(2) == 1) Savastir(koloni1, koloni2, oran);
            else Savastir(koloni1, koloni2, oran);
        }
        else if (koloni1.getPopulasyon() < koloni2.getPopulasyon()) Savastir(koloni1, koloni2, oran);
        else Savastir(koloni2, koloni1, oran);
    }

    public void PopulasyonArttirYemekStoguAzalt(Koloni[] koloniler) {
        for (int i = 0; i < koloniler.length; i++) {
            koloniler[i].setPopulasyon(koloniler[i].getPopulasyon());
            koloniler[i].setYemekStogu(koloniler[i].getYemekStogu() - koloniler[i].getPopulasyon()*2);
        }
    }

    public void KoloniOldur(Koloni koloni) {
        if(koloni.getPopulasyon() <= 0 || koloni.getYemekStogu() <= 0) koloni.setYasiyor(false);
    }

    public boolean Sadece1Yasayan(Koloni[] koloniler) {
        int yasayanSayisi = 0;

        for (int i = 0; i < koloniler.length; i++) {
            if(koloniler[i].getYasiyor()) yasayanSayisi++;
        }
        
        if(yasayanSayisi == 1 ) return true;
        else return false;
    }

    public void TuruYazdir(Koloni[] koloniler, int tur) {
        //Burada bu tur da savaş sonucu ekrana bastırılır ve en dış döngü turu devam ettirir
        System.out.println("Tur Sayisi: "+tur);
        System.out.print("Koloni");
        System.out.print("   Populasyon");
        System.out.print("   Yemek Stogu");
        System.out.print("   Kazanma");
        System.out.print("   Kaybetme");
        System.out.println("");
        for (int i = 0; i < koloniler.length; i++) 
        {
            //Burada yaşıyor yaşamıyor farklı yazdırılır...
            if(koloniler[i].getYasiyor())
            {
                System.out.print("     "+koloniler[i].getKoloniSimgesi());  //Koloni
                System.out.printf("%13d",koloniler[i].getPopulasyon());  //Populasyon
                System.out.printf("%14d",koloniler[i].getYemekStogu());  //Yemek Stoğu
                System.out.printf("%10d",koloniler[i].getKazanma());  //Kazanma
                System.out.printf("%11d",koloniler[i].getKaybetme());  //Kaybetme
                System.out.println("");
            }
            else
            {
                System.out.print("     "+koloniler[i].getKoloniSimgesi());  //Koloni
                System.out.print("          ---");  //Populasyon
                System.out.print("           ---");  //Yemek Stoğu
                System.out.print("       ---");  //Kazanma
                System.out.print("        ---");  //Kaybetme
                System.out.println("");
            }
            
        }
        System.out.println("");
        System.out.println("");
    }
}
