package med.voll.API.domain.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.API.domain.Direccion.DatosDireccion;
public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,7}") // acepta de 4 a 6 datos
        String documento,
        @NotBlank
        String consulta,
        @NotNull
        @Valid
        DatosDireccion direccion
) {
}
