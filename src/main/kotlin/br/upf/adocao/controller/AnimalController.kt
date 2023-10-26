package br.upf.adocao.controller

import br.upf.adocao.dtos.AnimalDTO
import br.upf.adocao.dtos.AnimalResponseDTO
import br.upf.adocao.service.AnimalService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/adocao")
class AnimalController(val service: AnimalService) {

    @GetMapping
    fun listar(@RequestParam(required = false) nomeAnimal: String?,
               @PageableDefault(size=10) paginacao: Pageable): Page<AnimalResponseDTO> {
        return service.listar(nomeAnimal, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): AnimalResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid animal: AnimalDTO,
                  uriBuilder: UriComponentsBuilder): ResponseEntity<AnimalResponseDTO> {
        val animalResponse = service.cadastrar(animal)
        val uri = uriBuilder.path("/adocao/${animalResponse.id}").build().toUri()
        return  ResponseEntity.created(uri).body(animalResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long,
                  @RequestBody @Valid animal: AnimalDTO): AnimalResponseDTO {
        return service.atualizar(id, animal)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun daletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}