package med.voll.API.domain.Medico;

import jakarta.validation.constraints.NotNull;
import med.voll.API.domain.Direccion.DatosDireccion;

public record DatosActualizarMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
