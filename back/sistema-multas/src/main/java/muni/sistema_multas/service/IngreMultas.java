package muni.sistema_multas.service;

import jakarta.persistence.EntityNotFoundException;
import muni.sistema_multas.modelo.Multa;
import muni.sistema_multas.repositorio.MultaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngreMultas implements IntIngreMultas{
    @Autowired
    private MultaRepositorio multaRepositorio;

    private static final int MORA = 50;
    @Override
    public Multa registrarMulta(Multa multa) {
        Multa multaExistente = multaRepositorio.findByPlaca(multa.getPlaca()).orElse(null);

        if (multaExistente != null) {
            // Si ya existe una multa, actualizamos los valores
            multaExistente.setEstado("M");
            multaExistente.setMonto(multaExistente.getMonto() + multa.getMonto() + 50); // Sumar multa + mora
            multaExistente.setCantidadMultas(multaExistente.getCantidadMultas() + 1);
            return multaRepositorio.save(multaExistente);
        } else {
            // Si no existe una multa previa, se registra una nueva
            multa.setEstado("A");
            multa.setCantidadMultas(1);
            return multaRepositorio.save(multa);
        }

    }//pago de placa

    @Override
    public Optional<Multa> buscarPorPlaca(String placa) {
        return multaRepositorio.findByPlaca(placa);
    }

    @Override
    public Multa pagarMulta(String placa) {
        Optional<Multa> multaOpt = multaRepositorio.findByPlaca(placa);

        if (multaOpt.isPresent()) {
            Multa multa = multaOpt.get();
            multa.setEstado("P");
            multa.setMonto(0);
            multa.setCantidadMultas(0);
            return multaRepositorio.save(multa);
        } else {
            throw new EntityNotFoundException("No se encontró una multa con la placa: " + placa);
        }
    }

    @Override
    public List<Multa> obtenerMultasPorEstado(String estado) {
        if ("todas".equalsIgnoreCase(estado)) {
            return multaRepositorio.findAll(); // Retorna todas las multas
        }
        return multaRepositorio.findByEstado(estado); // Retorna multas por estado específico
    }



}
