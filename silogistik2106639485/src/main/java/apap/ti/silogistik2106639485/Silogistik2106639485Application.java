package apap.ti.silogistik2106639485;

import apap.ti.silogistik2106639485.dto.BarangMapper;
import apap.ti.silogistik2106639485.dto.GudangBarangMapper;
import apap.ti.silogistik2106639485.dto.GudangMapper;
import apap.ti.silogistik2106639485.dto.KaryawanMapper;
import apap.ti.silogistik2106639485.dto.PermintaanPengirimanBarangMapper;
import apap.ti.silogistik2106639485.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106639485.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.service.BarangService;
import apap.ti.silogistik2106639485.service.GudangBarangService;
import apap.ti.silogistik2106639485.service.GudangService;
import apap.ti.silogistik2106639485.service.KaryawanService;
import apap.ti.silogistik2106639485.service.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106639485.service.PermintaanPengirimanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import jakarta.transaction.Transactional;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Silogistik2106639485Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106639485Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(
		GudangService gudangService, 
		BarangService barangService, 
		KaryawanService karyawanService, 
		GudangBarangService gudangBarangService, 
		PermintaanPengirimanService permintaanPengirimanService, 
		PermintaanPengirimanBarangService permintaanPengirimanBarangService, 
		GudangMapper gudangMapper, 
		KaryawanMapper karyawanMapper, 
		BarangMapper barangMapper, 
		GudangBarangMapper gudangBarangMapper, 
		PermintaanPengirimanMapper permintaanPengirimanMapper, 
		PermintaanPengirimanBarangMapper permintaanPengirimanBarangMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			for (int i = 0; i < 5; i++) {
				var barangDTO = new CreateBarangRequestDTO();
				double randomTipe = 1 + Math.random()*4;
				int randomTipeBarang = (int) randomTipe;
				barangDTO.setTipeBarang(randomTipeBarang);
				barangDTO.setSku(barangService.getNamaTipeBarang(randomTipeBarang) + String.format("%03d", barangService.getNextSku(randomTipeBarang)));
				barangDTO.setMerk(faker.commerce().productName());
				double randomHarga = 1 + Math.random()*1000000000;
				barangDTO.setHargaBarang((long) randomHarga);
				var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
				
				var gudangDTO = new CreateGudangRequestDTO();
				gudangDTO.setNama(faker.address().cityName());
				gudangDTO.setAlamatGudang(faker.address().fullAddress());
				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
				
				var gudangBarangDTO = new CreateGudangBarangRequestDTO();
				gudangBarangDTO.setGudang(gudang);
				gudangBarangDTO.setBarang(barang);
				double temp = Math.random()*999;
				int randomStok = (int) temp;
				gudangBarangDTO.setStok(randomStok);
				var gudangBarang = gudangBarangMapper.createGudangBarangRequestDTOToGudangBarang(gudangBarangDTO);
	
				var karyawanDTO = new CreateKaryawanRequestDTO();
				karyawanDTO.setNama(faker.name().name());
				double random = 1 + Math.random()*1;
				int jenisKelaminRandom = (int)random;
				karyawanDTO.setJenisKelamin(jenisKelaminRandom);
				int currentYear = LocalDate.now().getYear();
				int maxBirthYear = currentYear - 60;
				int minBirthYear = currentYear - 17;
				int randomBirthYear = faker.number().numberBetween(minBirthYear, maxBirthYear);
				int randomBirthMonth = faker.number().numberBetween(1, 13);
				int randomBirthDay = faker.number().numberBetween(1, Month.of(randomBirthMonth).maxLength());
				LocalDate randomBirthday = LocalDate.of(randomBirthYear, randomBirthMonth, randomBirthDay);
				karyawanDTO.setTanggalLahir(randomBirthday);
				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
	
				var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
				permintaanPengirimanDTO.setNamaPenerima(faker.name().fullName());
				permintaanPengirimanDTO.setAlamatPenerima(faker.address().fullAddress());
				long startEpochDay = LocalDate.now().toEpochDay();
				long endEpochDay = LocalDate.of(2023, 12, 31).toEpochDay();
				long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
				permintaanPengirimanDTO.setTanggalPengiriman(LocalDate.ofEpochDay(randomEpochDay));
				permintaanPengirimanDTO.setBiayaPengiriman(faker.number().numberBetween(1, 100000));
				permintaanPengirimanDTO.setJenisLayanan(1 + (int)Math.random()*3);
				int year = faker.number().numberBetween(2023, 2024);
				int month = faker.number().numberBetween(1, 13);
				int day = faker.number().numberBetween(1, 28);
				int hour = faker.number().numberBetween(0, 23);
				int minute = faker.number().numberBetween(0, 59);
				int second = faker.number().numberBetween(0, 59);
				permintaanPengirimanDTO.setWaktuPermintaan(LocalDateTime.of(year, month, day, hour, minute, second));
				permintaanPengirimanDTO.setKaryawan(karyawan);
				permintaanPengirimanDTO.setNomorPengiriman(permintaanPengirimanService.generateNomorPengiriman(permintaanPengirimanDTO));
				var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
	
				var permintaanPengirimanBarangDTO = new CreatePermintaanPengirimanBarangRequestDTO();
				permintaanPengirimanBarangDTO.setPermintaanPengiriman(permintaanPengiriman);
				permintaanPengirimanBarangDTO.setBarang(barang);
				double randomKuantitas = 1 + Math.random()*100;
				permintaanPengirimanBarangDTO.setKuantitasPengiriman((int) randomKuantitas);
				var permintaanPengirimanBarang = permintaanPengirimanBarangMapper.createPermintaanPengirimanBarangRequestDTOToPermintaanPengirimanBarang(permintaanPengirimanBarangDTO);
				
				barangService.saveBarang(barang);
				gudangService.saveGudang(gudang);
				gudangBarangService.saveGudangBarang(gudangBarang);
				karyawanService.saveKaryawan(karyawan);
				permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);
				permintaanPengirimanBarangService.savePermintaanPengirimanBarang(permintaanPengirimanBarang);
			}
		};
	}
}