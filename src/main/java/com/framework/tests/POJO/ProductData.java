package com.framework.tests.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductData {
    private String color;
    private String capacity;
    @JsonProperty("capacity GB")
    private Integer capacityGB;
    private Double price;
    private String generation;
    private Integer year;
    private String cpuModel;
    private String hardDiskSize;
    private String strapColour;
    private String caseSize;
    private String description;
    private String Color; // For cases where key is capitalized
    private String Capacity;
    private Double screenSize;
    private String Generation;
    private String Price;
}
