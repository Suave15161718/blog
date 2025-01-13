package com.sjjwn.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewLogsPO {

    private String ip;

    private String msg;

    private String viewTime;

    private String province;

    private String city;

    private String address;
}
