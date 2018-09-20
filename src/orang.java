import java.io.*;
import java.util.*;

public class orang implements Serializable {
	//attribut
	private String nama;
	private String nomor_hp;


	//konstruktor
	orang(String nama, String nmr)
	//urutan input->nama,jenis kelamin,nomor hp
	{
		this.nama = nama;
		nomor_hp = nmr;
	}


	//get dan set
	//nama
	public String getnama()
	{
		return nama;
	}
	public void setnama(String nama)
	{
		this.nama = nama;
	}
	//nomor hp
	public String getnmrhp()
	{
		return nomor_hp;
	}
	public void setnmr(String nmr)
	{
		nomor_hp = nmr;
	}


	//print atribut
	public void printattribut()
	{
		System.out.println("Nama          : "+nama);
		System.out.println("Nomor Hp      : "+nomor_hp);
	}
}
