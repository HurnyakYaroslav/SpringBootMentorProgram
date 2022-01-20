package com.mentorship.demo.security;

import com.mentorship.demo.dao.AbiturientDao;
import com.mentorship.demo.model.Abiturient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class JwtAbiturientService implements UserDetailsService {


    private final AbiturientDao abiturientDao;

    @Autowired
    public JwtAbiturientService(AbiturientDao abiturientDao) {
        this.abiturientDao = abiturientDao;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException, DataAccessException {
        Abiturient abiturient = abiturientDao.findAbiturientByFirstName(firstName);
        if (abiturient == null) {
            throw new EntityNotFoundException("Abiturient not found with name: ${firstName}");
        }

        return JwtAbiturientFactory.create(abiturient);
    }
}
