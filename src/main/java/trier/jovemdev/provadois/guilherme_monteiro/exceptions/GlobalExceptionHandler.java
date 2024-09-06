package trier.jovemdev.provadois.guilherme_monteiro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CampoInvalidoException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.CnpjExistenteException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EdicaoDoProdutoNaoPermitidaException;
import trier.jovemdev.provadois.guilherme_monteiro.exceptions.excessoes_personalizadas.EntidadeNaoEncontradaException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CnpjExistenteException.class)
    public ResponseEntity<MensagemExcessaoPersonalizada> handleCnpjExistenteException(CnpjExistenteException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMensagemExcessaoPersonalizada());
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<MensagemExcessaoPersonalizada> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMensagemExcessaoPersonalizada());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    public ResponseEntity<MensagemExcessaoPersonalizada> handleCampoInvalidoException(CampoInvalidoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMensagemExcessaoPersonalizada());
    }

    @ExceptionHandler(EdicaoDoProdutoNaoPermitidaException.class)
    public ResponseEntity<MensagemExcessaoPersonalizada> handleEdicaoDoEstoqueNaoPermitidaException(EdicaoDoProdutoNaoPermitidaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMensagemExcessaoPersonalizada());
    }
}
