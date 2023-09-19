package med.voll.API.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.API.domain.Consulta.AgendaDeConsultaService;
import med.voll.API.domain.Consulta.DatosAgendarConsulta;
import med.voll.API.domain.Consulta.DatosDetallesConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        service.agendar(datos);
        return ResponseEntity.ok(new DatosDetallesConsulta(null, null, null, null));
    }
}
