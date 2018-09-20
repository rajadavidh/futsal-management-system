# Futsal Management System

![logo](https://github.com/rajadavidh/futsal-management-system/blob/master/img/logo.png)

This documentation created in bilingual: English and Bahasa Indonesia.

## EN lang
### Program description
Java based desktop application for managing futsal tournaments.

### Features
List of features in this system:
1. Create new tournaments
1. Create tournament schedules
1. View top scorer
1. View tournament status
1. Save tournament information

### Class diagram
![class diagram](https://github.com/rajadavidh/futsal-management-system/blob/master/img/UMLFutsalManagementSystem.png)

### User manual
#### Main menu
![main menu](https://github.com/rajadavidh/futsal-management-system/blob/master/img/mainMenu.png)

Above is the menus when no tournament created. User is limited to create a tournament at once.

##### 1. Create tournament
This is a menu to create a tournament

![create tournament](https://github.com/rajadavidh/futsal-management-system/blob/master/img/createTournament.png)

##### 2. View tournament
User can view created tournament

![view tournament](https://github.com/rajadavidh/futsal-management-system/blob/master/img/viewTournament.png)

Available options :

* Manage tournament
* View previous tournamens

#### Manage tournament
![manage tournament](https://github.com/rajadavidh/futsal-management-system/blob/master/img/manageTournament.png)

Available options:

* Manage team

  ![manage team](https://github.com/rajadavidh/futsal-management-system/blob/master/img/manageTeam.png)

* Manage player

  ![manage player](https://github.com/rajadavidh/futsal-management-system/blob/master/img/managePlayer.png)

* Manage schedule

  ![manage schedule](https://github.com/rajadavidh/futsal-management-system/blob/master/img/manageSchedule.png)

  To start a tournament, at least there are 2 existing teams created.

  The number of players on each team should not exceed the maximum quota.

* View top scorer

  In this menu, user can view top scorer players


### Directory structure
```
/src  --> Contains driver class and other components
/img  --> Contains screenshots of the UI
```

### Download entire repository
Clone remote repository using command:
```
git clone https://github.com/rajadavidh/futsal-management-system.git
```

### Setup local repository and Push to remote repository
1. Create remote repository from github webpage
1. Create new local repository:
   ```
   git init
   git status
   git add -A
   ```
1. Commit changes on local repository:
   ```
   git commit -m "initial commit"
   ```
1. Add a remote repository:
   ```
   # Set a new remote
   git remote add origin https://github.com/rajadavidh/futsal-management-system.git
   # Verify new remote
   git remote -v
   ```
1. Upload local branch commits to remote repository:
   ```
   git push -u origin master
   ```

## ID lang
### Deskripsi program
Futsal Manajemen adalah aplikasi desktop berbasis Java untuk pengelolaan administrasi sebuah turnamen futsal.

Sasaran pengguna dari aplikasi ini adalah panitia penyelenggara turnamen futsal dengan lingkup batasan di lingkungan perkuliahan.

Tujuan pembuatan aplikasi ini adalah membantu panitia penyelenggara agar lebih mudah mencatat segala informasi yang berkaitan dengan turnamen.

### Fitur
Secara keseluruhan, fitur-fitur dari aplikasi ini adalah:

1. Membuat turnamen baru.

   Fitur ini terdiri dari:
   * mengelola data pemain,
   * mengelola data tim,
   * menentukan drawing knockout pertandingan secara otomatis.

1. Membuat penjadwalan.

   Dalam fitur ini admin bisa membuat plot-plot jadwal pertandingan kemudian akan di generate secara otomatis oleh sistem.

1. Ranking pencetak gol terbanyak

   Fitur ini dapat me-ranking pemain-pemain yang mengantongi jumlah gol terbanyak.

1. Berita acara tiap pertandingan

   Dalam tiap pertandingan admin bisa menggunakan fitur ini untuk mencatat:
   * hasil pertandingan, dan
   * pencetak gol

1. Penyimpanan data turnamen-turnamen sebelumnya

   Data dari turnamen-turnamen yang telah dilaksanakan akan disimpan oleh aplikasi dan dapat dilihat kembali kapanpun melalui fitur ini.

### Pengembangan
Aplikasi ini dapat dikembangkan lebih lanjut menggunakan desain interface yang baik dan menarik.

### Panduan pengguna
#### Menu utama
![menu utama](https://github.com/rajadavidh/futsal-management-system/blob/master/img/mainMenu.png)

Menu utama di atas ditampilkan jika tidak ada turnamen yang sedang berlangsung. User hanya bisa membuat satu turnamen dalam satu waktu.

##### 1. Buat turnamen
Menu untuk membuat Turnamen Baru:

![buat turnamen](https://github.com/rajadavidh/futsal-management-system/blob/master/img/createTournament.png)

##### 2. Lihat turnamen
Untuk melihat turnamen yang telah dilaksanakan sebelumnya:

![lihat turnamen](https://github.com/rajadavidh/futsal-management-system/blob/master/img/viewTournament.png)

Menu utama di atas ditampilkan jika ada turnamen yang sedang berlangsung.

Menu yang tersedia :

* Masuk Menu Turnamen

  Untuk mengelola turnamen yang sedang berlangsung dan aplikasi tidak menyediakan menu untuk membuat turnamen baru.

* Lihat Turnamen-turnamen Lama

  Untuk melihat turnamen yang telah dilaksanakan sebelumnya

#### Mengelola turnamen
![mengelola turnamen](https://github.com/rajadavidh/futsal-management-system/blob/master/img/manageTournament.png)

##### 1. Mengelola tim
`Menu utama >> pilihan: 1. (Daftar Tim)`

Menu tersebut digunakan untuk melihat daftar tim peserta, menambah, mengatur, serta menghapus Tim.

![mengelola tim](https://github.com/rajadavidh/futsal-management-system/blob/master/img/manageTeam.png)

##### 2. Mengelola pemain
`Menu Utama >> Pilihan: 3. (Menu Tim) >> Pilihan : 1. (Pengaturan Tim)`

![mengelola pemain](https://github.com/rajadavidh/futsal-management-system/blob/master/img/managePlayer.png)

##### 3. Mengelola jadwal pertandingan
`Menu Utama >> Daftar Pertandingan`

![mengelola jadwal](https://github.com/rajadavidh/futsal-management-system/blob/master/img/manageSchedule.png)

Untuk memulai turnamen, minimal harus ada 2 tim yang bergabung dalam sebuah turnamen.

Turnamen bisa dimulai jika minimal pemain per tim terpenuhi dan tidak melebihi kuota maksimal pemain per tim.

Menu yang tersedia :

* Buat pertandingan

  Digunakan untuk membuat dan men-generate jadwal pertandingan secara random

* Ubah jadwal pertandingan

  Mengubah jadwal pertandingan jika suda di-generate

* Ubah lapangan pertandingan

  Digunakan untuk mengubah lapangan pertandingan yang akan dipakai

* Lapor pertandingan

  Untuk mengisi berita acara setelah sebuah pertandingan selesai. User dapat mengisi skor dan pencetak goal, kartu.

* Lihat pertandingan sebelumnya

  Digunakan untuk melihat pertandingan-pertandingan yang telah dilaksanakan.


##### 4. Melihat rekap pencetak gol terbanyak

`Menu Utama >> Pilihan : 3. (Lihat Top Skor)`

Fitur ini digunakan untuk melihat dan mer-ranking pemain-pemain yang mencetak gol terbanyak.
