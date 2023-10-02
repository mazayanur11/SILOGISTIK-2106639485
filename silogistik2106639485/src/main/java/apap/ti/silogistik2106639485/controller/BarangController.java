package apap.ti.silogistik2106639485.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import apap.ti.silogistik2106639485.dto.BarangMapper;
import apap.ti.silogistik2106639485.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106639485.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.Gudang;
import apap.ti.silogistik2106639485.service.BarangService;

@Controller
public class BarangController {
    @Autowired
    private BarangService barangService;

    @Autowired
    private BarangMapper barangMapper;

    @GetMapping("barang/viewall")
    public String listBarang(Model model) {
        //Mendapatkan semua gudang
        List<Barang> listBarang = barangService.getAllBarang();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }

    @GetMapping(value = "/barang/{id}")
    public String detailBuku(@PathVariable(value = "id") String sku, Model model) {
        // Mendapatkan buku melalui kodeBuku
        Barang barang = barangService.getBarangById(sku);
        ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "view-barang";
    }
}
