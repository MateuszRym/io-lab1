package library.config;

import library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VodInfoContributor implements InfoContributor {
    private final BookService bookService;
    @Override
    public void contribute(Info.Builder builder){
        builder.withDetail("books", bookService.getAllBooks().size());
    }
}
