package com.mentorship.demo.dao;

import com.mentorship.demo.model.Abiturient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbiturientDao extends JpaRepository<Abiturient, Long> {
    Abiturient findAbiturientByFirstName(String firstName);
}
