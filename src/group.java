import java.io.*;
import java.util.*;

public class group {

  //attribut
  private String namaGroup;
  private ArrayList<tim> listTim;
  private int jmlTim;

  // Method
  public group (){
  	listTim = new ArrayList<tim>();
  	jmlTim = 0;
  }


  public void setNamaGroup(String namaGroup) {
    this.namaGroup = namaGroup;
  }

  public String getNamaGroup() {
    return namaGroup;
  }

  public void addTimGroup(tim tims) {
    listTim.add(tims);
  }

  public void deleteGroup(int nomor) {
    listTim.remove(nomor);
  }

  public void printAttribut(){
  	System.out.println("Nama Group   : "+namaGroup);
  	System.out.println("Jumlah Tim   : "+jmlTim);
  }
}

