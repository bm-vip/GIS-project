package se.dzm.electricvehiclechargingstationmanagement.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        String errorMessage = ex.getMessage();
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getRequestURI();
        // Check if the error message contains information about the unique constraint
        if (errorMessage.contains("uc_tbl_role_role")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Role is already in use."), status);
        } else if (errorMessage.contains("uc_tbl_user_email")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Email is already in use."), status);
        } else if (errorMessage.contains("uc_tbl_user_user_name")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Username is already in use."), status);
        } else if (errorMessage.contains("uc_tbl_user_uid")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Uid is already in use."), status);
        }

        else if (errorMessage.contains("fk_tbl_user_rol_on_role_id")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Role.id is already in use in another relations, first remove the relations then try again."), status);
        } else if (errorMessage.contains("fk_tbl_user_rol_on_user_id")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "User.id is already in use in another relations, first remove the relations then try again."), status);
        } else if (errorMessage.contains("fk_tbl_user_on_parent_id")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Parent.id is already in use in another relations, first remove the relations then try again."), status);
        } else if (errorMessage.contains("fk_tbl_parent_company_id")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Parent.id is already in use in another relations, first remove the relations then try again."), status);
        } else if (errorMessage.contains("fk_tbl_station_on_company")) {
            return new ResponseEntity<>(new ErrorResponse(status, path, "Station.id is already in use in another relations, first remove the relations then try again."), status);
        }

        // If the specific constraint is not identified, return a generic error message
        return new ResponseEntity<>(new ErrorResponse(status,path,"constraint violation occurred." + ex.getMessage()), status);
    }

    @ExceptionHandler({javax.validation.ConstraintViolationException.class, ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException(javax.validation.ConstraintViolationException exception, HttpServletRequest request) {
        String details = exception.getConstraintViolations().stream()
                .map(m -> String.format("{%s %s %s}", m.getRootBeanClass().getName(), m.getPropertyPath(), m.getMessage()))
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, request.getRequestURI(), details));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, request.getRequestURI(), errors));
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex, HttpServletRequest request) {
        return new ResponseEntity<>(ex.toErrorResponse(request.getRequestURI()), ex.getHttpStatus());
    }
}
