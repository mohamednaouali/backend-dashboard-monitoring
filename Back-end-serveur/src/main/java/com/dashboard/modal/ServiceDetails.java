package com.dashboard.modal;

import lombok.*;



/**
 * collect app info
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceDetails {

    private String idService;
    private String serviceName;
    private int servicePort;

}
