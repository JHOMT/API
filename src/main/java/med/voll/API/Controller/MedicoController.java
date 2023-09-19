package med.voll.API.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.API.domain.Direccion.DatosDireccion;
import med.voll.API.domain.Medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping // Ingresar información
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),medico.getDocumento(), medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping // listar información
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(Pageable paginacion) { // Se especifica la longitud de cada médico
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)); // Devuelve a los médicos de la base de datos con los datos del constructor
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new); // Devuelve a los médicos de la base de datos con los datos del constructor
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(
                new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),medico.getDocumento(), medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()
        )));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();// retorna 204 de eliminado
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornarDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),medico.getDocumento(), medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(), medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);// retorna 204 de eliminado
    }
    /*
        DELETE EN BASE DE DATOS
        @DeleteMapping("/{id}")
        @Transactional
        public void eliminarMedico(@PathVariable Long id){// eliminado de la base de datos permanentemente
            Medico medico = medicoRepository.getReferenceById(id);
            medicoRepository.delete(medico);
        }
    */

}
