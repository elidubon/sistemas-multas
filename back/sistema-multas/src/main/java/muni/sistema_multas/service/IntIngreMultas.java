package muni.sistema_multas.service;

import muni.sistema_multas.modelo.Multa;

import java.util.List;
import java.util.Optional;

public interface IntIngreMultas {
    Multa registrarMulta(Multa multa);
    Optional<Multa> buscarPorPlaca(String placa);
    Multa pagarMulta(String placa);
    List<Multa> obtenerMultasPorEstado(String estado);
}
