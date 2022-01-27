package com.mentorship.demo.dao;

import com.mentorship.demo.model.Abiturient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AbiturientDaoTest {

    @Autowired
    private AbiturientDao abiturientDao;

    @Test
    public void sampleTestCase() {
        Abiturient dave = new Abiturient(1, "Dave", "Matthews", "3806", "93fREGfwefeuwif742");
        abiturientDao.save(dave);
        Abiturient carter = new Abiturient(  2, "Carter", "Beauford", "3806", "93fREGfwefeuwif742");
        abiturientDao.save(carter);
        Abiturient result = abiturientDao.findAbiturientByFirstName("Dave");
        assertEquals(result.getFirstName(), "Dave");
    }
}