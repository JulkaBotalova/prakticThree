package test;

import org.springframework.expression.ExpressionException;
import org.springframework.web.bind.annotation.*;
import test.entity.ChangeController;
import test.entity.Product;
import test.entity.ProductGroup;
import test.repositories.ChangeControllerRepository;
import test.repositories.ProductGroupRepository;
import test.repositories.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("ControllerAll")
public class RControllerProduct {

    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ChangeControllerRepository changeControllerRepository;

    private Random random = new Random();

    public RControllerProduct(ProductRepository productRepository, ProductGroupRepository productGroupRepository, ChangeControllerRepository changeControllerRepository) {
        this.productRepository = productRepository;
        this.productGroupRepository = productGroupRepository;
        this.changeControllerRepository = changeControllerRepository;
    }

    ////////PRODUCT///////////
    @GetMapping("/product")
    Iterable<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/product/{ProductId}")
    Optional<Product> getProductId(@PathVariable Integer ProductId){
        return productRepository.findById(ProductId);
    }

    @RequestMapping("/productCreate")
    Product createProduct(@RequestParam(name = "idProductGroup", defaultValue = "") Integer idProductGroup,
                          @RequestParam(name  = "name", defaultValue = "") String name,
                          @RequestParam(name  = "price", defaultValue = "") Integer price) {
        Product product = new Product();
        Optional<ProductGroup> maybeProductGroup  = productGroupRepository.findById(idProductGroup);
        ProductGroup  productGroup = maybeProductGroup
                .orElseThrow(() -> new ExpressionException(String.valueOf(idProductGroup)));

      //  productGroup.setProducts(new HashSet<Product>());
        product.setProductGroup(productGroup);
        product.setProductName(name);
        product.setProductPrice(price);
      //  productGroup.getProducts().add(product);
        product = productRepository.save(product);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("Product");
        changeController.setRecordChangedId(product.getId());
        changeController.setActionWithTable("Create");

        changeControllerRepository.save(changeController);

        return product;

    }

    @RequestMapping("/productUp")
    Product updateProduct(@RequestParam(name = "id", defaultValue = "") Integer id,
                          @RequestParam(name = "idProductGroup", defaultValue = "") Integer idProductGroup,
                          @RequestParam(name  = "name", defaultValue = "") String name,
                          @RequestParam(name  = "price", defaultValue = "") Integer price) {
        Product product1 = new Product();
        if (productRepository.findAll().size()<id){
            product1 = createProduct(idProductGroup,name ,price);
        }
        else{
            Optional<Product> maybeProduct  = productRepository.findById(id);
            Product product = maybeProduct
                    .orElseThrow(() -> new ExpressionException(String.valueOf(id)));
            product.setProductName(name);
            product.setProductPrice(price);
            product1 = product;
            return productRepository.save(product);
        }
        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("Product");
        changeController.setRecordChangedId(id);
        changeController.setActionWithTable("Update");

        changeControllerRepository.save(changeController);
        return product1;
    }

    @GetMapping("/productDel/{pGroupId}")
    Product deleteProduct(@PathVariable Integer pId) throws Exception {
        Product product = productRepository.findById(pId)
                .orElseThrow(() -> new ExpressionException(String.valueOf(pId)));
        productRepository.delete(product);

        ChangeController changeController = new ChangeController();
        changeController.setNameTableOfChanged("Product");
        changeController.setRecordChangedId(pId);
        changeController.setActionWithTable("Delete");

        changeControllerRepository.save(changeController);

        return  product;

    }
}
