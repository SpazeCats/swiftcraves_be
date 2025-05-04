package com.swiftcraves.onlinefood.request;

import java.util.Date;
import java.util.List;

import com.swiftcraves.onlinefood.model.Address;
import com.swiftcraves.onlinefood.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

        private Long id;
        private String name;
        private String description;
        private String cuisineType;
        private Address address;
        private ContactInformation contactInformation;
        private String openingHours;
        private List<String>images;
        private Date registrationDate;
}
