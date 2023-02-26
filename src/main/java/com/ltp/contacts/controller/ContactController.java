package com.ltp.contacts.controller;

import com.ltp.contacts.entity.Contact;
import com.ltp.contacts.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import com.ltp.contacts.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Tag(name="Get List of Contacts / Get All Contacts")
    @Operation(summary = "Retrieves contacts", description = "Provides a list of all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Contact doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of contact", content = @Content(schema = @Schema(implementation = Contact.class))),
    })


    @GetMapping(value = "/contact/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }


    @GetMapping("/contact/{id}")
    @Tag(name="Get Contact Based on ID")
    @Operation(summary = "Get one contact based on ID", description = "Gets one contact on lists of contacts based on ID")
    public ResponseEntity<Contact> getContact(@PathVariable String id){
            Contact contact = contactService.getContact(id);
            return new ResponseEntity<>(contact, HttpStatus.OK);
    }


    @Tag(name="Create a Contact")
    @Operation(summary = "Create contact", description = "Creates contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful creation of contact"),
            @ApiResponse(responseCode = "400", description = "Bad request: unsuccessful submission", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/contact")
    public ResponseEntity<Contact> createContact(@RequestBody @Valid Contact contact){
        contactService.saveContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }


    @PutMapping("/contact/{id}")
    @Tag(name="Update Contact")
    @Operation(summary = "Update Cotact", description = "Updates contact based on ID")
    public ResponseEntity<HttpStatus> updateContact(@PathVariable String id, @RequestBody @Valid Contact contact){
            contactService.updateContact(id, contact);
            return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/contact/{id}")
    @Tag(name="Delete Cotact")
    @Operation(summary = "Deletes cotact", description = "Deletes contact based on ID")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id){
            contactService.deleteContact(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
