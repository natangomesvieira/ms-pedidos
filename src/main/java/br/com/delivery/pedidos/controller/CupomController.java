package br.com.delivery.pedidos.controller;

import br.com.delivery.pedidos.dto.CupomRequestDTO;
import br.com.delivery.pedidos.enums.TipoDesconto;
import br.com.delivery.pedidos.model.Cupom;
import br.com.delivery.pedidos.service.CupomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cupom")
public class CupomController {

    private final CupomService cupomService;

    @PostMapping
    public ResponseEntity<Cupom> inserirCupom(@RequestBody CupomRequestDTO cupomRequestDTO) {
        Cupom cupom = cupomService.inserirCupom(cupomRequestDTO);
        return ResponseEntity.ok(cupom);
    }

    @GetMapping
    public ResponseEntity<List<Cupom>> listarCupons(){
        return ResponseEntity.ok(cupomService.listarCupons());
    }

}
