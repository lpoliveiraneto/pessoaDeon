import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pessoaDeon.PessoaDeonApplication;
import com.pessoaDeon.domain.service.VerificacaoContaService;

@SpringBootTest(classes = PessoaDeonApplication.class)
class VerificationCodeExpirationCheckerTest {
	
	@Autowired
	private VerificacaoContaService contaService;

    @Test
    public void testTempoExpiracaoDentroDoLimite() {
        LocalDateTime dataExpiracao = LocalDateTime.now().plusMinutes(60); // Expira em 60 minutos
        Boolean resultado = contaService.calculaTempoExpiracaoCodigoVerificacaoConta(dataExpiracao);
        assertTrue(resultado);
    }

    @Test
    public void testTempoExpiracaoForaDoLimite() {
        LocalDateTime dataExpiracao = LocalDateTime.now().minusMinutes(30); // Expirou há 30 minutos
        Boolean resultado = contaService.calculaTempoExpiracaoCodigoVerificacaoConta(dataExpiracao);
        assertFalse(resultado);
    }

    @Test
    public void testTempoExpiracaoNoLimiteSuperior() {
        LocalDateTime dataExpiracao = LocalDateTime.now().plusMinutes(120); // Expira em exatamente 120 minutos
        Boolean resultado = contaService.calculaTempoExpiracaoCodigoVerificacaoConta(dataExpiracao);
        assertTrue(resultado);
    }

    @Test
    public void testTempoExpiracaoZero() {
        LocalDateTime dataExpiracao = LocalDateTime.now(); // Expira agora
        Boolean resultado = contaService.calculaTempoExpiracaoCodigoVerificacaoConta(dataExpiracao);
        assertFalse(resultado);
    }
}
