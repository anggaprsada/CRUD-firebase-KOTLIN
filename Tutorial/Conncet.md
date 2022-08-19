# Connect ke Firebase
Aplikasi yang kita buat harus kita koneksikan ke firebase dengan cara sebagai berikut:

## Menambahkan Firebase mengunakan Firebase Concole
Penambahan Firebase ke aplikasi memerlukan tindakan baik di [Firebase console](https://console.firebase.google.com/u/0/) maupun di project Android yang terbuka (misalnya, Anda mendownload file konfigurasi Firebase dari console, lalu memindahkannya ke project Android).
*Buat Project Firebase<br/>
Agar dapat menambahkan Firebase ke aplikasi Android, Anda perlu membuat project Firebase untuk dihubungkan ke aplikasi Android.
* Daftarkan aplikasi ke Firebase<b/>
  + Buka [Firebase console](https://console.firebase.google.com/u/0/).
  + Di bagian tengah halaman ringkasan project, klik ikon Android (plat_android) atau Add app untuk meluncurkan alur kerja penyiapan.
  + Masukkan nama paket aplikasi Anda di kolom Android package name.<br/>
     Pastikan untuk memasukkan nama paket yang benar-benar digunakan aplikasi Anda. Nilai nama paket peka huruf besar/kecil dan tidak dapat diubah untuk aplikasi Android Firebase ini setelah didaftarkan ke project Firebase Anda.
  + Klik Register app.<b/>
* Tambahkan file konfigurasi Firebase<br/>
  + Tambahkan file konfigurasi Andoid ke aplikasi
    - Klik Download google-services.json untuk mendapatkan file konfigurasi Android Firebase Anda (google-services.json).
    - Pindahkan file konfigurasi ke direktori modul (level aplikasi) aplikasi Anda.
    - ![resources/logo-artivisi.png](https://www.gstatic.com/mobilesdk/160426_mobilesdk/images/android_studio_project_panel@2x.png))
  + Untuk mengaktifkan produk Firebase di aplikasi, tambahkan [plugin google-services](https://developers.google.com/android/guides/google-services-plugin) ke file Gradle.
* Tambahkan Firebase SDK ke aplikasi
