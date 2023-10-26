package br.upf.adocao.exceptions

import br.upf.adocao.dtos.ErrorResponseDTO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        val errorMessage = hashMapOf<String, String>()
        exception.bindingResult.fieldErrors.forEach {
            errorMessage[it.field] = it.defaultMessage ?: "Erro não identificado!"
        }
        return ErrorResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handlerValidationErrorKotlin(
        exception: HttpMessageNotReadableException,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message ?: "Error não identificado!",
            path = request.servletPath
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handlerInternalServerError(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorResponseDTO {
        return ErrorResponseDTO(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message ?: "Erro desconhecido!",
            path = request.servletPath
        )
    }

}