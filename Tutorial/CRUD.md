# CRUD
CRUD itu sendiri adalah Create, Read, Update, dan Delete. hal ini merujuk dalam pengolahan dari database tersebut.<br/>

Terlebih dahulu kalian pastikan database sudah terdaftar pada firebase:
- Buka [Konsol Firebase](https://console.firebase.google.com/u/0/), pilih project yang sudah terdaftar dengan aplikasi. Pilih Builid  > Realtime Database > Create Database

- Pada Database options pilih <code translate="no" dir="ltr">Singapore(asia-southeast1)</code> > Next

- Pada Security Rules pilih <code translate="no" dir="ltr">Start in locked mode</code> > Enable

- Pilih Rules, Edi rules menjadi :
```
{
  "rules": {
    ".read":true,
    ".write": true
  }
}
```
- Kemudian Publish

[Create](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/CRUD/Create.md)
[Read](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/CRUD/Read.md)
[Update](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/CRUD/Update.md)
[Delete](https://github.com/anggaprsada/crud-firebase-kotlin/blob/main/Tutorial/CRUD/Delete.md)
