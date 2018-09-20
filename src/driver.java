import java.io.*;
import java.util.*;
import java.lang.*;

public class driver {

    private static turnamen curTourney;
    private static ArrayList<turnamen> pastTourney;
    private static Random randomGenerator;

	//fungsi baca string
	public static String bacaString()
	{
		BufferedReader bacadata = new BufferedReader(new InputStreamReader(System.in));
		String temp = "";
		try {
			temp = bacadata.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	//fungsi untuk mem-pause command prompt
	public static void pausing() {
		BufferedReader bacadata = new BufferedReader(new InputStreamReader(System.in));
		String temp = "";
		try {
			temp = bacadata.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//fungsi baca integer
	public static int bacaInteger()
	{
		return Integer.parseInt(bacaString());
	}

	//fungsi baca double
	public static double bacaDouble()
	{
		return Double.parseDouble(bacaString());
	}

	//fungsi baca float
	public static float bacaFloat()
	{
		return Float.parseFloat(bacaString());
	}

	//fungsi yang selalu dijalankan pada awal program
	public static void inisialisasi() {
        curTourney = new turnamen();
        pastTourney = new ArrayList<turnamen>();
        randomGenerator = new Random();
	}

	//fungsi yang menanyakan pertanyaan yang butuh jawaban y atau n
	public static boolean ynquestion(String question) {
        boolean ulang = true;
        String jawab;
        do {
            System.out.print(question);
            jawab = bacaString();
            if (jawab.length() > 1)
                System.out.println("Input pilihan harus y atau n");
            else if ((jawab.charAt(0) != 'y') && (jawab.charAt(0) != 'n') && (jawab.charAt(0) != 'Y') && (jawab.charAt(0) != 'N'))
                System.out.println("Input pilihan harus y atau n");
            else
                ulang = false;
        } while(ulang);
        if ((jawab.charAt(0) == 'y') || (jawab.charAt(0) == 'Y'))
            return true;
        else
            return false;
	}

	//fungsi untuk menghapus turnamen yang sedang berjalan sekarang
	public static boolean hapusTurnamen() {
        boolean jadi;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Hapus Turnamen");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        System.out.println("Nama Turnamen       : " + curTourney.getNama());
        System.out.println("Deskripsi Turnamen  : " + curTourney.getDeskripsi());
        System.out.println("Max Pemain/Tim      : " + curTourney.getMaxPemain());
        System.out.println("");
        if (ynquestion("Apakah anda yakin menghapus turnamen ini (y/n)? ")) {
            curTourney.setIsActive(false);
            if (ynquestion("Apakah anda ingin menyimpan data turnamen secara permanen di Arsip Turnamen Lama? "))
                pastTourney.add(curTourney);
            return true;
        }
        else
            return false;
	}

	//fungsi untuk mendaftarkan tim baru pada turnamen sekarang
	public static void tambahTim() {
        String nama;
        String deskripsi;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Tambah Tim");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        System.out.print("Nama Tim          : ");
        nama = bacaString();
        System.out.print("Deskripsi Tim     : ");
        deskripsi = bacaString();
        System.out.println("");
        System.out.println("Tim telah terdaftar!");
        tim tmp = new tim(nama, deskripsi);
        curTourney.addTim(tmp);
	}

	//fungsi pertanyaan yang membutuhkan jawaban 
	public static String ubahDataString(String question) {
        String inputan;
        System.out.print(question);
        inputan = bacaString();
        return inputan;
	}

	//fungsi untuk mengubah kapten suatu tim
	public static void ubahKaptenTim(tim timNow) {
        if (timNow.getJmlPemain() < 1) {
            System.out.println("Belum ada pemain di tim ini yang terdaftar");
            pausing();
            return;
        }
        int pil;
        boolean ulang;
        do {
            System.out.print("Pilih Kapten Tim Baru (input indeks pemain)   : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > timNow.getJmlPemain())) {
                    System.out.println("Input pilihan harus sesuai list pemain");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        timNow.setKapten(timNow.getPemain(pil-1));
	}

	//fungsi untuk mendaftarkan pemain baru ke suatu tim
	public static void tambahPemain(tim timNow) {
        int pil;
        boolean ulang;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Tambah Pemain");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        System.out.print("Nomor Pemain      : ");
        String nmrPemain = bacaString();
        System.out.print("Nama Pemain       : ");
        String namaPemain = bacaString();
        System.out.print("NIM               : ");
        String nimPemain = bacaString();
        System.out.print("Nomor HP          : ");
        String nmrHP = bacaString();
        pemain tmp = new pemain(namaPemain, nmrHP, nimPemain, nmrPemain);
        timNow.addPemainTim(tmp);
        System.out.println("");
        System.out.println("Pemain berhasil didaftarkan!");
        System.out.println("");
	}

	//fungsi untuk mengubah data pemain - part 1
	public static void ubahDataPemainLagi(pemain pemainNow, int modez) {
        do {
        int pil;
        boolean ulang;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Tambah Pemain");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        System.out.println("Nomor Punggung      : " + pemainNow.getnmrpemain());
        System.out.println("Nama Pemain         : " + pemainNow.getnama());
        System.out.println("NIM                 : " + pemainNow.getnim());
        System.out.println("Nomor HP            : " + pemainNow.getnmrhp());
        System.out.println("Goal                : " + pemainNow.getjumgol());
        System.out.println("Kartu Kuning        : " + pemainNow.getjumkuning());
        System.out.println("Kartu Merah         : " + pemainNow.getjummerah());
        System.out.println("");
        if (modez == 1) {
        System.out.println("1. Ubah Nomor Punggung");
        System.out.println("2. Ubah Nama pemain");
        System.out.println("3. Ubah NIM");
        System.out.println("4. Ubah Nomor HP");
        }
        System.out.println("0. Kembali ke Pengaturan Tim");
        System.out.println("");
        System.out.println("");
        do {
            System.out.print("Pilihan       : ");
            try {
                pil = bacaInteger();
                if ((pil >= 0) && (pil <= 5))
                    ulang = false;
                else {
                    System.out.println("Input pilihan harus sesuai pilihan menu");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        if ((pil == 1) && (modez == 1))
            pemainNow.setnmrpemain(ubahDataString("Nomor Pemain Baru    : "));
        else if ((pil == 2) && (modez == 1))
            pemainNow.setnama(ubahDataString("Nama Pemain Baru     : "));
        else if ((pil == 3) && (modez == 1))
            pemainNow.setnim(ubahDataString("NIM Pemain Baru      : "));
        else if ((pil == 4) && (modez == 1))
            pemainNow.setnmr(ubahDataString("Nomor HP Pemain Baru : "));
        else if (pil == 0)
            return;
        } while(true);
	}

	//fungsi untuk mengubah data pemain - part 2
	public static void ubahDataPemain(tim timNow, int modez) {
        if (timNow.getJmlPemain() < 1) {
            System.out.println("Belum ada pemain di tim ini yang terdaftar");
            pausing();
            return;
        }
        int pil;
        boolean ulang;
        do {
            System.out.print("Pilih Data Pemain yang ingin diubah (input indeks pemain)   : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > timNow.getJmlPemain())) {
                    System.out.println("Input pilihan harus sesuai list pemain");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        ubahDataPemainLagi(timNow.getPemain(pil-1), 1);
	}

	//fungsi untuk men-delete suatu pemain pada suatu tim
	public static void deletePemainTim(tim timNow) {
        if (timNow.getJmlPemain() < 1) {
            System.out.println("Belum ada pemain di tim ini yang terdaftar");
            pausing();
            return;
        }
        int pil;
        boolean ulang;
        do {
            System.out.print("Pilih pemain yang dihapus (input indeks pemain)   : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > timNow.getJmlPemain())) {
                    System.out.println("Input pilihan harus sesuai list pemain");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        timNow.deletePemainTim(pil-1);
	}

	//fungsi yang mengeluarkan menu pengaturan tim - part 1
	public static void aturTimSpesifik(int ind) {
        do {
        int pil;
        boolean ulang;
        tim timNow = curTourney.getTim(ind-1);
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Pengaturan Tim");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        System.out.println("Nama Tim        : ");
        System.out.println(timNow.getNamaTim());
        System.out.println("");
        System.out.println("Deskripsi Tim   : ");
        System.out.println(timNow.getDeskripsi());
        System.out.println("");
        System.out.println("Kapten Tim      : ");
        if (timNow.getAdaKapten()) {
            System.out.println(timNow.getKapten().getnama());
        }
        else System.out.println("Belum ada Kapten");
        System.out.println("");
        System.out.println("Jumlah Menang   : " + timNow.getMenang());
        System.out.println("Jumlah Kalah    : " + timNow.getKalah());
        System.out.println("Jumlah Seri     : " + timNow.getSeri());
        System.out.println("Jumlah Goal     : " + timNow.getGoal());
        System.out.println("Jumlah Kebobolan: " + timNow.getKebobolan());
        System.out.println("");
        System.out.println("| No. | Nama Pemain                   | NP | KK | MK |");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < timNow.getJmlPemain(); i++) {
            System.out.print("| ");
            if (i < 9)
                System.out.print("0");
            String nama = timNow.getPemain(i).getnama();
            System.out.print((i+1) + ". | " + nama);
            for (int j = 0; j < (30 - nama.length()); j++)
                System.out.print(" ");
            System.out.print("| " + timNow.getPemain(i).getnmrpemain());
            System.out.print(" | " + timNow.getPemain(i).getjumkuning());
            System.out.println(" | " + timNow.getPemain(i).getjummerah() + " |");
        }
        System.out.println("");
        System.out.println("1. Ubah Nama Tim");
        System.out.println("2. Ubah Deskripsi Tim");
        System.out.println("3. Ubah Kapten Tim");
        System.out.println("4. Tambah Pemain");
        System.out.println("5. Lihat Data Pemain");
        System.out.println("6. Hapus Pemain");
        System.out.println("0. Kembali ke Menu Tim");
        System.out.println("");
        System.out.println("");
        do {
            System.out.print("Pilihan       : ");
            try {
                pil = bacaInteger();
                if ((pil >= 0) && (pil <= 6))
                    ulang = false;
                else {
                    System.out.println("Input pilihan harus sesuai pilihan menu");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        if (pil == 1) {
            timNow.setNamaTim(ubahDataString("Nama Baru Tim         : "));
        }
        else if (pil == 2) {
            timNow.setDeskripsi(ubahDataString("Deskripsi Baru Tim    : "));
        }
        else if (pil == 3) {
            ubahKaptenTim(timNow);
        }
        else if (pil == 4) {
            tambahPemain(timNow);
        }
        else if (pil == 5) {
            ubahDataPemain(timNow, 1);
        }
        else if (pil == 6) {
            deletePemainTim(timNow);
        }
        else if (pil == 0)
            return;
        } while (true);
	}

	//fungsi yang mengeluarkan menu pengaturan tim - part 2
	public static void pengaturanTim() {
        if (curTourney.getJumlahTim() < 1) {
            System.out.println("Belum ada tim yang terdaftar");
            pausing();
            return;
        }
        int pil;
        boolean ulang;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Pengaturan Tim");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        for (int i = 0; i < curTourney.getListTim().size() ; i++)
            System.out.println((i+1) + ". " + curTourney.getListTim().get(i).getNamaTim());
        System.out.println("");
        do {
            System.out.print("Pilih tim yang ingin diatur   : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > curTourney.getListTim().size())) {
                    System.out.println("Input pilihan harus sesuai list tim");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        aturTimSpesifik(pil);
	}

	public static void hapusTim() {
        if (curTourney.getJumlahTim() < 1) {
            System.out.println("Belum ada tim yang terdaftar");
            pausing();
            return;
        }
        int pil;
        boolean ulang;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Hapus Tim");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        for (int i = 0; i < curTourney.getListTim().size() ; i++)
            System.out.println((i+1) + ". " + curTourney.getListTim().get(i).getNamaTim());
        System.out.println("");
        do {
            System.out.print("Hapus Tim ke (input nomor tim)  : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > curTourney.getListTim().size())) {
                    System.out.println("Input pilihan harus sesuai list tim");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        String tmp = "Apakah yakin menghapus Tim " + curTourney.getListTim().get(pil-1).getNamaTim() + " (y/n)? ";
        if (ynquestion(tmp)) {
            curTourney.deleteTim(pil-1);
            System.out.println("");
            System.out.println("Tim berhasil dihapus!");
            System.out.println("");
        }
	}

    public static void liatDetailTim() {
        if (curTourney.getJumlahTim() < 1) {
            System.out.println("Belum ada tim yang terdaftar");
            pausing();
            return;
        }
        int pil;
        boolean ulang;
        do {
            System.out.print("Pilih tim (input indeks tim)      : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > curTourney.getJumlahTim())) {
                    System.out.println("Input pilihan harus sesuai list tim");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        tim timNow = curTourney.getTim(pil-1);
        do {
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Detail Tim");
        System.out.println("");
        System.out.println("				>> " + curTourney.getNama() + " <<");
        System.out.println("");
        System.out.println("Nama Tim        : ");
        System.out.println(timNow.getNamaTim());
        System.out.println("");
        System.out.println("Deskripsi Tim   : ");
        System.out.println(timNow.getDeskripsi());
        System.out.println("");
        System.out.println("Kapten Tim      : ");
        if (timNow.getAdaKapten()) {
            System.out.println(timNow.getKapten().getnama());
        }
        else System.out.println("Belum ada Kapten");
        System.out.println("");
        System.out.println("Jumlah Menang   : " + timNow.getMenang());
        System.out.println("Jumlah Kalah    : " + timNow.getKalah());
        System.out.println("Jumlah Seri     : " + timNow.getSeri());
        System.out.println("Jumlah Goal     : " + timNow.getGoal());
        System.out.println("Jumlah Kebobolan: " + timNow.getKebobolan());
        System.out.println("");
        System.out.println("| No. | Nama Pemain                   | NP | KK | MK |");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < timNow.getJmlPemain(); i++) {
            System.out.print("| ");
            if (i < 9)
                System.out.print("0");
            String nama = timNow.getPemain(i).getnama();
            System.out.print((i+1) + ". | " + nama);
            for (int j = 0; j < (30 - nama.length()); j++)
                System.out.print(" ");
            System.out.print("| " + timNow.getPemain(i).getnmrpemain());
            System.out.print(" | " + timNow.getPemain(i).getjumkuning());
            System.out.println(" | " + timNow.getPemain(i).getjummerah() + " |");
        }
        System.out.println("");
        System.out.println("1. Lihat Data Pemain");
        System.out.println("0. Kembali ke Menu Tim");
        do {
            System.out.print("Pilihan       : ");
            try {
                pil = bacaInteger();
                if ((pil >= 0) && (pil <= 1))
                    ulang = false;
                else {
                    System.out.println("Input pilihan harus sesuai pilihan menu");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        if (pil == 1)
            ubahDataPemain(timNow, 0);
        else if (pil == 0)
            return;
        } while (true);
    }

	public static void menuTim() {
        int pil;
        boolean ulang;
        do {
            do {
                System.out.println("=================Futsal Manajemen=================");
                System.out.println("=== Menu Tim");
                System.out.println("");
                System.out.println("				>> " + curTourney.getNama() + " <<");
                System.out.println("");
                System.out.println("Daftar Tim");
                System.out.println("----------");
                for (int i = 0; i < curTourney.getListTim().size() ; i++)
                    System.out.println((i+1) + ". " + curTourney.getListTim().get(i).getNamaTim());
                System.out.println("");
                System.out.println("1. Liat Detail Tim");
                if (curTourney.getRegisterMode())
                    System.out.println("2. Tambah Tim");
                if (curTourney.getRegisterMode())
                    System.out.println("3. Pengaturan Tim");
                if (curTourney.getRegisterMode())
                    System.out.println("4. Hapus Tim");
                System.out.println("0. Kembali ke Menu Turnamen");
                System.out.println("");
                System.out.println("");
                System.out.print("pilihan : ");
                try {
                    pil = bacaInteger();
                    if (pil == 1 || pil == 2 || pil == 3 || pil == 4 || pil == 0) {
                        ulang = false;
                    }
                    else {
                        System.out.println("Input pilihan harus 1 atau 0");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    pil = 0;
                    ulang = true;
                }
            } while(ulang);
            if (pil == 1)
                liatDetailTim();
            else if (pil == 2)
                tambahTim();
            else if (pil == 3)
                pengaturanTim();
            else if (pil == 4)
                hapusTim();
            else if (pil == 0)
                return;
        } while (true);
	}

    public static String inputTanggal() {
        int tgl;
        int bulan;
        boolean ulang;
        do {
            ulang = false;
            System.out.print("Masukkan Tanggal (1-31)   : ");
            try {
                tgl = bacaInteger();
                if ((tgl < 1) || (tgl > 31))
                    System.out.println("Tanggal tidak valid");
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                tgl = 0;
                ulang = true;
            }
        } while(ulang);
        do {
            ulang = false;
            System.out.print("Masukkan Bulan (1-12)     : ");
            try {
                bulan = bacaInteger();
                if ((bulan < 1) || (bulan > 12))
                    System.out.println("Bulan tidak valid");
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                bulan = 0;
                ulang = true;
            }
        } while(ulang);
        String kembali = tgl + "-" + bulan;
        return kembali;
    }

    public static int pilihPertandingan() {
        int pil;
        boolean ulang;
        do {
            System.out.print("Pilih Pertandingan yang ingin dilaporkan (input indeks Pertandingan)   : ");
            try {
                pil = bacaInteger();
                ulang = false;
                if ((pil < 1) || (pil > curTourney.getListPertandingan().size())) {
                    System.out.println("Input pilihan harus sesuai list pertandingan");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                pil = 0;
                ulang = true;
            }
        } while(ulang);
        return pil;
    }

    public static void laporPertandingan() {
        if (curTourney.getListPertandingan().size() < 1) {
            System.out.println("Tidak ada pertandingan yang perlu dilaporkan");
            pausing();
            return;
        }
        int ind = pilihPertandingan()-1;
        Pertandingan tmp = curTourney.getListPertandingan().get(ind);
        int skorTim1;
        int skorTim2;
        boolean ulang;
        do {
        do {
            ulang = false;
            System.out.print("Skor " + tmp.getTim1().getNamaTim() + " : ");
            try {
                skorTim1 = bacaInteger();
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                skorTim1 = 0;
                ulang = true;
            }
        } while(ulang);
        do {
            ulang = false;
            System.out.print("Skor " + tmp.getTim2().getNamaTim() + " : ");
            try {
                skorTim2 = bacaInteger();
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                skorTim2 = 0;
                ulang = true;
            }
        } while(ulang);
        if (skorTim1 == skorTim2) {
            ulang = true;
            System.out.println("Skor antar kedua tim tidak boleh seri");
        }
        } while(ulang);
        tmp.setSkor_tim1(skorTim1);
        tmp.setSkor_tim2(skorTim2);
        System.out.println("Daftar Pemain Tim " + tmp.getTim1().getNamaTim());
        System.out.println("| No. | Nama Pemain                   | NP | KK | MK |");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < tmp.getTim1().getJmlPemain(); i++) {
            System.out.print("| ");
            if (i < 9)
                System.out.print("0");
            String nama = tmp.getTim1().getPemain(i).getnama();
            System.out.print((i+1) + ". | " + nama);
            for (int j = 0; j < (30 - nama.length()); j++)
                System.out.print(" ");
            System.out.print("| " + tmp.getTim1().getPemain(i).getnmrpemain());
            System.out.print(" | " + tmp.getTim1().getPemain(i).getjumkuning());
            System.out.println(" | " + tmp.getTim1().getPemain(i).getjummerah() + " |");
        }
        System.out.println("");
        System.out.println("Masukkan data penggol Tim 1");
        for (int i = 0; i < skorTim1; i++) {
            int inputGoaler;
            do {
                ulang = false;
                System.out.print("Goal " + (i+1) + " : ");
                try {
                    inputGoaler = bacaInteger();
                    if ((inputGoaler < 1) || (inputGoaler > tmp.getTim1().getJmlPemain())) {
                        System.out.println("Input harus sesuai dengan list pemain");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    inputGoaler = 0;
                    ulang = true;
                }
            } while (ulang);
            tmp.addPenggolTim1(tmp.getTim1().getPemain(inputGoaler-1));
        }
        System.out.println("Daftar Pemain Tim " + tmp.getTim2().getNamaTim());
        System.out.println("| No. | Nama Pemain                   | NP | KK | MK |");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < tmp.getTim2().getJmlPemain(); i++) {
            System.out.print("| ");
            if (i < 9)
                System.out.print("0");
            String nama = tmp.getTim2().getPemain(i).getnama();
            System.out.print((i+1) + ". | " + nama);
            for (int j = 0; j < (30 - nama.length()); j++)
                System.out.print(" ");
            System.out.print("| " + tmp.getTim2().getPemain(i).getnmrpemain());
            System.out.print(" | " + tmp.getTim2().getPemain(i).getjumkuning());
            System.out.println(" | " + tmp.getTim2().getPemain(i).getjummerah() + " |");
        }
        System.out.println("");
        System.out.println("Masukkan data penggol Tim 2");
        for (int i = 0; i < skorTim2; i++) {
            int inputGoaler;
            do {
                ulang = false;
                System.out.print("Goal " + (i+1) + " : ");
                try {
                    inputGoaler = bacaInteger();
                    if ((inputGoaler < 1) || (inputGoaler > tmp.getTim2().getJmlPemain())) {
                        System.out.println("Input harus sesuai dengan list pemain");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    inputGoaler = 0;
                    ulang = true;
                }
            } while (ulang);
            tmp.addPenggolTim2(tmp.getTim2().getPemain(inputGoaler-1));
        }
        if ((curTourney.getMaxRound() - curTourney.getRound()) == 0) {
            if (skorTim1 > skorTim2)
                curTourney.setPemenang(tmp.getTim1());
            else
                curTourney.setPemenang(tmp.getTim2());
        }
        if(skorTim2 > skorTim1) {
            int ind1 = -1;
            int ind2 = -1;
            for(int i = 0; i < curTourney.getListNmrTim().size(); i++) {
                if (curTourney.getListNmrTim().get(i).intValue() == tmp.getTim1().getNmrTim())
                    ind1 = i;
                else if (curTourney.getListNmrTim().get(i).intValue() == tmp.getTim2().getNmrTim())
                    ind2 = i;
            }
            int tmp2 = curTourney.getListNmrTim().get(ind1).intValue();
            int tmp3 = curTourney.getListNmrTim().get(ind2).intValue();
            curTourney.getListNmrTim().remove(ind1);
            curTourney.getListNmrTim().add(ind1, new Integer(tmp3));
            curTourney.getListNmrTim().remove(ind2);
            curTourney.getListNmrTim().add(ind2, new Integer(tmp2));
        }
        tmp.wrapping_up();
        curTourney.getListPastPertandingan().get(curTourney.getRound()-1).add(curTourney.getListPertandingan().get(ind));
        curTourney.getListPertandingan().remove(ind);
    }

    public static void liatPast() {
        System.out.println("");
        System.out.println("---------------");
        for (int i = 1; i <= curTourney.getListPastPertandingan().size(); i++) {
            System.out.print("RONDE : ");
            if (i == curTourney.getMaxRound())
                System.out.println("FINAL");
            else if (i == (curTourney.getMaxRound()-1))
                System.out.println("SEMIFINAL");
            else
                System.out.println(curTourney.getRound());
            System.out.println("---------------");
            for (int j = 0; j < curTourney.getListPastPertandingan().get(i-1).size(); j++) {
                Pertandingan tmp = curTourney.getListPastPertandingan().get(i-1).get(j);
                System.out.print(tmp.getTim1().getNamaTim());
                System.out.print(" " + tmp.getSkor_tim1() + " vs " + tmp.getSkor_tim2() + " ");
                System.out.println(tmp.getTim2().getNamaTim());
            }
        }
        System.out.println("");
        pausing();
    }

	public static void menuPertandingan() {
        int pil;
        boolean ulang;
        do {
            do {
                System.out.println("=================Futsal Manajemen=================");
                System.out.println("=== Menu Pertandingan");
                System.out.println("");
                System.out.println("				>> " + curTourney.getNama() + " <<");
                System.out.println("");
                if (curTourney.getPemenang().getNamaTim().equals("belum ada pemenang")) {
                    System.out.print("RONDE : ");
                    int bedaRound = curTourney.getMaxRound() - curTourney.getRound();
                    if (bedaRound == 0)
                        System.out.println("FINAL");
                    else if (bedaRound == 1)
                        System.out.println("SEMIFINAL");
                    else
                        System.out.println(curTourney.getRound());
                    System.out.println("");
                    System.out.println("Daftar Pertandingan");
                    System.out.println("-------------------");
                    for (int i = 0; i < curTourney.getListPertandingan().size() ; i++) {
                        Pertandingan tmp = curTourney.getListPertandingan().get(i);
                        System.out.println((i+1) + ". " + tmp.getTim1().getNamaTim() + " vs " + tmp.getTim2().getNamaTim());
                        System.out.println("Jadwal Pertandingan : " + tmp.getJadwal());
                        System.out.println("Lapangan            : " + tmp.getLapangan());
                    }
                }
                else
                    System.out.println("Pemenang Turnamen   : " + curTourney.getPemenang().getNamaTim());
                System.out.println("");
                if (curTourney.getPemenang().getNamaTim().equals("belum ada pemenang")) {
                    System.out.println("1. Buat Pertandingan");
                    System.out.println("2. Ubah Jadwal Pertandingan");
                    System.out.println("3. Ubah Lapangan Pertandingan");
                    System.out.println("4. Lapor Pertandingan");
                }
                System.out.println("5. Lihat Pertandingan Sebelumnya");
                System.out.println("0. Kembali ke Menu Turnamen");
                System.out.println("");
                System.out.println("");
                System.out.print("pilihan : ");
                try {
                    pil = bacaInteger();
                    if ((pil >= 0) && (pil <= 5)) {
                        ulang = false;
                    }
                    else {
                        System.out.println("Input pilihan harus sesuai nomor pilihan");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    pil = 0;
                    ulang = true;
                }
            } while(ulang);
            if (pil == 1) {
                if (curTourney.getListPertandingan().size() > 0) {
                    System.out.println("Pertandingan pada ronde ini belum selesai!");
                    System.out.println("");
                }
                else {
                    curTourney.buatPertandingan();
                    ArrayList<Pertandingan> waw = new ArrayList<Pertandingan>();
                    curTourney.getListPastPertandingan().add(waw);
                    System.out.println("Pertandingan berhasil dibuat!");
                    System.out.println("");
                }
            }
            else if (pil == 2) {
                Pertandingan tmp = curTourney.getListPertandingan().get(pilihPertandingan()-1);
                tmp.setJadwal(inputTanggal());
            }
            else if (pil == 3) {
                Pertandingan tmp = curTourney.getListPertandingan().get(pilihPertandingan()-1);
                tmp.setLapangan(ubahDataString("Lapangan baru : "));
            }
            else if (pil == 4) {
                laporPertandingan();
            }
            else if (pil == 5) {
                liatPast();
            }
            else if (pil == 0)
                return;
        } while (true);
	}

	public static boolean checkRegistrationValid() {
        boolean valid = true;
        if (curTourney.getJumlahTim() < 2) {
            System.out.println("Jumlah Tim harus lebih dari satu.");
            System.out.println("");
            valid = false;
        }
        ArrayList<String> timKurang = new ArrayList<String>();
        ArrayList<String> timLebih = new ArrayList<String>();
        for (int i = 0; i < curTourney.getJumlahTim(); i++) {
            if (curTourney.getTim(i).getJmlPemain() < curTourney.getMinPemain())
                timKurang.add(curTourney.getTim(i).getNamaTim());
            else if (curTourney.getTim(i).getJmlPemain() > curTourney.getMaxPemain())
                timLebih.add(curTourney.getTim(i).getNamaTim());
        }
        if (timKurang.size() > 0) {
            System.out.println("Berikut ini tim dengan anggota kurang dari batas minimal");
            for (int i = 0; i < timKurang.size(); i++)
                System.out.println((i+1) + ". " + timKurang.get(i));
            System.out.println("");
            valid = false;
        }
        if (timLebih.size() > 0) {
            System.out.println("Berikut ini tim dengan anggota lebih dari batas maksimal");
            for (int i = 0; i < timLebih.size(); i++)
                System.out.println((i+1) + ". " + timLebih.get(i));
            System.out.println("");
            valid = false;
        }
        if (!valid) {
            System.out.println("Karena syarat tim masih ada yang belum dipenuhi,\nTurnamen masih belum bisa dimulai.");
            pausing();
            return false;
        }
        else
            return true;
	}

	public static void lihatTopSkor() {
        int highest = 0;
        for (int i = 0; i < curTourney.getJumlahTim(); i++) {
            for (int j = 0; j < curTourney.getListTim().get(i).getJmlPemain(); j++) {
                if (curTourney.getListTim().get(i).getPemain(j).getjumgol() > highest)
                    highest = curTourney.getListTim().get(i).getPemain(j).getjumgol();
            }
        }
        for (int k = highest; k > 0; k--) {
            boolean exist = false;
            for (int i = 0; i < curTourney.getJumlahTim(); i++) {
                for (int j = 0; j < curTourney.getListTim().get(i).getJmlPemain(); j++) {
                    if (curTourney.getListTim().get(i).getPemain(j).getjumgol() == k) {
                        if (!exist) {
                            System.out.println(k + " GOL");
                            System.out.println("-------");
                        }
                        if (exist) {
                            System.out.print(", ");
                        }
                        if (!exist) {
                            exist = true;
                        }
                        System.out.print(curTourney.getListTim().get(i).getPemain(j).getnama());
                        System.out.print("(" + curTourney.getListTim().get(i).getNamaTim() + ")");
                    }
                }
            }
            System.out.println("");
        }
        pausing();
	}

	public static void menuTurnamen() {
        int pil;
        boolean ulang;
        do {
            do {
                System.out.println("=================Futsal Manajemen=================");
                System.out.println("=== Menu Turnamen");
                System.out.println("");
                System.out.println("				>> " + curTourney.getNama() + " <<");
                System.out.println("");
                System.out.println("Nama Turnamen       : " + curTourney.getNama());
                System.out.println("Deskripsi Turnamen  : " + curTourney.getDeskripsi());
                System.out.println("Max Pemain/Tim      : " + curTourney.getMaxPemain());
                System.out.println("Min Pemain/Tim      : " + curTourney.getMinPemain());
                if (curTourney.getRegisterMode())
                    System.out.println("Ronde               : Belum Dimulai");
                else if (curTourney.getPemenang().getNamaTim().equals("belum ada pemenang"))
                    System.out.println("Ronde               : " + curTourney.getRound());
                else
                    System.out.println("Pemenang Turnamen   : " + curTourney.getPemenang().getNamaTim());
                System.out.println("");
                System.out.println("1. Daftar Tim");
                if (curTourney.getRegisterMode())
                    System.out.println("2. Mulai Turnamen");
                else
                    System.out.println("2. Daftar Pertandingan");
                System.out.println("3. Lihat Top Skor");
                System.out.println("9. Hapus Turnamen");
                System.out.println("0. Kembali ke Menu Utama");
                System.out.println("");
                System.out.println("");
                System.out.print("pilihan : ");
                try {
                    pil = bacaInteger();
                    if (pil == 1 || pil == 2 || pil == 3 || pil == 9 || pil == 0) {
                        ulang = false;
                    }
                    else {
                        System.out.println("Input pilihan harus 1 atau 2 atau 0");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    pil = 0;
                    ulang = true;
                }
            } while(ulang);
            if (pil == 1) {
                menuTim();
            }
            else if (pil == 2) {
                if (curTourney.getRegisterMode())
                    if (checkRegistrationValid()) {
                        System.out.println("Registrasi valid.");
                        if (ynquestion("Apakah anda yakin mau mengakhiri registrasi (y/n)? ")) {
                            curTourney.shuffleTim(randomGenerator);
                            curTourney.calculatePostRegistration();
                        }
                    }
                    else;
                else
                    menuPertandingan();
            }
            else if (pil == 3) {
                lihatTopSkor();
            }
            else if (pil == 9) {
                if(hapusTurnamen())
                    return;
            }
            else if (pil == 0)
                return;
        } while(true);
	}

	public static void buatTurnamen() {
        String nama;
        String deskripsi;
        int maxPemain;
        int minPemain;
        boolean ulang;
        System.out.println("=================Futsal Manajemen=================");
        System.out.println("=== Buat Turnamen");
        System.out.println("");
        System.out.print("Nama Turnamen       : ");
        nama = bacaString();
        System.out.print("Deskripsi turnamen  : ");
        deskripsi = bacaString();
        do {
            ulang = false;
            System.out.print("Max Pemain/Tim      : ");
            try {
                maxPemain = bacaInteger();
                if (maxPemain < 0) {
                    System.out.println("Input bilangan tidak boleh negatif");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                maxPemain = 0;
                ulang = true;
            }
        } while (ulang);
        do {
            ulang = false;
            System.out.print("Min Pemain/Tim      : ");
            try {
                minPemain = bacaInteger();
                if (minPemain < 0) {
                    System.out.println("Input bilangan tidak boleh negatif");
                    ulang = true;
                }
                else if (minPemain > maxPemain) {
                    System.out.println("Minimum Pemain tidak boleh lebih dari Maksimum Pemain");
                    ulang = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input pilihan harus angka");
                minPemain = 0;
                ulang = true;
            }
        } while (ulang);
        curTourney = new turnamen(nama, deskripsi, maxPemain, minPemain);
        curTourney.setIsActive(true);
        System.out.println("");
        System.out.println("Turnamen berhasil dibuat!");
        System.out.println("");
	}

	public static void liatTurnamenDahoeloe() {
        int pil;
        boolean ulang;
        do {
            do {
                System.out.println("=================Futsal Manajemen=================");
                System.out.println("=== Turnamen Lama");
                System.out.println("");
                for (int i = 0; i < pastTourney.size(); i++)
                    System.out.println((i+1) + ". " + pastTourney.get(i).getNama());
                System.out.println("");
                System.out.println("0. Kembali ke Menu Utama");
                System.out.println("");
                System.out.print("pilihan : ");
                try {
                    pil = bacaInteger();
                    if (pil == 0) {
                        ulang = false;
                    }
                    else {
                        System.out.println("Input pilihan harus 0");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    pil = 0;
                    ulang = true;
                }
            } while(ulang);
            if (pil == 0)
                return;
        } while(true);
	}

    public static void mainMenu() {
        int pil;
        boolean ulang;
        do {
            do {
                System.out.println("=================Futsal Manajemen=================");
                System.out.println("=== Menu Utama");
                System.out.println("");
                if (!curTourney.getIsActive())
                    System.out.println("Tidak ada turnamen yang berlangsung.");
                else {
                    System.out.println("Nama Turnamen       : " + curTourney.getNama());
                    System.out.println("Deskripsi Turnamen  : " + curTourney.getDeskripsi());
                    System.out.println("Max Pemain/Tim      : " + curTourney.getMaxPemain());
                    System.out.println("Min Pemain/Tim      : " + curTourney.getMinPemain());
                    if (curTourney.getRegisterMode())
                        System.out.println("Ronde               : Belum Dimulai");
                    else if (curTourney.getPemenang().getNamaTim().equals("belum ada pemenang"))
                        System.out.println("Ronde               : " + curTourney.getRound());
                    else
                        System.out.println("Pemenang Turnamen   : " + curTourney.getPemenang().getNamaTim());
                }
                System.out.println("");
                if (!curTourney.getIsActive())
                    System.out.println("1. Buat Turnamen");
                else
                    System.out.println("1. Masuk Menu Turnamen");
                System.out.println("2. Lihat Turnamen-Turnamen Lama");
                System.out.println("0. Keluar");
                System.out.println("");
                System.out.println("");
                System.out.print("pilihan : ");
                try {
                    pil = bacaInteger();
                    if (pil == 1 || pil == 2 || pil == 0) {
                        ulang = false;
                    }
                    else {
                        System.out.println("Input pilihan harus 1 atau 2 atau 0");
                        ulang = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input pilihan harus angka");
                    pil = 0;
                    ulang = true;
                }
            } while(ulang);
            if (pil == 1)
                if (!curTourney.getIsActive())
                    buatTurnamen();
                else
                    menuTurnamen();
            else if (pil == 2)
                liatTurnamenDahoeloe();
            else if (pil == 0)
                return;
        } while(true);
    }

    public static void saving() {
        try {
            FileOutputStream f_out = new FileOutputStream("tournamentSave.data");
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
            obj_out.writeObject(curTourney);
            obj_out.flush();
            obj_out.close();
            f_out = new FileOutputStream("tournamentPastSave.data");
            obj_out = new ObjectOutputStream (f_out);
            for (int i = 0; i < pastTourney.size(); i++)
                obj_out.writeObject(pastTourney.get(i));
            turnamen eofMark = new turnamen("eof", "buat mark", -981, -981);
            obj_out.writeObject(eofMark);
            obj_out.flush();
            obj_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loading() {
        try {
            FileInputStream f_in = new FileInputStream("tournamentSave.data");
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            curTourney = (turnamen) obj_in.readObject();
            f_in = new FileInputStream("tournamentPastSave.data");
            obj_in = new ObjectInputStream(f_in);
            turnamen tmp = (turnamen) obj_in.readObject();
            while(tmp.getMaxPemain() != -981) {
                pastTourney.add(tmp);
                tmp = (turnamen) obj_in.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void forDebug() {
        curTourney.setRound(0);
        curTourney.refereshNomorTim();
        curTourney.getListPertandingan().clear();
    }

	//main
	public static void main(String args[])throws IOException{
        inisialisasi();
        loading();
        //forDebug();
        mainMenu();
        saving();
	}
}
