package apap.ti.silogistik2106639485;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.math.BigInteger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106639485.dto.BarangMapper;
import apap.ti.silogistik2106639485.dto.GudangBarangMapper;
import apap.ti.silogistik2106639485.dto.GudangMapper;
import apap.ti.silogistik2106639485.dto.KaryawanMapper;
import apap.ti.silogistik2106639485.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106639485.service.BarangService;
import apap.ti.silogistik2106639485.service.GudangBarangService;
import apap.ti.silogistik2106639485.service.GudangService;
import apap.ti.silogistik2106639485.service.KaryawanService;
import apap.ti.silogistik2106639485.service.PermintaanPengirimanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106639485Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106639485Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, BarangService barangService, KaryawanService karyawanService, GudangBarangService gudangBarangService, PermintaanPengirimanService permintaanPengirimanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper, BarangMapper barangMapper, GudangBarangMapper gudangBarangMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			var barangDTO = new CreateBarangRequestDTO();
			double randomTipe = 1 + Math.random()*4;
			int randomTipeBarang = (int) randomTipe;
			barangDTO.setTipeBarang(randomTipeBarang);
			String tipe = "";
			if (randomTipeBarang == 1) {
				tipe = "ELEC";
			} else if (randomTipeBarang == 2) {
				tipe = "CLOT";
			} else if (randomTipeBarang == 3) {
				tipe = "FOOD";
			} else if (randomTipeBarang == 4) {
				tipe = "COSM";
			} else {
				tipe = "TOOL";
			}
			double temp1 = 1 + Math.random()*998;
			int kodeTipe = (int) temp1;
			barangDTO.setSku(tipe + String.format("%03d", kodeTipe));
			barangDTO.setMerk(faker.company().name());
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
			
			barangService.saveBarang(barang);
			gudangService.saveGudang(gudang);
			gudangBarangService.saveGudangBarang(gudangBarang);
			karyawanService.saveKaryawan(karyawan);
		};
	}
}
