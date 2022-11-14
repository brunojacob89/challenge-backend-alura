package br.com.alura.challenge.controller;

import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.service.CategoriaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "categorias")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public Page<Categoria> listaCategoria(@PageableDefault(sort = "id", direction = Sort.Direction.ASC,page = 0, size = 5) Pageable paginacao){
        return categoriaService.listaCategoria(paginacao);
    }

    @GetMapping("categorias/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Categoria> verCategoria (@Valid @PathVariable Long id){
        return new ResponseEntity<>(categoriaService.verCategoria(id),HttpStatus.OK);
    }

    @PostMapping("categorias")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Categoria> adicionaCategoria (@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.adicionarCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("categorias/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Categoria> atualizaCategoria ( @PathVariable Long id,@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.altualizarCategoria(id, categoria), HttpStatus.OK);
    }

    @DeleteMapping("categorias/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> removeCategoria (@PathVariable Long id){
        categoriaService.removerCategoria(id);
        return ResponseEntity.ok("Categoria Deletada");
    }
}
