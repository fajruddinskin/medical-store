package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="stockalerts")
public class StockAlert {
    @Id
        private Integer id;
    //- Medicine medicine
    private Integer minimumStockLevel;
    private Integer currentStock;
   // private AlertType alertType;
    private LocalDateTime alertDate;
    private boolean resolved;

    public StockAlert(Integer id, Integer minimumStockLevel, Integer currentStock, LocalDateTime alertDate, boolean resolved) {
        this.id = id;
        this.minimumStockLevel = minimumStockLevel;
        this.currentStock = currentStock;
        this.alertDate = alertDate;
        this.resolved = resolved;
    }

    public StockAlert() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id;}
    public Integer getMinimumStockLevel() { return minimumStockLevel; }
    public void setMinimumStockLevel(Integer minimumStockLevel) {this.minimumStockLevel = minimumStockLevel;  }

    public Integer getCurrentStock() { return currentStock; }

    public void setCurrentStock(Integer currentStock) {this.currentStock = currentStock; }

    public LocalDateTime getAlertDate() { return alertDate;}

    public void setAlertDate(LocalDateTime alertDate) { this.alertDate = alertDate; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}
