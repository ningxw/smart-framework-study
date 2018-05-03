package org.smart4j.chapter3.model;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String contact;
    private String telephone;
    private String email;
    private String remark;
}
