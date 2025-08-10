package com.project.health_care.dto.Request.Response;

import lombok.Data;

@Data
public class HeathCareDTO {
    private String userName;
    private Long id;
    private String diagnosis;
    private String notes;
    private String createdAt;
}
