package angelomoreno.progetto_diciannovesima_settimana.exceptions;

import angelomoreno.progetto_diciannovesima_settimana.payloads.errors.ErrorsDTO;
import angelomoreno.progetto_diciannovesima_settimana.payloads.errors.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handlerBadRequest(BadRequestException exception) {
        if (exception.getErrorList() != null) {
            List<String> errorList = exception.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).toList()
            return new ErrorsWithListDTO(exception.getMessage(), new Date(), errorList);
        } else {
            return new ErrorsWithListDTO(exception.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleUnauthorized(UnauthorizedException exception){
        return new ErrorsDTO(exception.getMessage(), new Date());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsDTO handleAccessDenied(AccessDeniedException exception) {
        return new ErrorsDTO(exception.getMessage(), new Date());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException exception) {
        return new ErrorsDTO(exception.getMessage(), new Date());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGeneric(Exception exception) {
        exception.printStackTrace();
        return new ErrorsDTO("Errore del server...", new Date());
    }
}
