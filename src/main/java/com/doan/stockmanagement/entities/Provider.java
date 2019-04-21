package com.doan.stockmanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.doan.stockmanagement.common.CommonEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "PROVIDER")
public class Provider extends CommonEntity {

    @NotNull
    @Column(name = "NAME")
    private String name;
    
    @NotNull
    @Column(name = "ADDRESS")
    private String address;

}
