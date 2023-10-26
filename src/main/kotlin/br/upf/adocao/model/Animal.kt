package br.upf.adocao.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Animal(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val dataNascimento: LocalDate,
    val dataEntrada: LocalDateTime,
    val dataSaida: LocalDateTime,
    val descricao: String,
    @Enumerated(value = EnumType.STRING)
    val status: StatusAnimal,
    @OneToMany(mappedBy = "animal")
    val tutor: List<Adocao> = listOf()
)
