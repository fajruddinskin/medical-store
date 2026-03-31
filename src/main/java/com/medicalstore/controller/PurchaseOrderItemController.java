package com.medicalstore.controller;

import com.medicalstore.entity.PurchaseOrderItem;
import com.medicalstore.service.purchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchaseorderItem")
public class PurchaseOrderItemController {
    @Autowired
    private purchaseOrderItemService purchaseOrderItemService;

    @PostMapping
    public PurchaseOrderItem createPurchaseOrderItem(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemService.addOrderItem(purchaseOrderItem);

    }

    @GetMapping
    public List<PurchaseOrderItem> getAllPurchaseOrdersItem() {
        return purchaseOrderItemService.getPurchaseOrderit();
    }

    /*
      @GetMapping
    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrderService.getPurchaseOrder();
    }
     =========================================================
     @GetMapping("{id}")
    public ResponseEntity<PurchaseOrder> PurchaseOrdersById(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseorder= purchaseOrderService.getOrderById(id);
        return purchaseorder.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }*/
    @GetMapping("{id}")
    public ResponseEntity<PurchaseOrderItem> PurchaseOrdersById(@PathVariable Integer id) {
        Optional<PurchaseOrderItem> purchaseorder = purchaseOrderItemService.getOrderById(id);
        return purchaseorder.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());

    }
}