package com.midwesttape.project.challengeapplication.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address {
    private final Long id;
    private final String address1;
    private final String address2;
    private final String city;
    private final String state;
    private final String postal;
}
