package br.com.alura.challenge.controller;

import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")

public class VideoController {


    private VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping(value = "videos")
    public List<Video> listaVideos (){
        return videoService.listaVideos();
    }

    @GetMapping("videos/{id}")
    public Video verVideo(@PathVariable("id") Long id){
        return videoService.verVideo(id);
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
