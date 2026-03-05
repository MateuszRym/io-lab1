package library;

import jakarta.annotation.PostConstruct;
import library.model.Branch;
import library.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class LibraryComponent implements CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {

    private final BranchService branchService;

    public LibraryComponent(BranchService branchService) {this.branchService = branchService;}

    @PostConstruct
    void init() {
        List<Branch> branches = branchService.getAllBranches();
        System.out.println(branches.size() + " branches found:");
        branches.forEach(System.out::println);

    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Program arguments: {}", args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Context refreshed event");
        List<Branch> branches = branchService.getAllBranches();
        log.info("Found {} branches", branches.size());
        branches.forEach(b -> log.info("Branch: {}", b));
    }

    @EventListener
    public void eventListener(ContextRefreshedEvent event) {
        log.info("Context refreshed event (from event listener)");
    }
}
