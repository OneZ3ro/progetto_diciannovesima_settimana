package angelomoreno.progetto_diciannovesima_settimana.payloads.errors;

import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;

public record ErrorsWithListDTO(String message, Date timestamp, List<ObjectError> errorList) {
}
