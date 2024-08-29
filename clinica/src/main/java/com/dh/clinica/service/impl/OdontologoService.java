package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;
    public OdontologoService(IOdontologoRepository odontologoRepository){
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {return odontologoRepository.save(odontologo);}

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {return odontologoRepository.findById(id);}

    @Override
    public List<Odontologo> buscarTodos() {return odontologoRepository.findAll();}

    @Override
    public void modificarOdontologo(Odontologo odontologo) {odontologoRepository.save(odontologo);}

    @Override
    public void eliminarOdontologo(Integer id) {odontologoRepository.deleteById(id);}
}
