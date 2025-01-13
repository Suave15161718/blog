package com.sjjwn.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class IpLocationPO implements Serializable {

    private String province;

    private String city;

    private String address;

    private String msg;
}
