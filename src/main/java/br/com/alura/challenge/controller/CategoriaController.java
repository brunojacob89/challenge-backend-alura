package br.com.alura.challenge.controller;

import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Categoria> listaCategoria(){
        return categoriaService.listaCategoria();
    }

    @GetMapping("categorias/{id}")
    @Transactional
    public ResponseEntity<Categoria> verCategoria (@Valid @PathVariable Long id){
        return new ResponseEntity<>(categoriaService.verCategoria(id),HttpStatus.OK);
    }

    @PostMapping("categorias")
    @Transactional
    public ResponseEntity<Categoria> adicionaCategoria (@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.adicionarCategoria(categoria), HttpStatus.CREATED);
    }

    @PutMapping("categorias/{id}")
    @Transactional
    public ResponseEntity<Categoria> atualizaCategoria ( @PathVariable Long id,@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<Categoria>(categoriaService.altualizarCategoria(id, categoria), HttpStatus.OK);
    }

    @DeleteMapping("categorias/{id}")
    @Transactional
    public ResponseEntity<?> removeCategoria (@PathVariable Long id){
        categoriaService.removerCategoria(id);
        return ResponseEntity.ok("Categoria Deletada");
    }
}
