package br.com.zup.machine_manager.controllers.machine.dtos;

public class MachineUpdateDTO {
    private String registerId;
    private String brand;
    private Boolean InUse;
    private Integer zuperId;

    public MachineUpdateDTO(String registerId, String brand, Boolean InUse, Integer zuperId) {
        this.registerId = registerId;
        this.brand = brand;
        this.InUse = InUse;
        this.zuperId = zuperId;
    }

    public MachineUpdateDTO() {
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
        return InUse;
    }

    public void setInUse(Boolean inUse) {
        InUse = inUse;
    }

    public Integer getZuperId() {
        return zuperId;
    }

    public void setZuperId(Integer zuperId) {
        this.zuperId = zuperId;
    }
}
