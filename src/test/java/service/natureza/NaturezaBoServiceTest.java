package service.natureza;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;
import com.pessoaDeon.domain.model.natureza.NaturezaDeon;
import com.pessoaDeon.domain.repository.natureza.NaturezaBoRepository;
import com.pessoaDeon.domain.service.natureza.NaturezaBoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
public class NaturezaBoServiceTest {
    @InjectMocks
    private NaturezaBoService naturezaBoService;

    @Mock
    private NaturezaBoRepository naturezaBoRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testarSalvarNaturezaBo(){
        NaturezaBo naturezaBo = new NaturezaBo();
        BoDeon boDeonMock  = Mockito.mock(BoDeon.class);
        NaturezaDeon naturezaDeonMock = Mockito.mock(NaturezaDeon.class);

        when(naturezaBoRepository.save(naturezaBo)).thenReturn(naturezaBo);

        naturezaBoService.salvarNaturezaBo(boDeonMock, naturezaDeonMock);
    }

    @Test
    public void testBuscarEnvolvimentoComIdExistente(){
        Integer idSimulado = 1;
        NaturezaBo naturezaBoSimulado = new NaturezaBo();
        naturezaBoSimulado.setId(idSimulado);

        when(naturezaBoRepository.findById(idSimulado)).thenReturn(java.util.Optional.of(naturezaBoSimulado));

        NaturezaBo resultado = naturezaBoService.buscarNaturezaBo(idSimulado).get();

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getId());
    }
    @Test
    public void testBuscarNaturezaBoComIdInexistente(){
        Integer idInexistente = 99;

        when(naturezaBoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        Optional<NaturezaBo> resultado = naturezaBoService.buscarNaturezaBo(idInexistente);

        assertNotNull(resultado);
    }
}
