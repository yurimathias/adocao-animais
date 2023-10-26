package br.upf.adocao.service

import br.upf.adocao.converters.AnimalConverter
import br.upf.adocao.dtos.AnimalDTO
import br.upf.adocao.dtos.AnimalResponseDTO
import br.upf.adocao.exceptions.NotFoundException
import br.upf.adocao.repository.AnimalRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private const val ERROR_MESSAGE = "Animal não encontrado!!!"

@Service
class AnimalService(private val repository: AnimalRepository,
                    val converter: AnimalConverter) {

    fun listar(nomeEvento: String?,
               paginacao: Pageable): Page<AnimalResponseDTO> {
        val eventos = if (nomeEvento == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByNome(nomeEvento, paginacao)
        }

        return eventos
            .map(converter::toEventoResponseDTO)
    }

    fun buscarPorId(id: Long): AnimalResponseDTO {
        val evento = repository.findById(id)
            .orElseThrow{ NotFoundException(ERROR_MESSAGE) }
        return converter.toEventoResponseDTO(evento)
    }

    fun cadastrar(dto: AnimalDTO): AnimalResponseDTO {
        return converter.toEventoResponseDTO(
            repository.save(converter.toEvento(dto)))
    }

    fun atualizar(id: Long, dto: AnimalDTO) : AnimalResponseDTO {
        val evento = repository.findById(id)
            .orElseThrow{ NotFoundException(ERROR_MESSAGE) }
            .copy(nome = dto.nome,
                dataNascimento = dto.dataNascimento,
                descricao = dto.descricao,
                status = dto.status
            )
        return converter.toEventoResponseDTO(
            repository.save(evento)
        )
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

}