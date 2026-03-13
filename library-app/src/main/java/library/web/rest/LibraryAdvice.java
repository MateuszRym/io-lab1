package library.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
@RequiredArgsConstructor
public class LibraryAdvice {
    private final BranchValidator validator;

    @InitBinder("branch")
    void initBinder(WebDataBinder binder) {binder.addValidators(validator);}
}
