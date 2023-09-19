package med.voll.API.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.API.domain.Paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }
    @GetMapping
    public Page<DatosListadoPaciente> listadoPacientes(Pageable paginacion){
        return pacienteRepository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new);
    }
    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void desactivarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
    }
}
