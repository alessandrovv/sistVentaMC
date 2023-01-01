package com.example.vvam.dto;

public class ClientRegistrationDto {
    private String documentoIdentidad;
    private String razonSocial;
    private String email;

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientRegistrationDto() {
    }

    public ClientRegistrationDto(String documentoIdentidad, String razonSocial, String email) {
        this.documentoIdentidad = documentoIdentidad;
        this.razonSocial = razonSocial;
        this.email = email;
    }
}
