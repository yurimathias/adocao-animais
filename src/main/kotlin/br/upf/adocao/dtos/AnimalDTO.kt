package br.upf.adocao.dtos

import br.upf.adocao.model.StatusAnimal
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class AnimalDTO(
    @field:NotBlank(message = "Animal sempre deve ter um nome!")
    val nome: String,
    @field:NotNull(message = "Animal deve ter uma data de nascimento")
    val dataNascimento: LocalDate,
    @field:NotBlank(message = "Animal deve possuir uma descricao")
    val descricao: String,
    val status: StatusAnimal
)
