# OpenLibrary App

Aplikasi Katalog dan Eksplorasi Buku dari OpenLibrary.

Video Penjelasan
(https://youtu.be/7Zs4w6n66HA)

## Fitur
- Pencarian buku berdasarkan kata kunci
- Menampilkan daftar buku (Judul, Penulis, Tahun Terbit Pertama)
- Menampilkan detail buku (Judul, Penulis, Tahun Terbit Pertama, Jumlah Edisi, Bahasa)

## Architecture
Aplikasi ini menggunakan arsitektur MVVM (Model-View-ViewModel) dan menggunakan `StateFlow` untuk state management (State-driven UI).
```text
app/src/main/java/com/example/openlibraryapp/
├── data/
│   ├── model/
│   │   └── BookResponse.kt        ← Data class
│   ├── network/
│   │   ├── OpenLibraryApi.kt       ← Interface API (Retrofit)
│   │   └── RetrofitClient.kt       ← Singleton Retrofit
│   └── repository/
│       └── BookRepository.kt       ← Repository pattern
├── theme/
│   ├── Color.kt                    ← Warna custom
│   ├── Theme.kt                    ← Light/Dark ColorScheme
│   └── Type.kt                     ← Typography override
├── ui/
│   ├── navigation/
│   │   └── AppNavigation.kt        ← NavHost + routing
│   ├── screens/
│   │   ├── HomeScreen.kt           ← Halaman utama + search
│   │   └── DetailScreen.kt         ← Halaman detail buku
│   └── viewmodel/
│       ├── BookViewModel.kt        ← ViewModel (logika bisnis)
│       └── UiState.kt              ← Sealed class state
└── MainActivity.kt                 ← Entry point
```


## API
Menggunakan OpenLibrary Search API:
`https://openlibrary.org/search.json?q={keyword}&limit=20`

## Teknis
- **Bahasa**: Kotlin (menggunakan Data Class, Null Safety)
- **UI**: Jetpack Compose dengan Material Design 3 (Theme.kt, Color.kt, Type.kt telah dimodifikasi)
- **Networking**: Retrofit2 + Gson Converter
- **Navigation**: Navigation Compose
- **Architecture**: MVVM, `lifecycle-viewmodel-compose`

## Struktur Kode
- `data/model`: Mendefinisikan struktur JSON response (BookResponse, BookItem).
- `data/network`: Antarmuka Retrofit untuk memanggil REST API (OpenLibraryApi, RetrofitClient).
- `data/repository`: Lapisan Repository untuk abstraksi pemanggilan API (BookRepository).
- `ui/viewmodel`: Menangani business logic dan menyimpan StateFlow (BookViewModel, UiState).
- `ui/screens`: Tampilan Compose (HomeScreen, DetailScreen).
- `ui/navigation`: Rute navigasi Compose (AppNavigation).
- `theme`: Warna, Typography, dan Konfigurasi MaterialTheme (Theme.kt, Color.kt, Type.kt).

## Screenshot Aplikasi
<img width="423" height="890" alt="Screenshot_20261006_234445_OpenLibrary_App" src="https://github.com/user-attachments/assets/9f51fba4-80b2-4558-9acf-146cfeb7069d" />
<img width="423" height="890" alt="Screenshot_20261006_234600_OpenLibrary_App" src="https://github.com/user-attachments/assets/dd23508b-2942-4d97-b0fe-ae937b0d898a" />




