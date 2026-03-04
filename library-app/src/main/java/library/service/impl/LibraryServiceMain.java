package library.service.impl;

import library.model.Branch;
import library.service.BranchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class LibraryServiceMain {
    public static void main(String[] args) {
        System.out.println("Let's find branches!");
        ApplicationContext context = new AnnotationConfigApplicationContext("library");
        BranchService service = context.getBean(BranchService.class);

        String foo = context.getBean(String.class);
        System.out.println("foo string: " + foo);
    }
}
