package com.ltp.contacts.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class Contact {
    private String id;

   @NotBlank(message = "Name cannot be left blank")
    private String name;

   @NotBlank(message = "Phone number cannot be left blank")
    private String phoneNumber;

    public Contact() {
        this.id = UUID.randomUUID().toString();
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
