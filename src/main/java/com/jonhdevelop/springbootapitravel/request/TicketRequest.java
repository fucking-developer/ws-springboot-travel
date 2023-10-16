package com.jonhdevelop.springbootapitravel.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest implements Serializable {

    private String idClient;
    private Long idfly;

}
