package br.com.zup.machine_manager.controllers.zupers.dtos;

public class ZuperResponseDTO {
    private int zuperId;
    private String name;
    private String address;
    private String postalCode;

    public ZuperResponseDTO(int zuperId, String name, String address, String postalCode) {
        this.zuperId = zuperId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    public ZuperResponseDTO() {
    }

    public int getZuperId() {
        return zuperId;
    }

    public void setZuperId(int zuperId) {
        this.zuperId = zuperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
