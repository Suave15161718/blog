package com.sjjwn.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class IpLocation implements Serializable {

    private String query;

    private String country;

    private String regionName;

    private String city;

    private String lon;

    private String lat;
}
