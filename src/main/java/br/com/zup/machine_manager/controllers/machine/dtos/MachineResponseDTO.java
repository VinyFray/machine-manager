package br.com.zup.machine_manager.controllers.machine.dtos;

import br.com.zup.machine_manager.controllers.zupers.dtos.ZuperResponseDTO;

public class MachineResponseDTO {
    private String registerId;
    private String brand;
    private Boolean inUse;
    private ZuperResponseDTO zuper;

    public MachineResponseDTO(String registerId, String brand, Boolean inUse, ZuperResponseDTO zuper) {
        this.registerId = registerId;
        this.brand = brand;
        this.inUse = inUse;
        this.zuper = zuper;
    }

    public MachineResponseDTO() {
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public ZuperResponseDTO getZuper() {
        return zuper;
    }

    public void setZuper(ZuperResponseDTO zuper) {
        this.zuper = zuper;
    }
}
