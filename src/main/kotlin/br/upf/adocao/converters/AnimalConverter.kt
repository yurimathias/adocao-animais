package br.upf.adocao.converters

import br.upf.adocao.dtos.AnimalDTO
import br.upf.adocao.dtos.AnimalResponseDTO
import br.upf.adocao.model.Animal
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AnimalConverter {

    fun toEvento(dto: AnimalDTO) : Animal {
        return Animal(
            nome = dto.nome,
            dataNascimento = dto.dataNascimento,
            dataEntrada = LocalDateTime.now(),
            dataSaida = LocalDateTime.now(),
            descricao = dto.descricao,
            status = dto.status
        )
    }

    fun toEventoResponseDTO(animal: Animal): AnimalResponseDTO {
        return AnimalResponseDTO(
            id = animal.id!!,
            nome = animal.nome,
            dataNascimento = animal.dataNascimento,
            descricao = animal.descricao,
            status = animal.status,
            tutor = animal.tutor
        )
    }

}