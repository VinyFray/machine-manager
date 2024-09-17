package br.com.zup.machine_manager.repository.models;

public class Machine {
    private String registerId;
    private String brand;
    private Boolean isInUse;
    private Zuper zuper;

    public Machine(String registerId, String brand, Boolean isInUse, Zuper zuper) {
        this.registerId = registerId;
        this.brand = brand;
        this.isInUse = isInUse;
        this.zuper = zuper;
    }

    public Machine(String registerId, String brand, Boolean isInUse) {
        this.registerId = registerId;
        this.brand = brand;
        this.isInUse = isInUse;
    }

    public Machine() {
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
        return isInUse;
    }

    public void setInUse(Boolean inUse) {
        isInUse = inUse;
    }

    public Zuper getZuper() {
        return zuper;
    }

    public void setZuper(Zuper zuper) {
        this.zuper = zuper;
    }
}
