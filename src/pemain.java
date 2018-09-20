import java.io.*;
import java.util.*;

public class pemain extends orang implements Serializable{
	//atribut
	private String nim;
	private String nmr_pemain;
	private int jum_gol;
	private int cur_kuning;
	private int jum_kuning;
	private int jum_merah;
	private int match_delay;


	//konstruktor
	pemain(String nama,String nh,String nim,String np)
	/*urutan input->nama,jenis kelamin,nomor hp,nim,nomor pemain
	 * asumsi jum gol, cur kuning,jum kuning,jum merah,match delay semuanya = 0
	   pada saat melahirkan obyek baru, makanya gak di cantumin di konstruktor*/
	{
		super(nama,nh);
		this.nim = nim;
		nmr_pemain = np;
	}


	//get dan set
	//nim
	public String getnim()
	{
		return nim;
	}
	public void setnim(String nim)
	{
		this.nim = nim;
	}
	//nomor pemain
	public String getnmrpemain()
	{
		return nmr_pemain;
	}
	public void setnmrpemain(String np)
	{
		nmr_pemain = np;
	}
	//current kuning
	public int getcurkuning()
	{
		return cur_kuning;
	}
	public void addcurkuning(int ck) {
		cur_kuning += ck;
		if (cur_kuning >= 2) {
            cur_kuning = 0;
            match_delay = 2;
		}
	}
	//jumlah gol
	public int getjumgol()
	{
		return jum_gol;
	}
	public void setjumgol(int jg)
	{
		jum_gol = jg;
	}
    public void addjumgol(int jg) {
        jum_gol += jg;
    }
	//jumlah kuning
	public int getjumkuning()
	{
		return jum_kuning;
	}
	public void setjumkuning(int jk)
	{
		jum_kuning = jk;
	}
	public void addjumkuning(int jk) {
        jum_kuning += jk;
	}
	//jumlah merah
	public int getjummerah()
	{
		return jum_merah;
	}
	public void setjummerah(int jm)
	{
		jum_merah = jm;
	}
	public void addjummerah(int jm) {
        jum_merah += jm;
	}
	//match delay
	public int getdelay()
	{
		return match_delay;
	}
	public void setdelay(int md)
	{
		match_delay = md;
	}
	public void decreasedelay(int md) {
        match_delay -= md;
        if (match_delay < 0)
            match_delay = 0;
	}

	//print atribut
	public void printattribut()
	{
		//super.printattribut();
		System.out.println("NIM                     : "+nim);
		System.out.println("Nomor Pemain            : "+nmr_pemain);
		System.out.println("Jumlah Gol              : "+jum_gol);
		System.out.println("Jumlah krt kuning skrg  : "+jum_gol);
		System.out.println("Jumlah total krt kuning : "+jum_kuning);
		System.out.println("Jumlah krt merah        : "+jum_merah);
		System.out.println("Match_delay             : "+match_delay);

	}
}
