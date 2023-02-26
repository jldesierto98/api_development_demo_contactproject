package com.ltp.contacts.service;

import com.ltp.contacts.entity.Contact;

import java.util.List;

public interface ContactService {

    public Contact getContact(String id);

    public void saveContact(Contact contact);

    public void updateContact(String id, Contact contact);

    public void deleteContact(String id);

    public List<Contact> getAllContacts();
}
