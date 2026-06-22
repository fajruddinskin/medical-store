package com.medicalstore.controller.customer;

import com.medicalstore.dto.customer.SaleDto;
import com.medicalstore.service.customer.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("*")
public class SaleControlle {
    @Autowired
    private  SaleService saleService;
    @PostMapping
    public SaleDto saveSale(
            @RequestBody SaleDto dto) {

        return saleService.saveSale(dto);
    }
    @GetMapping("/{saleId}/invoice")
    public ResponseEntity<byte[]> generateInvoice(
            @PathVariable Long saleId) {

        byte[] pdf =
                saleService.generateInvoice(saleId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=invoice.pdf")
                .contentType(
                        MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
