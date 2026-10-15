package com.cesde.retosbiz.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "empresas")
public class CompanyModel {
    
    @Id
    @GeneratedValue
    @Column(name = "id_empresa")
    private String id;

    @Column(name = "nombre_empresa")
    private String name;

    @Column(name = "nit_empresa")
    private String nit;

    @Column(name = "direccion_empresa")
    private String address;

    @Column(name = "telefono_empresa")
    private String phone;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    
   

}
