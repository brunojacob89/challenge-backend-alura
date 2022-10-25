package br.com.alura.challenge.service;

import br.com.alura.challenge.model.Video;

import java.util.List;

public interface VideoService {

    List<Video> listaVideos();

    Video verVideo(Long id);

    Video adicionarVideo ();

    Video alterarVideo ();

    Video removerVideo ();
}
