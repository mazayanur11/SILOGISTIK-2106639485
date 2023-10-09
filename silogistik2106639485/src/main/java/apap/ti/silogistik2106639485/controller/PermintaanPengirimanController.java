package apap.ti.silogistik2106639485.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
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

import apap.ti.silogistik2106639485.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.GudangBarang;
import apap.ti.silogistik2106639485.model.Karyawan;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106639485.service.BarangService;
import apap.ti.silogistik2106639485.service.KaryawanService;
import apap.ti.silogistik2106639485.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
        List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengirimanDTO = new ArrayList<>();
        
        for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
            ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
            listPermintaanPengirimanDTO.add(readPermintaanPengirimanResponseDTO);
        }

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        model.addAttribute("listPermintaanPengirimanDTO", listPermintaanPengirimanDTO);
        model.addAttribute("page", "permintaan-pengiriman");
        return "viewall-permintaan-pengiriman";
    }

    @GetMapping(value = "/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable(value = "idPermintaanPengiriman") Long id, Model model) {
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        ReadPermintaanPengirimanResponseDTO permintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
        String jenisLayanan = permintaanPengirimanService.getStringJenisLayanan(permintaanPengiriman.getJenisLayanan());
        Dictionary<Barang, Long> totalHargaBarang = new Hashtable<>();

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            totalHargaBarang.put(permintaanPengirimanBarang.getBarang(), permintaanPengirimanBarang.getBarang().getHargaBarang() * permintaanPengirimanBarang.getKuantitasPengiriman());
        }

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("jenisLayanan", jenisLayanan);
        model.addAttribute("totalHargaBarang", totalHargaBarang);
        model.addAttribute("page", "permintaan-pengiriman");
        return "view-permintaan-pengiriman";
    }
    
    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model) {
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("page", "permintaan-pengiriman");
        return "form-create-permintaan-pengiriman";
    }
    
    @PostMapping("permintaan-pengiriman/tambah")
    public RedirectView addPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //Validasi gagal, kembalikan pesan error
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder(); //Menginisiasi error message

            //Mengambil setiap error message yang ada
            for (FieldError error : bindingResult.getFieldErrors()) {
                String defaultMessage = error.getDefaultMessage();
                errorMessage.append(defaultMessage).append("<br>"); //Menampilkan error message dengan tampilan ke bawah
            }

            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            redirectAttributes.addFlashAttribute("page", "permintaan-pengiriman");
            return new RedirectView("/permintaan-pengiriman/tambah");
        }
        
        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        permintaanPengiriman.setNomorPengiriman(permintaanPengirimanService.generateNomorPengiriman(permintaanPengirimanDTO));
        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);
        
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("listBarang", barangService.getAllBarang());
        redirectAttributes.addFlashAttribute("page", "permintaan-pengiriman");
        return new RedirectView("/permintaan-pengiriman");
    }
    
    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowBarang(
        CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,
        Model model
        ) {
        if (permintaanPengirimanDTO.getListPermintaanPengirimanBarang() == null || permintaanPengirimanDTO.getListPermintaanPengirimanBarang().size() == 0) {
            permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
        model.addAttribute("page", "permintaan-pengiriman");
        return "form-create-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaan(@PathVariable("idPermintaanPengiriman") Long id, Model model) {
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);

        try {
            permintaanPengirimanService.cancelPermintaan(permintaanPengiriman);
            
            model.addAttribute("successMessage", "Permintaan pengiriman dengan nomor pengiriman " + permintaanPengiriman.getNomorPengiriman() + " berhasil dihapus");
            return "success-cancel-permintaan";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "failed-cancel-permintaan";
        }
    }

    @GetMapping("filter-permintaan-pengiriman")
    public String filterPermintaanPengiriman(
        @RequestParam(name = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String start, 
        @RequestParam(name = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String end, 
        @RequestParam(name = "sku", required = false) String sku, Model model
    ) {
        if (start != null && end != null && sku != null) {
            Barang barang = barangService.getBarangById(sku);
            LocalTime localTime = LocalTime.of(0, 0);
            LocalDate localStartDate = LocalDate.parse(start);
            LocalDate localEndDate = LocalDate.parse(end);

            model.addAttribute("listBarang", barangService.getAllBarangSortedByMerk());
            model.addAttribute("listPermintaanPengiriman", permintaanPengirimanService.filterWaktuPenerimaan(LocalDateTime.of(localStartDate, localTime), LocalDateTime.of(localEndDate, localTime), barang));
            model.addAttribute("page", "bonus");
            return "filter-permintaan-pengiriman";
        }

        List<Barang> listBarang = barangService.getAllBarangSortedByMerk();
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
        List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengirimanDTO = new ArrayList<>();

        for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
            listPermintaanPengirimanDTO.add(permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman));
        }
        
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengirimanDTO);
        model.addAttribute("page", "bonus");
        return "filter-permintaan-pengiriman";
    }
}
