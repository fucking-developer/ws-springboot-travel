package com.jonhdevelop.springbootapitravel.response;

import com.jonhdevelop.springbootapitravel.util.AeroLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlyResponse implements Serializable {

    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private BigDecimal price;
    private String originName;
    private String destinyName;
    private AeroLine aeroLine;

}
