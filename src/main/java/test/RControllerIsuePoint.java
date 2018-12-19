package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.ChangeController;
import test.entity.IsuePoint;
import test.repositories.ChangeControllerRepository;
import test.repositories.IsuePointRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ControllerAll")
public class RControllerIsuePoint {
    ////////ISUEPOINT////////////

    private final IsuePointRepository isuePointRepository;
    private final ChangeControllerRepository changeControllerRepository;

    @Autowired
    public RControllerIsuePoint(IsuePointRepository isuePointRepository, ChangeControllerRepository changeControllerRepository) {
        this.isuePointRepository = isuePointRepository;
        this.changeControllerRepository = changeControllerRepository;
    }

    @GetMapping("/isuepoints")
    Iterable<IsuePoint> getAllIsuePoints() {
        return (List<IsuePoint>) isuePointRepository.findAll();
    }

    @GetMapping("/isuepoints/{ipId}")
    Optional<IsuePoint> getIPId(@PathVariable Integer ipId){
        return isuePointRepository.findById(ipId);
    }


    @GetMapping("/isuepointsCreate")
    IsuePoint createIsuePoint( @RequestParam(name  = "name", defaultValue = "") String name,
                               @RequestParam(name  = "address", defaultValue = "") String address) {

        IsuePoint isuePointCreate = new IsuePoint();
        isuePointCreate.setIsuePointName(name);
        isuePointCreate.setAddress(address);
        isuePointCreate = isuePointRepository.save(isuePointCreate);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("IsuePoint");
        changeController.setRecordChangedId(isuePointCreate.getId());
        changeController.setActionWithTable("Create");


        changeControllerRepository.save(changeController);

        return isuePointCreate;
    }


    @RequestMapping("/isuepointsUp")
    IsuePoint updateIsuePoint( @RequestParam(name = "id", defaultValue = "") Integer id,
                               @RequestParam(name  = "name", defaultValue = "") String name,
                               @RequestParam(name  = "address", defaultValue = "") String address) {
        IsuePoint issuePoint = new IsuePoint();
        if (isuePointRepository.findAll().size()<id){
            issuePoint = createIsuePoint(name, address);
        }
        else{
            Optional<IsuePoint> maybeIsuePoint= isuePointRepository.findById(id);
            IsuePoint isuePoint = maybeIsuePoint
                    .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
            isuePoint.setAddress(address);;
            isuePoint.setIsuePointName(name);
            issuePoint = isuePoint;
            isuePointRepository.save(issuePoint);

            ChangeController changeController = new ChangeController();
            changeController.setNameTableOfChanged("IsuePoint");
            changeController.setRecordChangedId(id);
            changeController.setActionWithTable("Update");

            changeControllerRepository.save(changeController);
        }

        return issuePoint;
    }

    @GetMapping("/isuepointsDel/{ipId}")
    IsuePoint deleteIsuePoint(@PathVariable Integer ipId) throws Exception {

        IsuePoint isuePoint = isuePointRepository.findById(ipId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(ipId)));
        isuePointRepository.delete(isuePoint);


        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("IsuePoint");
        changeController.setRecordChangedId(ipId);
        changeController.setActionWithTable("Delete");
        changeControllerRepository.save(changeController);

        return isuePoint;
    }
}
