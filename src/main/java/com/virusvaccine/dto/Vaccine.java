package com.virusvaccine.dto;

public class Vaccine {
    private final Long id;
    private final String code;
    private final String name;
    private final Integer dose;
    private final Long virusId;

    public Vaccine(Long id, String code, String name, Integer dose, Long virusId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dose = dose;
        this.virusId = virusId;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getDose() {
        return dose;
    }

    public Long getVirusId() {
        return virusId;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dose=" + dose +
                ", virusId=" + virusId +
                '}';
    }
}
