package apap.ti.silogistik2106639485.controller;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106639485.dto.BarangMapper;
import apap.ti.silogistik2106639485.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.GudangBarang;
import apap.ti.silogistik2106639485.service.BarangService;
import apap.ti.silogistik2106639485.service.GudangBarangService;
import jakarta.validation.Valid;

@Controller
public class BarangController {
    @Autowired
    private BarangService barangService;

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private GudangBarangService gudangBarangService;

    @GetMapping("barang/viewall")
    public String listBarang(Model model) {
        //Mendapatkan semua gudang
        List<Barang> listBarang = barangService.getAllBarang();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("stokBarang", barangService.getStokBarang(listBarang));
        return "viewall-barang";
    }

    @GetMapping(value = "/barang/{id}")
    public String detailBarang(@PathVariable(value = "id") String sku, Model model) {
        // Mendapatkan buku melalui kodeBuku
        Barang barang = barangService.getBarangById(sku);
        List<GudangBarang> listGudangBarang = gudangBarangService.getStokOfBarang(barang);
        int totalStok = 0;

        for (GudangBarang gudangBarang : listGudangBarang) {
            totalStok += gudangBarang.getStok();
        }

        ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);

        model.addAttribute("barangDTO", barangDTO);
        model.addAttribute("totalStok", totalStok);

        return "view-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);

        return "form-add-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {
        //Validasi gagal, kembalikan pesan error
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder(); //Menginisiasi error message

            //Mengambil setiap error message yang ada
            for (FieldError error : bindingResult.getFieldErrors()) {
                String defaultMessage = error.getDefaultMessage();
                errorMessage.append(defaultMessage).append("<br>"); //Menampilkan error message dengan tampilan ke bawah
            }
            model.addAttribute("errorMessage", errorMessage.toString()); //Menyimpan message error di model untuk ditampilkan di HTML
            model.addAttribute("barangDTO", barangDTO);
            
            return "form-add-barang";
        }

        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        String namaTipeBarang = barangService.getNamaTipeBarang(barang.getTipeBarang());
        long noSKU = barangService.getNextSku(barang.getTipeBarang());
        barang.setSku(namaTipeBarang + String.format("%03d", noSKU));
        barangService.saveBarang(barang);

        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("stokBarang", barangService.getStokBarang(listBarang));
        return "viewall-barang";
    }
}
