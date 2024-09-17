package com.ubots.Service;

import com.ubots.Entity.Solicitacao;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DistribuidorDeSolicitacoes {

    @Autowired
    private KafkaTemplate<String, Solicitacao> kafkaTemplate;

    private Map<String, String> mapaTopicos = new HashMap<>();

    @PostConstruct
    public void init() {
        mapaTopicos.put("problemas com cartão", "topico-cartoes");
        mapaTopicos.put("contratação de empréstimo", "topico-emprestimos");
        mapaTopicos.put("Outros Assuntos", "topico-Outros");
    }

    public void distribuir(Solicitacao solicitacao) {
        String topico = getFila(solicitacao.getAssunto());
        kafkaTemplate.send(topico, solicitacao);
    }

    public String getFila(String assunto) {
        switch (assunto.toLowerCase()) {
            case "problemas com cartão":
                return "topico-cartoes";
            case "contratação de empréstimo":
                return "topico-emprestimos";
            default:
                return "topico-Outros";
        }
    }

    @KafkaListener(topics = {"topico-cartoes", "topico-emprestimos", "topico-Outros"})
    public String consume(String message) {
        if (message.contains("topico-cartoes")) {
            return "Processando mensagem de cartão";
        } else if (message.contains("topico-emprestimos")) {
            return "Processando mensagem de empréstimo";
        } else {
            return "Processando outra mensagem";
        }
    }
}
