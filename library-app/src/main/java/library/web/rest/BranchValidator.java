package library.web.rest;

import library.model.Branch;
import library.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BranchValidator implements Validator {
    private final BranchService branchService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Branch.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Branch validatedBranch = (Branch) target;
        boolean duplicated = branchService.getAllBranches().stream()
                .anyMatch(b -> b.getName().equals(validatedBranch.getName()));
        if (duplicated) {
            errors.rejectValue("name", "branch.name.duplicated");
        }
    }
}
