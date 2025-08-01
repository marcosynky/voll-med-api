package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Anotação que indica que esta classe vai tratar erros de forma global para todos os controladores
@RestControllerAdvice
public class TratadorDeErros {

    // Trata exceções do tipo EntityNotFoundException (erro 404 - Não encontrado)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();  // Retorna uma resposta 404
    }

    // Trata exceções do tipo MethodArgumentNotValidException (erro 400 - Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        // Obtém os erros de validação dos campos
        var erros = ex.getFieldErrors();
        // Retorna uma resposta 400 com a lista de erros de validação
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    // Trata exceções do tipo HttpMessageNotReadableException (erro 400 - Bad Request)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> tratarErro400(HttpMessageNotReadableException ex) {
        // Retorna uma resposta 400 com a mensagem de erro
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Trata exceções do tipo ValidacaoException (erro 400 - Bad Request), personalizada para regras de negócio
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {
        // Retorna uma resposta 400 com a mensagem da exceção
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Trata qualquer outra exceção genérica (erro 500 - Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro500(Exception ex) {
        // Retorna uma resposta 500 com a mensagem de erro da exceção
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    // Classe auxiliar para encapsular os dados de erro de validação
    private record DadosErroValidacao(String campo, String mensagem) {
        // Construtor que mapeia o erro do campo para um objeto DadosErroValidacao
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
