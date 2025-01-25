package io.github.cursos.produtosapi.controller;

import io.github.cursos.produtosapi.model.Produto;
import io.github.cursos.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        System.out.println("Produto recebido: " + produto);

        produto.setId(UUID.randomUUID().toString());

        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id){
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id") String id,
                          @RequestBody Produto produto){
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}
