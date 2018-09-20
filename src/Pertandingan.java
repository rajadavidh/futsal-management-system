import java.io.*;
import java.util.*;

class Pertandingan implements Serializable {

    private tim tim1;
    private tim tim2;
    private String jadwal;
    private String lapangan;
    private int skor_tim1;
    private int skor_tim2;
    private boolean selesai;
    private ArrayList<pemain> list_penggolTim1;
    private ArrayList<pemain> list_penggolTim2;
    private ArrayList<pemain> list_kartuKuningTim1;
    private ArrayList<pemain> list_kartuKuningTim2;

    Pertandingan() {
        selesai = false;
        list_penggolTim1 = new ArrayList<pemain>();
        list_penggolTim2 = new ArrayList<pemain>();
        list_kartuKuningTim1 = new ArrayList<pemain>();
        list_kartuKuningTim2 = new ArrayList<pemain>();
    }

    Pertandingan(tim tim1, tim tim2, String jadwal, String lapangan) {
        this.tim1 = tim1;
        this.tim2 = tim2;
        this.jadwal = jadwal;
        this.lapangan = lapangan;
        skor_tim1 = 0;
        skor_tim2 = 0;
        selesai = false;
    }

    tim getTim1() {
        return tim1;
    }

    tim getTim2() {
        return tim2;
    }

    String getJadwal() {
        return jadwal;
    }

    String getLapangan() {
        return lapangan;
    }

    int getSkor_tim1() {
        return skor_tim1;
    }

    int getSkor_tim2() {
        return skor_tim2;
    }

    boolean getSelesai() {
        return selesai;
    }

    void setTim1(tim tim1) {
        this.tim1 = tim1;
    }

    void setTim2(tim tim2) {
        this.tim2 = tim2;
    }

    void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }

    void setSkor_tim1(int skor_tim1) {
        this.skor_tim1 = skor_tim1;
    }

    void setSkor_tim2(int skor_tim2) {
        this.skor_tim2 = skor_tim2;
    }

    void addPenggolTim1(pemain goaler) {
        list_penggolTim1.add(goaler);
    }

    void addPenggolTim2(pemain goaler) {
        list_penggolTim2.add(goaler);
    }

    void addKartuKuningTim1(pemain goaler) {
        list_kartuKuningTim1.add(goaler);
    }

    void addKartuKuningTim2(pemain goaler) {
        list_kartuKuningTim2.add(goaler);
    }

    //return true kalau berhasil wrap-up match
    //return false kalau gagal wrap-up match karena sebelumnya sudah di-wrap-up
    boolean wrapping_up() {
        if (!selesai) {
            selesai = true;
            if (skor_tim1 > skor_tim2) {
                tim1.addMenang();
                tim2.addKalah();
            }
            else if (skor_tim1 < skor_tim2) {
                tim1.addKalah();
                tim2.addMenang();
            }
            else {
                tim1.addSeri();
                tim2.addSeri();
            }
            for (int i = 0; i < list_penggolTim1.size(); i++)
                list_penggolTim1.get(i).addjumgol(1);
            for (int i = 0; i < list_penggolTim2.size(); i++)
                list_penggolTim2.get(i).addjumgol(1);
            for (int i = 0; i < list_kartuKuningTim1.size(); i++) {
                list_kartuKuningTim1.get(i).addjumkuning(1);
                list_kartuKuningTim1.get(i).addcurkuning(1);
            }
            for (int i = 0; i < list_kartuKuningTim2.size(); i++) {
                list_kartuKuningTim2.get(i).addjumkuning(1);
                list_kartuKuningTim2.get(i).addcurkuning(1);
            }
            return true;
        }
        else return false;
    }
}
