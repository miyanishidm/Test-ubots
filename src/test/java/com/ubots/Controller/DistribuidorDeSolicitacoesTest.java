package com.ubots.Controller;

import com.ubots.Entity.Solicitacao;
import com.ubots.Service.DistribuidorDeSolicitacoes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;


class DistribuidorDeSolicitacoesTest {

    @InjectMocks
    private DistribuidorDeSolicitacoes distribuidor;

    @Mock
    private KafkaTemplate<String, Solicitacao> kafkaTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        distribuidor.init();
    }

    @Test
    void shouldSendSolicitacaoToCorrectTopic() {
        Solicitacao solicitacao = new Solicitacao("problemas com cartão");
        distribuidor.distribuir(solicitacao);
        verify(kafkaTemplate).send("topico-cartoes", solicitacao);
    }

    @Test
    void shouldSendSolicitacaoToDefaultTopic() {
        Solicitacao solicitacao = new Solicitacao("assunto desconhecido");
        distribuidor.distribuir(solicitacao);
        verify(kafkaTemplate).send("topico-Outros", solicitacao);
    }

    @Test
    void shouldSendSolicitacaoToEmprestimoTopic() {
        Solicitacao solicitacao = new Solicitacao("contratação de empréstimo");
        distribuidor.distribuir(solicitacao);
        verify(kafkaTemplate).send("topico-emprestimos", solicitacao);
    }
}
