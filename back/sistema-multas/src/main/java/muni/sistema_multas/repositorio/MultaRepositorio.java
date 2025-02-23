package muni.sistema_multas.repositorio;

import muni.sistema_multas.modelo.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultaRepositorio extends JpaRepository<Multa, String> {
    Optional<Multa> findByPlaca(String placa);
    List<Multa> findByEstado(String estado);
    //para el pago de placas

}

