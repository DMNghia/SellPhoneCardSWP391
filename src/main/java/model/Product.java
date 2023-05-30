/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 *
 * @author hp
 */
public class Product {
    private int id,quantity,supplier,createdBy,deletedBy,updatedBy;
    private String name;
    private double price;
    private Timestamp createdAt,deletedAt,updatedAt;
    private boolean isDelete;
    
    public Product() {
    }

    public Product(int id, int quantity, int supplier, int createdBy, int deletedBy, int updatedBy, String name, double price, Timestamp createdAt, Timestamp deletedAt, Timestamp updatedAt, boolean isDelete) {
        this.id = id;
        this.quantity = quantity;
        this.supplier = supplier;
        this.createdBy = createdBy;
        this.deletedBy = deletedBy;
        this.updatedBy = updatedBy;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.updatedAt = updatedAt;
        this.isDelete = isDelete;
    }

    public Product(double price) {
        
        this.price = price;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(int deletedBy) {
        this.deletedBy = deletedBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    
}
