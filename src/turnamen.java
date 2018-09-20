import java.io.*;
import java.util.*;

class turnamen implements Serializable {

    private String nama;
    private String deskripsi;
    private int jumlah_tim;
    private int minPemain;
    private int maxPemain;
    private int round;
    private int maxRound;
    private boolean isActive;
    private boolean registerMode;
    private tim pemenang;
    private ArrayList<tim> list_tim;
    private ArrayList<Integer> list_nmrtim;
    private ArrayList<Pertandingan> list_pertandingan;
    private ArrayList< ArrayList<Pertandingan> > list_pastPertandingan;
    private ArrayList<String> list_lapangan;

    turnamen() {
        round = 0;
        jumlah_tim = 0;
        isActive = false;
        registerMode = true;
        pemenang = new tim("belum ada pemenang", "belum ada pemenang");
        list_tim = new ArrayList<tim>();
        list_nmrtim = new ArrayList<Integer>();
        list_pertandingan = new ArrayList<Pertandingan>();
        list_pastPertandingan = new ArrayList< ArrayList<Pertandingan> >();
        list_lapangan = new ArrayList<String>();
    }

    turnamen(String nama, String deskripsi, int maxPemain, int minPemain) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.maxPemain = maxPemain;
        this.minPemain = minPemain;
        round = 0;
        jumlah_tim = 0;
        isActive = false;
        registerMode = true;
        pemenang = new tim("belum ada pemenang", "belum ada pemenang");
        list_tim = new ArrayList<tim>();
        list_nmrtim = new ArrayList<Integer>();
        list_pertandingan = new ArrayList<Pertandingan>();
        list_pastPertandingan = new ArrayList< ArrayList<Pertandingan> >();
        list_lapangan = new ArrayList<String>();
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean a) {
        isActive = a;
    }

    public boolean getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(boolean a) {
        registerMode = a;
    }

    public tim getPemenang() {
        return pemenang;
    }

    public void setPemenang(tim pemenang) {
        this.pemenang = pemenang;
    }

    String getNama() {
        return nama;
    }

    String getDeskripsi() {
        return deskripsi;
    }

    tim getTim(int ind) {
        return list_tim.get(ind);
    }

    int getMaxRound() {
        return maxRound;
    }

    int getRound() {
        return round;
    }

    int getJumlahTim() {
        return jumlah_tim;
    }

    int getMaxPemain() {
        return maxPemain;
    }

    int getMinPemain() {
        return minPemain;
    }

    ArrayList<tim> getListTim() {
        return list_tim;
    }

    ArrayList<Integer> getListNmrTim() {
        return list_nmrtim;
    }

    ArrayList<Pertandingan> getListPertandingan() {
        return list_pertandingan;
    }

    ArrayList< ArrayList<Pertandingan> > getListPastPertandingan() {
        return list_pastPertandingan;
    }

    ArrayList<String> list_lapangan() {
        return list_lapangan;
    }

    void setNama(String nama) {
        this.nama = nama;
    }

    void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    void setJumlahTim(int jumlah_tim) {
        this.jumlah_tim = jumlah_tim;
    }

    void setRound(int round) {
        this.round = round;
    }

    void setMaxPemain(int maxPemain) {
        this.maxPemain = maxPemain;
    }

    void addTim(tim newTim) {
        list_tim.add(newTim);
        jumlah_tim++;
    }

    void addLapangan(String newLapangan) {
        list_lapangan.add(newLapangan);
    }

    void deleteLapangan(int index) {
        list_lapangan.remove(index);
    }

    void deleteTim(int index) {
        list_tim.remove(index);
        jumlah_tim--;
    }

    void shuffleTim(Random randomGenerator) {
        randomGenerator = new Random();
        int jmlh = list_nmrtim.size();
        for (int i = 0; i < (jmlh-1); i++) {
            int tmp = randomGenerator.nextInt(jmlh-i);
            int tmp2 = list_nmrtim.get(tmp).intValue();
            int tmp3 = list_nmrtim.get(jmlh-i-1).intValue();
            list_nmrtim.remove(tmp);
            list_nmrtim.add(tmp, new Integer(tmp3));
            list_nmrtim.remove(jmlh-i-1);
            list_nmrtim.add(jmlh-i-1, new Integer(tmp2));
        }
    }

    private int isPerfect(int a) {
        int x = 2;
        while (a > x)
            x *= 2;
        return (x / 2);
    }

    public void calculatePostRegistration() {
        registerMode = false;
        //Inisialisasi Seed
        for (int i = 0; i < list_tim.size(); i++) {
            list_nmrtim.add(new Integer(i));
            list_tim.get(i).setNmrTim(i);
        }

        //Menghitung maxRound
        int perfectNumber = isPerfect(list_nmrtim.size());
        if (perfectNumber == list_nmrtim.size())
            maxRound = 0;
        else
            maxRound = 1;
        while(perfectNumber != 1) {
            maxRound++;
            perfectNumber /= 2;
        }
    }

    //buat debug
    public void refereshNomorTim() {
        list_nmrtim.clear();
        for (int i = 0; i < list_tim.size(); i++)
            list_nmrtim.add(i);
    }

    public void buatPertandingan() {
        Pertandingan tmp;
        int perfectNumber = isPerfect(list_nmrtim.size());
        if (round > 0) {
            if (perfectNumber == list_nmrtim.size()) {
                for (int i = 0; i < (list_nmrtim.size() / 2); i++) {
                    int tmplg = list_nmrtim.get(1 + i*2 - i);
                    list_nmrtim.remove(1 + i*2 - i);
                    list_nmrtim.add(new Integer(tmplg));
                }
            }
            else {//
                int x = list_nmrtim.size() - perfectNumber;
                int decideMode = (x-1) / (perfectNumber / 2);
                int earlyMatch = ((x-1) % (perfectNumber / 2)) + 1;
                if (decideMode == 0) {
                    for (int i = 0; i < earlyMatch; i++) {
                        int tmplg = list_nmrtim.get(2 + i*3 - i).intValue();
                        list_nmrtim.remove(2 + i*3 - i);
                        list_nmrtim.add(new Integer(tmplg));
                    }
                }
                else {
                    for (int i = 0; i < earlyMatch; i++) {
                        int tmplg = list_nmrtim.get(1 + i*2);
                        list_nmrtim.remove(1 + i*2);
                        list_nmrtim.add(new Integer(tmplg));
                        tmplg = list_nmrtim.get(2 + i*2);
                        list_nmrtim.remove(2 + i*2);
                        list_nmrtim.add(new Integer(tmplg));
                    }
                    for (int i = 0; i < (4 - earlyMatch); i++) {
                        int tmplg = list_nmrtim.get((2*earlyMatch-1)+(3+3*i)).intValue();
                        list_nmrtim.remove((2*earlyMatch-1)+(3+3*i));
                        list_nmrtim.add(new Integer(tmplg));
                    }
                }
            }
            int ukur = list_nmrtim.size();
            if (perfectNumber == ukur) {
                for (int i = ukur / 2; i < ukur; i++)
                    list_nmrtim.remove(ukur/2);
            }
            else {
                for (int i = perfectNumber; i < ukur; i++)
                    list_nmrtim.remove(perfectNumber);
            }
        }
        round++;
		Random randomGenerator = new Random();
		shuffleTim(randomGenerator);
        perfectNumber = isPerfect(list_nmrtim.size());
        if (perfectNumber == list_nmrtim.size()) {
            for (int i = 0; i < (list_nmrtim.size() / 2); i++) {
                tmp = new Pertandingan();
                tmp.setTim1(list_tim.get(list_nmrtim.get(i*2).intValue()));
                tmp.setTim2(list_tim.get(list_nmrtim.get(i*2+1).intValue()));
                list_pertandingan.add(tmp);
            }
        }
        else {
            int x = list_nmrtim.size() - perfectNumber;
            int decideMode = (x-1) / (perfectNumber / 2);
            int earlyMatch = ((x-1) % (perfectNumber / 2)) + 1;
            if (decideMode == 0) {
                for (int i = 0; i < earlyMatch; i++) {
                    tmp = new Pertandingan();
                    tmp.setTim1(list_tim.get(list_nmrtim.get(1 + (i*3)).intValue()));
                    tmp.setTim2(list_tim.get(list_nmrtim.get(2 + (i*3)).intValue()));
                    list_pertandingan.add(tmp);
                }
            }
            else {
                for (int i = 0; i < earlyMatch; i++) {
                    tmp = new Pertandingan();
                    tmp.setTim1(list_tim.get(list_nmrtim.get(i*4).intValue()));
                    tmp.setTim2(list_tim.get(list_nmrtim.get(i*4+1).intValue()));
                    list_pertandingan.add(tmp);
                    tmp = new Pertandingan();
                    tmp.setTim1(list_tim.get(list_nmrtim.get(i*4+2).intValue()));
                    tmp.setTim2(list_tim.get(list_nmrtim.get(i*4+3).intValue()));
                    list_pertandingan.add(tmp);
                }
                for (int i = 0; i < (4 - earlyMatch); i++) {
                    tmp = new Pertandingan();
                    tmp.setTim1(list_tim.get(list_nmrtim.get(earlyMatch*perfectNumber/2+(i*3)).intValue()));
                    tmp.setTim1(list_tim.get(list_nmrtim.get(earlyMatch*perfectNumber/2+(i*3)+1).intValue()));
                    list_pertandingan.add(tmp);
                }
            }
        }
    }
}

