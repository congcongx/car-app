package com.car.domain.sys;

import lombok.Data;

@Data
public class AccessToken {

    private String access_token;

    private long expires_in;
}
