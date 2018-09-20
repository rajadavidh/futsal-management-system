import java.io.*;
import java.util.*;

public class tim implements Serializable {

  // Anfang Attribute
  private String namaTim;
  private int nmrTim;
  private pemain kapten;
  private ArrayList<pemain> listPemain;
  private int jmlPemain;
  private String deskripsi;
  private int menang;
  private int kalah;
  private int seri;
  private int goal;
  private int kebobolan;
  private boolean adaKapten;

  //method
  tim(){
    listPemain = new ArrayList<pemain>();
  	jmlPemain = 0;
  	menang = 0;
  	kalah = 0;
  	seri = 0;
  	goal = 0;
  	kebobolan = 0;
  	adaKapten = false;
  }

  tim (String namaTim, String deskripsi) {
    this.namaTim = namaTim;
    this.deskripsi = deskripsi;
    listPemain = new ArrayList<pemain>();
  	jmlPemain = 0;
  	menang = 0;
  	kalah = 0;
  	seri = 0;
  	goal = 0;
  	kebobolan = 0;
    adaKapten = false;
  }

  public void setNamaTim(String namaTim) {
    this.namaTim= namaTim;
  }

  public String getNamaTim() {
    return namaTim;
  }

  public void setNmrTim(int nmrTim) {
    this.nmrTim = nmrTim;
  }

  public int getNmrTim() {
    return nmrTim;
  }

  public void setKapten(pemain kapten) {
    this.kapten = kapten;
    adaKapten = true;
  }

  public pemain getKapten() {
    return kapten;
  }

  public boolean getAdaKapten() {
    return adaKapten;
  }

  public pemain getPemain(int ind) {
    return listPemain.get(ind);
  }

  public int getJmlPemain() {
    return jmlPemain;
  }

  public String getDeskripsi() {
    return deskripsi;
  }

  public void setDeskripsi(String deskripsi) {
    this.deskripsi = deskripsi;
  }

  public void addPemainTim(pemain pemains) {
	listPemain.add(pemains);
	jmlPemain++;
  }

  public void deletePemainTim(int nomor){
    if (adaKapten) {
        if (kapten.getnama().equals(listPemain.get(nomor).getnama())) {
            adaKapten = false;
        }
    }
    listPemain.remove(nomor);
    jmlPemain--;
  }

  public void printAttribut() {
	System.out.println("Nama Tim      : "+namaTim);
  	System.out.println("Kapten        : "+kapten);
	System.out.println("Jumlah Pemain : "+jmlPemain);
  }

  public void topScoreTim() {
	int maks = 0;
	for (int i=0;i<jmlPemain;i++){
		if (listPemain.get(i).getjumgol() > maks){
			maks = listPemain.get(i).getjumgol();
		}
  	}
  }

  public void refreshMatchDelay() {
	for (int i=0;i<jmlPemain;i++){
        listPemain.get(i).decreasedelay(1);
  	}
  }

  public ArrayList<pemain> cariPemainBisaMain() {
    ArrayList<pemain> tmp = new ArrayList<pemain>();
    for (int i = 0; i < jmlPemain; i++) {
        if (listPemain.get(i).getdelay() == 0)
            tmp.add(listPemain.get(i));
    }
    return tmp;
  }

  public int getTotalKMerah() {
    int total = 0;
    for (int i=0;i<jmlPemain;i++){
		total = total + listPemain.get(i).getjummerah();
  	}
  	return total;
  }

  public int getTotalKKuning() {
    int total=0;
    for (int i=0;i<jmlPemain;i++){
		total = total + listPemain.get(i).getjumkuning();
  	}
  	return total;
  }

  public int getTotalMain() {
    return menang+kalah+seri;
  }

  public int getMenang() {
    return menang;
  }

  public int getKalah() {
    return kalah;
  }

  public int getSeri() {
    return seri;
  }

  public void addMenang() {
    menang++;
  }

  public void addKalah() {
	kalah++;
  }

  public void addSeri() {
	seri++;
  }

  public void addGoal(int x) {
	goal += x;
  }

  public void addKebobolan(int x) {
	kebobolan += x;
  }

  public int getGoal() {
    return goal;
  }

  public int getKebobolan() {
    return kebobolan;
  }

  public int getSelisihGoal() {
    return (goal-kebobolan);
  }
}
