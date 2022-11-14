package br.com.alura.challenge.controller;

import br.com.alura.challenge.controller.dto.VideoDto;
import br.com.alura.challenge.controller.form.VideoForm;
import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<VideoDto> listaVideos (@RequestParam(required = false) String titulo,
                                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC,page = 0, size = 5) Pageable paginacao){
      if(titulo == null){
          return videoService.listaVideos(paginacao);
      }else {
        return videoService.listaVideoPorNome(titulo, paginacao);
      }
    }

    @GetMapping("videos/{id}")
    @Transactional
    public ResponseEntity<VideoDto>  verVideo(@PathVariable("id") Long id){
        return new ResponseEntity<>(videoService.verVideo(id), HttpStatus.OK);
    }

    @PostMapping(value = "videos")
    @Transactional
    public ResponseEntity<VideoDto> adicionarVideo(@Valid @RequestBody VideoForm video){
        if(video.getIdcategoria() == null){
            video.setIdcategoria(1L);
        }
        return new ResponseEntity<VideoDto>(videoService.adicionarVideo(video), HttpStatus.CREATED);
    }

    @PutMapping("videos/{id}")
    @Transactional
    public ResponseEntity<VideoDto> atualizaVideo(@PathVariable("id") Long id,@Valid @RequestBody VideoForm video){
        return new ResponseEntity<>(videoService.atualizarVideo(id,video), HttpStatus.OK);
    }

    @DeleteMapping("videos/{id}")
    @Transactional
    public ResponseEntity<?> removerVideo(@PathVariable("id") Long id){
        videoService.removerVideo(id);
       return ResponseEntity.ok("Video deletado com sucesso");
    }

    @GetMapping("categorias/{id}/videos")
    @Transactional
    public Page<VideoDto> listaVideosPorCategoria (@PathVariable("id") Long idCategoria,
                                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC,page = 0, size = 5) Pageable paginacao){
        return videoService.videoPorCategoria(idCategoria, paginacao);
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
