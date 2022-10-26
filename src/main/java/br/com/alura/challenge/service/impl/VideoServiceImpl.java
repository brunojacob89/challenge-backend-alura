package br.com.alura.challenge.service.impl;

import br.com.alura.challenge.exceptionhandler.VideoNotFoundException;
import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.VideoRepository;
import br.com.alura.challenge.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> listaVideos() {

        return videoRepository.findAll();
    }

    @Override
    public Video verVideo(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.orElseThrow(() -> new VideoNotFoundException());
    }

    @Override
    public Video adicionarVideo(Video video) {
        Video videoSalvo = videoRepository.save(video);

        return videoSalvo;
    }

    @Override
    public Video altualizarVideo(Long id, Video novoVideo) {

        verVideo(id);
        Optional<Video> videoAntigo = videoRepository.findById(id);
        Video video = videoAntigo.get();
        video.setTitulo(novoVideo.getTitulo());
        video.setDescricao(novoVideo.getDescricao());
        video.setUrl(novoVideo.getUrl());

        Video videoSalvo = adicionarVideo(video);

        return videoSalvo;
    }


    @Override
    public void removerVideo(Long id) {
        verVideo(id);
        videoRepository.deleteById(id);

    }
}
