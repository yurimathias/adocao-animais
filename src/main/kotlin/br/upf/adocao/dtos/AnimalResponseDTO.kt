package br.upf.adocao.dtos

import br.upf.adocao.model.Adocao
import br.upf.adocao.model.StatusAnimal
import java.time.LocalDate

data class AnimalResponseDTO (
    val id: Long,
    val nome: String,
    val dataNascimento: LocalDate,
    val descricao: String,
    val status: StatusAnimal,
    val tutor: List<Adocao>
)