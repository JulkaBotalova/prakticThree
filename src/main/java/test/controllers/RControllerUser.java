package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.ChangeController;
import test.entity.User;
import test.repositories.ChangeControllerRepository;
import test.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ControllerAll")
public class RControllerUser {
    private final UserRepository userRepository;
    private final ChangeControllerRepository changeControllerRepository;


    public RControllerUser(UserRepository userRepository, ChangeControllerRepository changeControllerRepository) {
        this.userRepository = userRepository;
        this.changeControllerRepository = changeControllerRepository;
    }

    ////////USER////////////
    @GetMapping("/users")
    Iterable<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    Optional<User> getUserId(@PathVariable Integer userId){
        return userRepository.findById(userId);
    }


    @GetMapping("/userCreate")
    User createUser( @RequestParam(name  = "name", defaultValue = "") String name,
                     @RequestParam(name  = "phone", defaultValue = "") String phone) {

        User user = new User();
        user.setUsername(name);
        user.setUserphone(phone);
        user = userRepository.save(user);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("Users");
        changeController.setRecordChangedId(user.getId());
        changeController.setActionWithTable("Create");

        changeControllerRepository.save(changeController);

        return user;
    }


    @RequestMapping("/usersUp")
    User updateUser(@RequestParam(name = "id", defaultValue = "") Integer id,
                    @RequestParam(name  = "name", defaultValue = "") String name,
                    @RequestParam(name  = "phone", defaultValue = "") String phone) {
        User user1 = new User();
        if (userRepository.findAll().size()<id){
            user1 = createUser(name, phone);
        }
        else{
            Optional<User> maybeUser = userRepository.findById(id);
            User user = maybeUser
                    .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
            user.setUsername(name);
            user.setUserphone(phone);
            user1 = user;
            return userRepository.save(user);
        }

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("Users");
        changeController.setRecordChangedId(id);
        changeController.setActionWithTable("Update");

        changeControllerRepository.save(changeController);

        return user1;
    }

    @GetMapping("/usersDel/{userId}")
    User deleteUser(@PathVariable Integer userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(userId)));
        userRepository.delete(user);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("Users");
        changeController.setRecordChangedId(userId);
        changeController.setActionWithTable("Delete");

        changeControllerRepository.save(changeController);

        return user;
    }
}
