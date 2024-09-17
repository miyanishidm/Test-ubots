package com.ubots.Controller;


import com.ubots.Entity.Solicitacao;
import com.ubots.Service.DistribuidorDeSolicitacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private DistribuidorDeSolicitacoes distribuidor;

    @PostMapping
    public ResponseEntity<String> criarSolicitacao(@RequestBody Solicitacao solicitacao) {
        distribuidor.distribuir(solicitacao);
        return ResponseEntity.ok("Solicitação criada com sucesso");
    }

    @GetMapping("/atendimento}")
    public ResponseEntity<String> getFila(@PathVariable String tipoSolicitacao) {
        return ResponseEntity.ok(distribuidor.consume(tipoSolicitacao));
    }
}
