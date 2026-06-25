package it.intesys.codylab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return """
<html>
    <body>
        <h1>CodyLab 404 Error Page</h1>
        <p>ciao</p>
        <div>There was an unexpected error (type=Not Found, status=404).</div>
    </body>
</html>
                
                """;
    }
}
