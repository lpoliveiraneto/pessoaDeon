package com.pessoaDeon.api.controller.usuarios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.UsuariosPendentesResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.model.usuario.UsuarioAnaliseRequest;
import com.pessoaDeon.domain.service.usuario.UsuarioAnaliseService;
import com.pessoaDeon.domain.service.usuario.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioAnaliseService analiseService;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/analise")
    public ResponseEntity<?> analiseUsuarioPorId(@RequestBody UsuarioAnaliseRequest usuarioRequest, HttpServletRequest request, @RequestParam(value = "status") Status status){
		analiseService.salvarUsuarioEmTabelaAnalise(usuarioRequest, request, status);
        return ResponseEntity.ok().build();
    }
	
	@PatchMapping("/cancelarAnalise/{idUsuario}")
	public ResponseEntity<?> mudaStatusUsuario(@PathVariable(value = "idUsuario") Integer idUsuario,
			@RequestBody Usuario usuarioRequest){
		Optional<Usuario> usuario = usuarioService.findById(idUsuario);
		if (usuario.isPresent() && usuario.get().getStatus().equals(Status.EA)) {
			usuarioService.mudaStatusUsuarioEmAnalise(usuario.get(), usuarioRequest.getStatus());
		}
		return ResponseEntity.ok("Analise cancelada!");
	}
	
	@PostMapping("/respostaValido")
	public void salvarRespostaUsuario(@RequestBody UsuarioAnaliseRequest usuarioRequest, @RequestParam(name = "id") Integer id){
		analiseService.aprovarUsuarioEmAnalise(usuarioRequest, id);
	}
	
	@PostMapping("/recusarUsuario")
	public void recusatRespostaUsuario(@RequestBody UsuarioAnaliseRequest usuarioRequest, @RequestParam(name = "id") Integer id, HttpServletRequest request){
		analiseService.recusarUsuarioEmAnalise(usuarioRequest, id, request);
	}
	
	@GetMapping("/listaUsuariosAnalise")
    public Page<UsuariosPendentesResponseDto> usuariosAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idUsuario",
            direction = Sort.Direction.ASC)Pageable pageable){
    	return usuarioService.getUsuariosAnalise(pageable);
    }
}
