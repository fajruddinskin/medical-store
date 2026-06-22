package com.medicalstore.service.customer;


import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.medicalstore.dto.customer.SaleDto;
import com.medicalstore.dto.customer.SaleItemDto;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.customer.Customer;
import com.medicalstore.entity.customer.Sale;
import com.medicalstore.entity.customer.SaleItem;
import com.medicalstore.mapper.customer.SaleItemMapper;
import com.medicalstore.mapper.customer.SaleMapper;

import com.medicalstore.repository.MedicineRepository;
import com.medicalstore.repository.customer.CRepository;
import com.medicalstore.repository.customer.SaleItemRepository;
import com.medicalstore.repository.customer.SaleRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;


import com.lowagie.text.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private CRepository customerRepository;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleItemMapper saleItemMapper;
    @Transactional
    public SaleDto saveSale(SaleDto saleDto) {

        Customer customer = customerRepository
                .findById(saleDto.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Sale sale = new Sale();

        sale.setBillNumber("BILL-" + System.currentTimeMillis());
        sale.setSaleDate(LocalDateTime.now());
        sale.setCustomer(customer);

        double subtotal = 0.0;

        List<SaleItem> saleItems = new ArrayList<>();

        for (SaleItemDto itemDto : saleDto.getItems()) {

            Medicine medicine = medicineRepository
                    .findById(itemDto.getMedicineId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Medicine not found: "
                                            + itemDto.getMedicineId()));

            if (medicine.getQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for "
                                + medicine.getName());
            }

            SaleItem saleItem = new SaleItem();

            saleItem.setSale(sale);
            saleItem.setMedicine(medicine);
            saleItem.setQuantity(itemDto.getQuantity());

            saleItem.setUnitPrice(medicine.getPrice());

            double itemTotal =
                    medicine.getPrice() * itemDto.getQuantity();

            saleItem.setTotalPrice(itemTotal);

            saleItems.add(saleItem);

            medicine.setQuantity(
                    medicine.getQuantity()
                            - itemDto.getQuantity());

            medicineRepository.save(medicine);

            subtotal += itemTotal;
        }

        double discount =
                saleDto.getDiscount() == null
                        ? 0.0
                        : saleDto.getDiscount();

        double totalAmount = subtotal - discount;

        sale.setSubtotal(subtotal);
        sale.setDiscount(discount);
        sale.setTotalAmount(totalAmount);

        sale.setSaleItems(saleItems);

        Sale savedSale = saleRepository.save(sale);

        SaleDto response = saleMapper.toDto(savedSale);

        response.setItems(
                savedSale.getSaleItems()
                        .stream()
                        .map(saleItemMapper::toDto)
                        .toList()
        );

        return response;
    }
    public byte[] generateInvoice(Long saleId) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();

        PdfWriter.getInstance(document, out);

        document.open();

        document.add(new Paragraph("Medical Store Invoice"));

        document.close();

        return out.toByteArray();
    }
}