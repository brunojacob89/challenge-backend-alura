package br.com.alura.challenge.controller;

import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")

public class VideoController {


    @Autowired
    private VideoService videoService;



    @GetMapping(value = "videos")
    @Transactional
    public List<Video> listaVideos (){
        return videoService.listaVideos();
    }

    @GetMapping("videos/{id}")
    @Transactional
    public ResponseEntity<Video>  verVideo(@PathVariable("id") Long id){
        return new ResponseEntity<>(videoService.verVideo(id), HttpStatus.OK);
    }

    @PostMapping(value = "videos")
    @Transactional
    public ResponseEntity<Video> adicionarVideo(@Valid @RequestBody Video video){
        return new ResponseEntity<Video>(videoService.adicionarVideo(video), HttpStatus.CREATED);
    }

    @PutMapping("videos/{id}")
    @Transactional
    public ResponseEntity<Video> atualizaVideo(@PathVariable("id") Long id,@Valid @RequestBody Video video){
        return new ResponseEntity<>(videoService.altualizarVideo(id,video), HttpStatus.OK);
    }

    @DeleteMapping("videos/{id}")
    @Transactional
    public ResponseEntity<?> removerVideo(@PathVariable("id") Long id){
        videoService.removerVideo(id);
       return ResponseEntity.ok("Video deletado com sucesso");
    }

//    @Autowired
//    private VideoRepository videoRepository;
//
//    @GetMapping(value = "videos")
//    public ResponseEntity<List<Video>> listaVideos(){
//
//        List<Video> listaFilmes = videoRepository.findAll();
//
//        return new ResponseEntity<List<Video>>( listaFilmes, HttpStatus.OK);
//    }
}
