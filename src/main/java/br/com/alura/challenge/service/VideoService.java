package br.com.alura.challenge.service;

import br.com.alura.challenge.controller.dto.VideoDto;
import br.com.alura.challenge.controller.form.VideoForm;
import br.com.alura.challenge.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VideoService {

    Page<VideoDto> listaVideos(Pageable paginacao);


    Page<VideoDto> listaVideoPorNome(String titulo, Pageable paginacao);

    VideoDto verVideo(Long id);

    VideoDto adicionarVideo (VideoForm video);

    VideoDto atualizarVideo (Long id, VideoForm video);

    void removerVideo (Long id);

    Page<VideoDto> videoPorCategoria(Long categoriaId, Pageable paginacao);
}
