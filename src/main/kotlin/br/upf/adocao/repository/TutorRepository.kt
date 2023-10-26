package br.upf.adocao.repository

import br.upf.adocao.model.Tutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TutorRepository: JpaRepository<Tutor, Long> {
}