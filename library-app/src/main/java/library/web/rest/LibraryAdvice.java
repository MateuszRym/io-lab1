package library.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "library.web.rest")
@RequiredArgsConstructor
@Slf4j
public class LibraryAdvice {
    private final BranchValidator branchValidator;
    private final BookValidator bookValidator;

    @InitBinder("branch")
    void initBinderForBranch(WebDataBinder binder) {binder.addValidators(branchValidator);}

    @InitBinder("bookDTO")
    void initBinderForBook(WebDataBinder binder) {binder.addValidators(bookValidator);}

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(Exception e) {
        log.error("unexpected error", e);
        return ResponseEntity.status(HttpStatus.LOOP_DETECTED).body(e.getMessage());
    }
}
