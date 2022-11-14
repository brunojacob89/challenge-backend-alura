package br.com.alura.challenge.controller.form;

import br.com.alura.challenge.exceptionhandler.NotFoundException;
import br.com.alura.challenge.model.Categoria;
import br.com.alura.challenge.model.Video;
import br.com.alura.challenge.repository.CategoriaRepository;
import br.com.alura.challenge.repository.VideoRepository;
import br.com.alura.challenge.service.CategoriaService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class VideoForm {

    @Autowired
    private CategoriaService categoriaService;

    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    @URL(message = "Não é uma url")
    private String url;

    private Long idCategoria;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getIdcategoria() {
        return idCategoria;
    }

    public void setIdcategoria(Long idcategoria) {
        idCategoria = idcategoria;
    }

    public Video converter (CategoriaRepository categoriaRepository){
        Optional<Categoria> categoriaId = categoriaRepository.findById(idCategoria);
        if(categoriaId.isPresent()){
            return new Video(titulo,descricao,url,categoriaId.get());
        }
        else{
            return new Video(titulo, descricao, url, categoriaService.findCategoriaById(1));
        }
    }

    public Video atualizar(Long id, VideoRepository videoRepository){
        Video video = videoRepository.getReferenceById(id);

        video.setTitulo(this.titulo);
        video.setDescricao(this.descricao);
        video.setUrl(this.url);
        video.setCategoria(new Categoria(idCategoria));

        return video;
    }

}
