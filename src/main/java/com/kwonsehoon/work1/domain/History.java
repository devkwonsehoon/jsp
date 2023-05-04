package com.kwonsehoon.work1.domain;

import lombok.*;

import java.util.Date;

@Data
public class History {
    private Long id;
    private String lat;
    private String lnt;
    private Date createAt;

}
