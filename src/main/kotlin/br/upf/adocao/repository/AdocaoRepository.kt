package br.upf.adocao.repository

import br.upf.adocao.model.Adocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdocaoRepository: JpaRepository<Adocao, Long> {
}