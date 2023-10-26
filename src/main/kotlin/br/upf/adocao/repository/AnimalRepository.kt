package br.upf.adocao.repository

import br.upf.adocao.model.Animal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository: JpaRepository<Animal, Long> {
    fun findByNome(nomeAnimal: String, paginacao: Pageable): Page<Animal>

}