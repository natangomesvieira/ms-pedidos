package br.com.delivery.pedidos.service;

import br.com.delivery.pedidos.dto.CupomRequestDTO;
import br.com.delivery.pedidos.model.Cupom;
import br.com.delivery.pedidos.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupomService {

    private final CupomRepository cupomRepository;

    public Cupom inserirCupom(CupomRequestDTO cupomRequestDTO){
        Cupom cupom = new Cupom();
        cupom.setCodigo(cupomRequestDTO.getCodigo());
        cupom.setValor(cupomRequestDTO.getValor());
        cupom.setValidade(cupomRequestDTO.getValidade());
        cupom.setTipo(cupomRequestDTO.getTipo());

        return cupomRepository.save(cupom);
    }

    public List<Cupom> listarCupons(){
        return cupomRepository.findAll();
    }

}
