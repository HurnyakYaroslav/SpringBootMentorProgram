package com.mentorship.demo.dao;

import com.mentorship.demo.model.Abiturient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class AbiturientDao {
    @PersistenceContext
    private EntityManager manager;

    public Abiturient save(Abiturient newAbiturient) {
        log.info("Saving Abiturient: {}", newAbiturient);
        manager.persist(newAbiturient);
        return newAbiturient;
    }

    public Abiturient getById(int id) {
        log.info("Getting Abiturient by id: {}", id);
        return manager.find(Abiturient.class, id);
    }

    public Abiturient update(Abiturient abiturient) {
        log.info("Updating Abiturient id: {}", abiturient.getId());
        return manager.merge(abiturient);
    }

    public void delete(Abiturient abiturient) {
        log.info("Deleting Abiturient id: {}", abiturient.getId());
        manager.remove(abiturient);

    }

    public List<Abiturient> getAll() {
        log.info("Getting List of Users");
        return manager.createQuery("SELECT u FROM Abiturient u", Abiturient.class)
                .getResultList();
    }

}
