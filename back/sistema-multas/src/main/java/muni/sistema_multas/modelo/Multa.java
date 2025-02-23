package muni.sistema_multas.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "placa", nullable = false, unique = true, length = 6)
    private String placa = "";

    @Column(name = "tipo_vehiculo", nullable = false)
    private String tipoVehiculo = "";

    @Column(name = "monto", nullable = false)
    private Integer monto = 0;

    @Column(name = "estado", nullable = false)
    private String estado = "A";

    @Column(name = "cantidad_multas", nullable = false)
    private Integer cantidadMultas = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidadMultas() {
        return cantidadMultas;
    }

    public void setCantidadMultas(Integer cantidadMultas) {
        this.cantidadMultas = cantidadMultas;
    }
}
