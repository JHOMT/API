package med.voll.API.domain.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.API.domain.Direccion.DatosDireccion;

public record DatosActualizarPaciente(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
