package com.example.orderdemo.request;

import lombok.Data;

@Data
public class SearchRequest {
    private Long idCat;
    private String proName;
    private Long min;
    private Long max;
}
