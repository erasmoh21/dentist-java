package com.example.odontologo.service;

import com.example.odontologo.model.Dentist;
import com.example.odontologo.repository.IDao;
import com.example.odontologo.repository.impl.DentistDaoH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {
    private IDao<Dentist> daoService;

    public DentistService(IDao<Dentist> daoService) {
        this.daoService = daoService;
    }

    public Dentist save(Dentist dentist) {
        if(dentist == null) {
            return null;
        }
        return this.daoService.save(dentist);
    }

    public int delete(long id) {
        if(this.daoService.read(id) == null) {
            return -1;
        }
        this.daoService.delete(id);
        return 1;
    }

    public Dentist update(Dentist dentist) {
        if(dentist == null) {
            return null;
        }
        this.daoService.update(dentist);
        return dentist;
    }

    public Dentist read(long id) {
        if(this.daoService.read(id) == null) {
            return null;
        }
        return this.daoService.read(id);
    }

    public List<Dentist> showAll() {
        if(this.daoService.showAll().isEmpty()) {
            return null;
        }
        return this.daoService.showAll();
    }


}
