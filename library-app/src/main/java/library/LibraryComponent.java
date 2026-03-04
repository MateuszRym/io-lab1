package library;

import library.model.Branch;
import library.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class LibraryComponent {
    public LibraryComponent(BranchService branchService) {
        List<Branch> branches = branchService.getAllBranches();
        System.out.println(branches.size() + " branches found:");
        branches.forEach(System.out::println);

    }
}
