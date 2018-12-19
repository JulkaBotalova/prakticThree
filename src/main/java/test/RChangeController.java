package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.entity.ChangeController;
import test.repositories.ChangeControllerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ControllerAll")
public class RChangeController {

    private final ChangeControllerRepository changeControllerRepository;

    @Autowired
    public RChangeController(ChangeControllerRepository changeControllerRepository) {
        this.changeControllerRepository = changeControllerRepository;
    }

    @GetMapping("/changeControllerAll")
    Iterable<ChangeController> getAllChangeController() {
        return (List<ChangeController>) changeControllerRepository.findAll();
    }

    @GetMapping("/ichangeControllerId/{ipId}")
    Optional<ChangeController> getIPId(@PathVariable Integer ipId) {
        return changeControllerRepository.findById(ipId);
    }

}