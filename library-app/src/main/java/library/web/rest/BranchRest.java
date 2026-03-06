package library.web.rest;

import library.model.Branch;
import library.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BranchRest {
    private final BranchService branchService;

    @GetMapping("/branches")
    List<Branch> getBranches() {
        log.info("searching all cinemas");
        List<Branch> branches = branchService.getAllBranches();
        log.info("Found {} branches", branches.size());
        return branches;
    }
}
