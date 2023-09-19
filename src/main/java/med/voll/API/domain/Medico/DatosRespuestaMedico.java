package med.voll.API.domain.Medico;

import med.voll.API.domain.Direccion.DatosDireccion;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {
}
