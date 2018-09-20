import java.io.*;
import java.util.*;

class Klasemen extends Knockout {

    private ArrayList<group> listGrup;
    private int maxPemainPerGroup;

    Klasemen() {
        super();
    }

    Klasemen(String nama, String deskripsi, int maxPemain, int maxPemainPerGroup) {
        super(nama, deskripsi, maxPemain);
        this.maxPemainPerGroup = maxPemainPerGroup;
    }

}


