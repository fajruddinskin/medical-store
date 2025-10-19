package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="suppliers")
public class Supplier {
    @Id
    private String id;
    private  String name;
    private String contactPerson;
    private String email;
    private String phone;
    private String address;
    // List<PurchaseOrder> purchaseOrders

    public Supplier(){}
    public Supplier(String id, String name, String contactPerson, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {        return id;    }

    public void setId(String id) {        this.id = id;    }

    public String getName() {        return name;    }

    public void setName(String name) {        this.name = name;    }

    public String getContactPerson() {        return contactPerson;    }

    public void setContactPerson(String contactPerson) {   this.contactPerson = contactPerson;    }

    public String getEmail() {        return email;    }

    public void setEmail(String email) {        this.email = email;    }

    public String getPhone() {        return phone;    }

    public void setPhone(String phone) {        this.phone = phone;    }

    public String getAddress() {        return address;    }

    public void setAddress(String address) {        this.address = address;    }
}
