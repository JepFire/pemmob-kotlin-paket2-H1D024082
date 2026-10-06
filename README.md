# OpenLibrary App

Aplikasi Katalog dan Eksplorasi Buku dari OpenLibrary.

## Fitur
- Pencarian buku berdasarkan kata kunci
- Menampilkan daftar buku (Judul, Penulis, Tahun Terbit Pertama)
- Menampilkan detail buku (Judul, Penulis, Tahun Terbit Pertama, Jumlah Edisi, Bahasa)

## Architecture
Aplikasi ini menggunakan arsitektur MVVM (Model-View-ViewModel) dan menggunakan `StateFlow` untuk state management (State-driven UI).

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
