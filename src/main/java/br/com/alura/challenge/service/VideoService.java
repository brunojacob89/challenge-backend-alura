package br.com.alura.challenge.service;

import br.com.alura.challenge.controller.dto.VideoDto;
import br.com.alura.challenge.controller.form.VideoForm;
import br.com.alura.challenge.model.Video;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VideoService {

    List<VideoDto> listaVideos();

    List<VideoDto> listaVideoPorNome(String titulo);
    VideoDto verVideo(Long id);

    VideoDto adicionarVideo (VideoForm video);

    VideoDto atualizarVideo (Long id, VideoForm video);

    void removerVideo (Long id);

    List<VideoDto> videoPorCategoria(Long categoriaId);
}
