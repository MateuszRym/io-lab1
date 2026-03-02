package library.service;

import library.model.Branch;
import library.repository.BookDao;
import library.repository.BranchDao;
import library.repository.mem.MemBookDao;
import library.repository.mem.MemBranchDao;
import library.service.impl.BranchServiceBean;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Let's find branches!");

        BranchDao branchDao = new MemBranchDao();
        BookDao bookDao = new MemBookDao();

        BranchService service = new BranchServiceBean(branchDao, bookDao);

        List<Branch> branches = service.getAllBranches();
        System.out.println(branches.size() + " branches found:");
        branches.forEach(System.out::println);
    }
}
