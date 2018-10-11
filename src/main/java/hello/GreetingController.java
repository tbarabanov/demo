package hello;

import hello.exception.GreetingNotFoundException;
import hello.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.UUID.randomUUID;

@Controller
public class GreetingController {

    private final ConcurrentMap<String, Greeting> greetings = new ConcurrentHashMap<>();

    @GetMapping("/greeting")
    @ResponseBody
    public Map<String, Greeting> read() {
        return greetings;
    }

    @PostMapping("/greeting")
    @ResponseBody
    public Greeting create(@RequestBody Greeting greeting) {
        String id = randomUUID().toString();
        greeting.setId(id);
        greetings.put(id, greeting);
        return greeting;
    }

    @PutMapping("/greeting")
    @ResponseBody
    public void update(@RequestParam(name = "id") String id, @RequestBody Greeting greeting) {
        if (greetings.computeIfPresent(id, (key, value) -> {
            value.setContent(greeting.getContent());
            return value;
        }) == null) {
            throw new GreetingNotFoundException(String.format("Greeting with 'id'='%s' is not found!", id));
        }
    }

    @DeleteMapping
    @ResponseBody
    public void delete(@RequestParam(name = "id") String id) {
        if (greetings.remove(id) == null) {
            throw new GreetingNotFoundException(String.format("Greeting with 'id'='%s' is not found!", id));
        }
    }
}


