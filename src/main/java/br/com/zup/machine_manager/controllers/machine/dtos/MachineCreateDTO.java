package br.com.zup.machine_manager.controllers.machine.dtos;

import br.com.zup.machine_manager.repository.models.Zuper;

public class MachineCreateDTO {
    private String brand;
    private Boolean isInUse;
    private Integer zuperId;

    public MachineCreateDTO(String brand, Boolean isInUse, Integer zuperId) {
        this.brand = brand;
        this.isInUse = isInUse;
        this.zuperId = zuperId;
    }

    public MachineCreateDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getInUse() {
        return isInUse;
    }

    public void setInUse(Boolean inUse) {
        isInUse = inUse;
    }

    public Integer getZuperId() {
        return zuperId;
    }

    public void setZuperId(Integer zuperId) {
        this.zuperId = zuperId;
    }
}
