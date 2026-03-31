package com.medicalstore.service;

import com.medicalstore.entity.PurchaseOrder;
import com.medicalstore.entity.PurchaseOrderItem;
import com.medicalstore.repository.PurchaseOrderItemRepository;
import com.medicalstore.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    public List<PurchaseOrder> getPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }
    //create PurchaseOrder
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }


    public Optional<PurchaseOrder> getOrderById(Long id) {
      return purchaseOrderRepository.findById(id);
    }
//Purchase orderitem service operations==========================================

}
