package br.upf.adocao.exceptions

class NotFoundException(override val message: String)
    : RuntimeException() {
}