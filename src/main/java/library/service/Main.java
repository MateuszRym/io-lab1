package library.service;

import library.model.Branch;
import library.repository.BookDao;
import library.repository.BranchDao;
import library.repository.mem.MemBookDao;
import library.repository.mem.MemBranchDao;
import library.service.impl.BranchServiceBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Let's find branches!");
        ApplicationContext context = new AnnotationConfigApplicationContext("library");
        BranchService service = context.getBean(BranchService.class);
        //uzywamy kontekstu do pobrania beana typu interfejsowego CinemaService
        // service use
        List<Branch> cinemas = service.getAllBranches();
        System.out.println(cinemas.size() + " cinemas found:");
        cinemas.forEach(System.out::println);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
