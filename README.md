# API Server Sistem Informasi dan Manajemen Data Center (SIMDC)

## Cara menjalankan
- Ubah terlebih dahulu konfigurasi ``spring.jpa.hibernate.ddl-auto``  menjadi ``create`` pada file ``application.properties``
- Buat database dengan nama ``db_apl_simdc``
- Jalankan perintah ``mvn spring-boot:run`` pada terminal
- Dokumentasi API Server dapat diakses melalui URL http://localhost:8080/swagger-ui/index.html
- Jangan lupa mengubah ``spring.jpa.hibernate.ddl-auto``  menjadi ``update`` jika tabel pada database sudah ada