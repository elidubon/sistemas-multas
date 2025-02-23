package muni.sistema_multas.controlador;

import jakarta.persistence.EntityNotFoundException;
import muni.sistema_multas.modelo.Multa;
import muni.sistema_multas.service.IngreMultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/multas")
public class IngreMultaController {
    @Autowired
    private IngreMultas multaService;

    @PostMapping("/registrar")
    public ResponseEntity<Multa> registrarMulta(@RequestBody Multa multa) {
        System.out.println("Recibido: " + multa); // 🔹 Verifica que los datos están llegando


        Multa nuevaMulta = multaService.registrarMulta(multa);
        return ResponseEntity.ok(nuevaMulta);
    }

    @GetMapping("/buscar/{placa}")
    public ResponseEntity<?> buscarMulta(@PathVariable String placa) {
        Optional<Multa> multa = multaService.buscarPorPlaca(placa);

        if (multa.isPresent()) {
            return ResponseEntity.ok(multa.get());
        } else {
            // Devuelve un objeto JSON con un mensaje de error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una multa con la placa: " + placa);
        }
    }

    @PutMapping("/pagar/{placa}")
    public ResponseEntity<?> pagarMulta(@PathVariable String placa) {
        try {
            Multa multaPagada = multaService.pagarMulta(placa);
            return ResponseEntity.ok(multaPagada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public List<Multa> obtenerMultasPorEstado(@PathVariable String estado) {
        return multaService.obtenerMultasPorEstado(estado);
    }
}
