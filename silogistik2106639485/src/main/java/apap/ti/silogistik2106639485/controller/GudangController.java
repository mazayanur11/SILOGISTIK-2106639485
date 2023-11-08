package apap.ti.silogistik2106639485.controller;

import apap.ti.silogistik2106639485.service.BarangService;
import apap.ti.silogistik2106639485.service.GudangService;
import apap.ti.silogistik2106639485.service.KaryawanService;
import apap.ti.silogistik2106639485.service.PermintaanPengirimanService;
import jakarta.validation.Valid;
import apap.ti.silogistik2106639485.dto.GudangMapper;
import apap.ti.silogistik2106639485.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.Gudang;
import apap.ti.silogistik2106639485.model.GudangBarang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
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

    @GetMapping("gudang")
    public String listGudang(Model model) {
        //Mendapatkan semua gudang
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        model.addAttribute("page", "gudang");
        return "viewall-gudang";
    }

    @GetMapping(value = "/gudang/{idGudang}")
    public String detailGudang(@PathVariable(value = "idGudang") Long id, Model model) {
        Gudang gudang = gudangService.getGudangById(id);
        ReadGudangResponseDTO gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("page", "gudang");
        return "view-gudang";
    }

    @GetMapping(value = "/gudang/cari-barang")
    public String formCariBarang(@RequestParam(name = "sku", required = false) String sku, Model model) {
        if (sku != null && !sku.isEmpty()) {
            Barang barang = barangService.getBarangById(sku);
            List<GudangBarang> listGudangBarang = barang.getListGudangBarang();
            
            model.addAttribute("listGudangBarang", listGudangBarang);
        }
        
        List<Barang> listBarang = barangService.getAllBarangSortedByMerk();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("page", "gudang");
        return "cari-barang";
    }

    @GetMapping(value = "/gudang/cari-barang-kategori")
    public String formCariBarangKategori(@RequestParam(name = "tipeBarang", required = false) Integer tipeBarang, Model model) {
        if (tipeBarang != null) {
            List<Barang> listBarang = barangService.getBarangByKategori(tipeBarang);
            
            model.addAttribute("listBarangWithStok", barangService.getStokBarang(listBarang));
        }
        
        List<Barang> listBarang = barangService.getAllBarangSortedByMerk();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("page", "barang");
        return "cari-barang-kategori";
    }

    @GetMapping(value = "/gudang/{idGudang}/restock-barang")
    public String formRestockBarang(@PathVariable(value = "idGudang") Long id, Model model) {
        Gudang gudang = gudangService.getGudangById(id);
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);
        var listBarang = barangService.getAllBarangSortedByMerk();

        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("page", "gudang");
        return "form-restock-barang";
    }

    @PostMapping("gudang/{idGudang}/restock-barang")
    public RedirectView restockBarang(@Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                String defaultMessage = error.getDefaultMessage();
                errorMessage.append(defaultMessage).append("<br>");
            }

            model.addAttribute("gudangDTO", gudangDTO);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return new RedirectView("/gudang/restock-barang");
        }

        try {
            Gudang gudang = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);
            gudangService.restockBarang(gudang);
            model.addAttribute("id", gudang.getId());

            redirectAttributes.addFlashAttribute("page", "gudang");
            return new RedirectView("/gudang/" + gudangDTO.getId());
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Barang sudah terdaftar di gudang");
            redirectAttributes.addFlashAttribute("page", "gudang");
            return new RedirectView("/gudang/" + gudangDTO.getId() + "/restock-barang");
        } catch (TransactionSystemException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Stok harus positif");
            return new RedirectView("/gudang/" + gudangDTO.getId() + "/restock-barang");
        }
    }

    @PostMapping(value = "gudang/{id}/restock-barang", params = {"addRow"})
    public String addRowBarang(
        UpdateGudangRequestDTO updateGudangRequestDTO,
        Model model
    ) {
        if (updateGudangRequestDTO.getListGudangBarang() == null || updateGudangRequestDTO.getListGudangBarang().size() == 0) {
            updateGudangRequestDTO.setListGudangBarang(new ArrayList<>());
        }

        updateGudangRequestDTO.getListGudangBarang().add(new GudangBarang());

        model.addAttribute("gudangDTO", updateGudangRequestDTO);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("page", "gudang");
        return "form-restock-barang";
    }
}