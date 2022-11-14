package br.com.alura.challenge.repository;

import br.com.alura.challenge.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query(value = "SELECT * FROM Video WHERE categoria_id = :categoriaId", nativeQuery = true)
    public Page<Video> buscaVideoPorCategoria(Long categoriaId, Pageable paginacao);

    @Query("SELECT v FROM Video v WHERE v.titulo LIKE %?1%")
    Page<Video> findByTitulo(String titulo, Pageable paginacao);
}
