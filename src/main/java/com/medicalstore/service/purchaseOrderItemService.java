package com.medicalstore.service;

import com.medicalstore.entity.PurchaseOrderItem;
import com.medicalstore.repository.PurchaseOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class purchaseOrderItemService {
   @Autowired
        private PurchaseOrderItemRepository purchaseOrderItemRepository;

        public PurchaseOrderItem addOrderItem(PurchaseOrderItem purchaseOrderItem) {
            return purchaseOrderItemRepository.save(purchaseOrderItem);
        }
        //find All
        public List<PurchaseOrderItem> getPurchaseOrderItem() {
            return purchaseOrderItemRepository.findAll();

    }

    public List<PurchaseOrderItem> getPurchaseOrderit() {

            return purchaseOrderItemRepository.findAll();
    }

    public Optional<PurchaseOrderItem> getOrderById(Integer id) {
            return purchaseOrderItemRepository.findById(id);
    }
}
