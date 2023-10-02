package apap.ti.silogistik2106639485.controller;

import apap.ti.silogistik2106639485.service.BarangService;
import apap.ti.silogistik2106639485.service.GudangService;
import apap.ti.silogistik2106639485.service.KaryawanService;
import apap.ti.silogistik2106639485.service.PermintaanPengirimanService;
import apap.ti.silogistik2106639485.dto.GudangMapper;
import apap.ti.silogistik2106639485.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106639485.model.Gudang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GudangController {
    @Autowired
    private GudangService gudangService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private GudangMapper gudangMapper;

    @GetMapping("/")
    public String home(Model model){
        var listGudang = gudangService.getAllGudang();
        var listBarang = barangService.getAllBarang();
        var listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
        var listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("jumlahGudang", listGudang.size());
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("jumlahBarang", listBarang.size());
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("jumlahPermintaanPengiriman", listPermintaanPengiriman.size());
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        model.addAttribute("jumlahKaryawan", listKaryawan.size());
        model.addAttribute("listKaryawan", listKaryawan);
        return "home";
    }

    @GetMapping("gudang/viewall")
    public String listBuku(Model model) {
        //Mendapatkan semua gudang
        List<Gudang> listGudang = gudangService.getAllGudang();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listGudang", listGudang);
        return "viewall-gudang";
    }

    @GetMapping(value = "/gudang/{id}")
    public String detailBuku(@PathVariable(value = "id") Long id, Model model) {
        // Mendapatkan buku melalui kodeBuku
        Gudang gudang = gudangService.getGudangById(id);
        ReadGudangResponseDTO gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

        model.addAttribute("gudangDTO", gudangDTO);

        return "view-gudang";
    }
}
