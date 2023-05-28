package TestPackage;

import java.util.Random;

public class Koloni extends Taktik implements Uretim {
    private int YemekStogu = 0;
    private int Populasyon = 0;
    private int Kazanma = 0;
    private int Kaybetme = 0;
    private char KoloniSimgesi = (char) 0;
    private boolean Yasiyor = true;

    public Koloni() {
        super();
    }

    public Koloni(int Populasyon, int YemekStogu) 
    {
        this.Populasyon = Populasyon;
        this.YemekStogu = YemekStogu;
    }

    public char getKoloniSimgesi() {
        if (KoloniSimgesi != (char) 0) return KoloniSimgesi;
        else
        {
            Random rand = new Random();
            KoloniSimgesi = (char) rand.nextInt(65);
            return KoloniSimgesi;
        }
    }
    public int getYemekStogu() {
        return YemekStogu;
    }
    public int getPopulasyon() {
        return Populasyon;
    }
    public boolean getYasiyor() {
        return Yasiyor;
    }
    public int getKazanma() {
        return Kazanma;
    }
    public int getKaybetme() {
        return Kaybetme;
    }
    public void setYemekStogu(int YemekStogu) {
        this.YemekStogu = YemekStogu;
    }
    public void setPopulasyon(int Populasyon) {
        this.Populasyon = Populasyon;
    }
    public void setYasiyor(boolean Yasiyor) {
        this.Yasiyor = Yasiyor;
    }
    public void setKazanma(int Kazanma) {
        this.Kazanma = Kazanma;
    }
    public void setKaybetme(int Kaybetme) {
        this.Kaybetme = Kaybetme;
    }

    @Override
    public int Uret() {
        Random rand = new Random();
        return rand.nextInt(11);
    }

    @Override
    public int Savas() {
        Random rnd = new Random();
        return rnd.nextInt(1001);
    }
}
