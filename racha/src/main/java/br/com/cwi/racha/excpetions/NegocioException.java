package br.com.cwi.racha.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NegocioException extends ResponseStatusException {

    public NegocioException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
