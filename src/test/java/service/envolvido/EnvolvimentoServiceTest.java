package service.envolvido;

import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;
import com.pessoaDeon.domain.repository.envolvido.EnvolvimentoRepository;
import com.pessoaDeon.domain.service.envolvido.EnvolvimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AssertionErrors;

import java.util.Optional;

public class EnvolvimentoServiceTest {

    @InjectMocks
    private EnvolvimentoService envolvimentoService;

    @Mock
    private EnvolvimentoRepository envolvimentoRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testarSalvarEnvolvimento(){

        Envolvimento envolvimento = new Envolvimento();
        Envolvido envolvidoMock = Mockito.mock(Envolvido.class);
        NaturezaBo naturezaBoMock = Mockito.mock(NaturezaBo.class);
        TipoParticipacao tipoParticipacaomock = Mockito.mock(TipoParticipacao.class);

        when(envolvimentoRepository.save(envolvimento)).thenReturn(envolvimento);

        envolvimentoService.salvarEnvolvimento(envolvidoMock, naturezaBoMock, tipoParticipacaomock);
    }
    @Test
    public void testBuscarEnvolvimentoComIdExistente(){
        Integer idSimulado = 1;
        Envolvimento envolvimentoSimulado = new Envolvimento();
        envolvimentoSimulado.setIdEnvolvimento(idSimulado);

        when(envolvimentoRepository.findById(idSimulado)).thenReturn(java.util.Optional.of(envolvimentoSimulado));

        Envolvimento resultado = envolvimentoService.buscarEnvolvimento(idSimulado).get();

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdEnvolvimento());
    }

    @Test
    public void testBuscarEnvolvimentoComIdInexistente(){
        Integer idInexistente = 99;

        when(envolvimentoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        Optional<Envolvimento> resultado = envolvimentoService.buscarEnvolvimento(idInexistente);

        assertNotNull(resultado);
    }

}
