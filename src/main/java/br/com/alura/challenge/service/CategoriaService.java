package br.com.alura.challenge.service;

import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Page<Categoria> listaCategoria(Pageable paginacao);

    Categoria verCategoria(Long id);

    Categoria adicionarCategoria (Categoria categoria);

    Categoria altualizarCategoria (Long id, Categoria categoria);

    void removerCategoria (Long id);

    Categoria findCategoriaById(long id);
}
