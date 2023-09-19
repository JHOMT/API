package med.voll.API.domain.Paciente;

public record DatosListadoPaciente(Long id, String nombre, String consulta, String documento, String email) {
    public DatosListadoPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getConsulta(), paciente.getDocumento(), paciente.getEmail());
    }
}
