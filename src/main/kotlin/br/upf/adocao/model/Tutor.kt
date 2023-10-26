package br.upf.adocao.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Tutor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val cidade: String,
    val telefone: String,

    @OneToMany(mappedBy = "tutor")
    val adocoes: List<Adocao> = listOf()
)
