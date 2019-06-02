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
@Table(name = "WAREHOUSE")
public class Warehouse extends CommonEntity {

    @NotNull
    @Column(name = "NAME")
    private String name;
    
    @Override
    public String toString() {
        return this.getId() + ", " + this.name;
    }

}
