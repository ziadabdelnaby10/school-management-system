package com.ziad.school.model.base;

import lombok.Data;

//This Class is not used because i wanted to test the @ElementCollection
@Data
public class PhoneNumber {
    private String countryCode;
    private String number;
}
