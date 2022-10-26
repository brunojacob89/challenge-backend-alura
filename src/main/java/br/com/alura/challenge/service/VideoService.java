package br.com.alura.challenge.service;

import br.com.alura.challenge.model.Video;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VideoService {

    List<Video> listaVideos();

    Video verVideo(Long id);

    Video adicionarVideo (Video video);

    Video altualizarVideo (Long id, Video video);

    void removerVideo (Long id);
}
