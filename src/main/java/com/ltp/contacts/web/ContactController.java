package com.ltp.contacts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.service.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("contact/all")
    @ResponseBody
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactService.getContacts();
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

    @GetMapping("/contact/{id}")
    @ResponseBody
    public ResponseEntity<Contact> getContact(@PathVariable("id") String id) {
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> addContact(@RequestBody Contact contact) {
        System.out.println(contact);
        contactService.addContact(contact);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED, null, 0);
    }

    @ResponseBody
    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable("id") String id, @RequestBody Contact contact) {
        contactService.updateContact(id, contact);
        Contact c = contactService.getContactById(id);
        System.out.println(c);
        return new ResponseEntity<Contact>(contactService.getContactById(id), HttpStatus.OK);
    }

    @DeleteMapping("contact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") String id) {
        contactService.deleteContact(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT, null, 0);
    }

}
