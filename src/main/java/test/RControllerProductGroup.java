package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.ChangeController;
import test.entity.ProductGroup;
import test.repositories.ChangeControllerRepository;
import test.repositories.ProductGroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ControllerAll")
public class RControllerProductGroup {

    private final ProductGroupRepository productGroupRepository;
    private final ChangeControllerRepository changeControllerRepository;

    public RControllerProductGroup(ProductGroupRepository productGroupRepository, ChangeControllerRepository changeControllerRepository) {
        this.productGroupRepository = productGroupRepository;
        this.changeControllerRepository = changeControllerRepository;
    }

    ////////PRODUCTGROUP///////////
    @GetMapping("/productgroup")
    Iterable<ProductGroup> getAllPGroup() {
        return (List<ProductGroup>) productGroupRepository.findAll();
    }

    @GetMapping("/productgroup/{pGroupId}")
    Optional<ProductGroup> getpGroupId(@PathVariable Integer pGroupId){
        return productGroupRepository.findById(pGroupId);
    }

    @GetMapping("/productGroupCreate")
    ProductGroup createProductGroup( @RequestParam(name  = "name", defaultValue = "") String name,
                                     @RequestParam(name  = "remark", defaultValue = "") String remark) {

        ProductGroup productGroup = new ProductGroup();
        productGroup.setProductGroupName(name);
        productGroup.setRemark(remark);
        productGroup = productGroupRepository.save(productGroup);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("ProductGroup");
        changeController.setRecordChangedId(productGroup.getId());
        changeController.setActionWithTable("Create");

        changeControllerRepository.save(changeController);

        return productGroup;
    }

    @RequestMapping("/productgroupUp")
    ProductGroup updateProductGroup(@RequestParam(name = "id", defaultValue = "") Integer id,
                                    @RequestParam(name  = "name", defaultValue = "") String name,
                                    @RequestParam(name  = "remark", defaultValue = "") String remark) {
        ProductGroup productGroup = new ProductGroup();
        if (productGroupRepository.findAll().size()<id){
            productGroup = createProductGroup(name, remark);
        }
        else{
            Optional<ProductGroup> maybeProduct  = productGroupRepository.findById(id);
            ProductGroup productGroup1 = maybeProduct
                    .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
            productGroup1.setRemark(remark);
            productGroup1.setProductGroupName(name);
            productGroup = productGroup1;

            ChangeController changeController = new ChangeController();
            changeController.setNameTableOfChanged("ProductGroup");
            changeController.setRecordChangedId(id);
            changeController.setActionWithTable("Update");

            changeControllerRepository.save(changeController);

            return productGroupRepository.save(productGroup);
        }
        return productGroup;
    }

    @GetMapping("/productGroupDel/{pGroupId}")
    ProductGroup deleteProductGroup(@PathVariable Integer pGroupId) throws Exception {
        ProductGroup productGroup = productGroupRepository.findById(pGroupId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(pGroupId)));
        productGroupRepository.delete(productGroup);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("ProductGroup");
        changeController.setRecordChangedId(pGroupId);
        changeController.setActionWithTable("Delete");

        changeControllerRepository.save(changeController);

        return  productGroup;
    }

}
