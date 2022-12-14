package br.com.alura.challenge.service.impl;

import br.com.alura.challenge.controller.dto.VideoDto;
import br.com.alura.challenge.controller.form.VideoForm;
import br.com.alura.challenge.exceptionhandler.NotFoundException;
import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.CategoriaRepository;
import br.com.alura.challenge.repository.VideoRepository;
import br.com.alura.challenge.service.CategoriaService;
import br.com.alura.challenge.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaService categoriaService;


    public VideoServiceImpl() {
    }

    @Override
    public Page<VideoDto> listaVideos(Pageable paginacao) {
        Page<Video> videos = videoRepository.findAll(paginacao);
        return VideoDto.converter(videos);
    }

    @Override
    public Page<VideoDto> listaVideoPorNome(String titulo, Pageable paginacao) {
        Page<Video> videos = videoRepository.findByTitulo(titulo, paginacao);
        return VideoDto.converter(videos);
    }

    @Override
    public List<VideoDto> listaOsCincoPrimeiroVideos() {
        List<Video> videos = videoRepository.buscaOsCincoPrimeiroVideos();
        return VideoDto.converterList(videos);
    }

    @Override
    public VideoDto verVideo(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if(video.isPresent()){
            return new VideoDto(video.get());
        }else {
           throw new NotFoundException();
        }
    }

    @Override
    public VideoDto adicionarVideo(VideoForm video) {
        Video videoSalvo = video.converter(categoriaRepository);
        Video save = videoRepository.save(videoSalvo);
        return new VideoDto(save);
    }


    @Override
    public VideoDto atualizarVideo(Long id, VideoForm video) {
        Optional<Video> videoId = videoRepository.findById(id);

        if(videoId.isPresent()){
            Video videoAtualizado = video.atualizar(id, videoRepository);
            return new VideoDto(videoAtualizado);
        }
        throw new NotFoundException();
    }


    @Override
    public void removerVideo(Long id) {
        verVideo(id);
        videoRepository.deleteById(id);

    }

    @Override
    public Page<VideoDto> videoPorCategoria(Long categoriaId, Pageable paginacao) {
        Page<Video> listaVideos = videoRepository.buscaVideoPorCategoria(categoriaId, paginacao);
        return VideoDto.converter(listaVideos);
    }


}
