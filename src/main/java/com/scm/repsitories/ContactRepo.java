package com.scm.repsitories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactRepo extends JpaRepository<Contact, String> {

    // find the contacts by user
    Page<Contact> findByUser(User user, Pageable pageable);

    
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(String userId);


    // For Searching contacts

    // public List<Contact> findByNameContainingAndUser(String name, User user);


    Page<Contact> findByNameContainingAndUser(String namekeyword, User user, Pageable pageable);

    Page<Contact> findByEmailContainingAndUser(String emailkeyword, User user, Pageable pageable);
    
    Page<Contact> findByPhoneNumberContainingAndUser(String phonekeyword, User user, Pageable pageable);
}
