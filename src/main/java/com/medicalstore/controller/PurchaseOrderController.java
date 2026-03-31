package com.medicalstore.controller;

import com.medicalstore.entity.PurchaseOrder;
import com.medicalstore.entity.PurchaseOrderItem;
import com.medicalstore.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/purchaseorder")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrderService.getPurchaseOrder();
    }
    //create PurchaseOrder
    @PostMapping
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
       // return purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }
    /*  @GetMapping ("{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id){
        Optional<Prescription> prescription=prescriptionService.getPrescriptionById(id);
        return prescription.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());*/
    @GetMapping("{id}")
    public ResponseEntity<PurchaseOrder> PurchaseOrdersById(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseorder= purchaseOrderService.getOrderById(id);
        return purchaseorder.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }




}
