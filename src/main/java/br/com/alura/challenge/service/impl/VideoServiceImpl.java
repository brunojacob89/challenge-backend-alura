package br.com.alura.challenge.service.impl;

import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.VideoRepository;
import br.com.alura.challenge.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return videoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Video não encontrado");
                });
    }

    @Override
    public Video adicionarVideo() {
        return null;
    }

    @Override
    public Video alterarVideo() {
        return null;
    }

    @Override
    public Video removerVideo() {
        return null;
    }
}
