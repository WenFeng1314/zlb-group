package com.zlb.authority.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -7009376445001864613L;
    private Integer id;
    private Integer companyId;
    private String username;
    private String phone;
    private Integer Type;
    private String code;
    private String companyName;
    private String ServiceCompanyName;
    private Integer level;
    private Integer serviceCompanyId;
}
