package br.com.alura.challenge.service.impl;

import br.com.alura.challenge.exceptionhandler.NotFoundException;
import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.repository.CategoriaRepository;
import br.com.alura.challenge.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listaCategoria() {
      return categoriaRepository.findAll();
    }

    @Override
    public Categoria verCategoria(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new NotFoundException());
    }

    @Override
    public Categoria adicionarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria altualizarCategoria(Long id, Categoria novaCategoria) {
        verCategoria(id);
        Optional<Categoria> categoriaAntiga = categoriaRepository.findById(id);
        Categoria categoria = categoriaAntiga.get();
        categoria.setTitulo(novaCategoria.getTitulo());
        categoria.setCor(novaCategoria.getCor());

        Categoria categoriaSalva = adicionarCategoria(categoria);

        return categoriaSalva;
    }

    @Override
    public void removerCategoria(Long id) {
        verCategoria(id);
        categoriaRepository.deleteById(id);

    }

    @Override
    public Optional<Categoria> findCategoriaById(long id) {
        return categoriaRepository.findById(id);
    }
}
