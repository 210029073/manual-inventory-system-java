import com.manual.exception.InvalidStockAmountException;
import com.manual.model.Product;
import com.manual.model.ProductCollections;
import org.junit.jupiter.api.*;

public class TestProduct {

    @Test
    public void assertNotNullIfProductIsFound() {
        //Assume that I wish to look at product 1 which is a bmw
        //m3 it should assume that the product brand is BMW
        //and return object is not null.
        //create a new product
        ProductCollections productCollections = new ProductCollections();
        Product product = new Product("M3", "BMW", "2011 BMW M3 E93",
                1200F, 1.2F, "Automatic", 12, "n/a.png", 20);
        Product searchedProduct = productCollections.showProduct(12);
        Assertions.assertNotNull(productCollections.showProduct(12));
        Assertions.assertEquals(product.getProductBrand(), searchedProduct.getProductBrand());
    }

    @Test
    public void assertIfProductStockIsNotValid() throws InvalidStockAmountException {
        Product product = new Product("M3", "BMW", "2011 BMW M3 E93",
                1200F, 1.2F, "Automatic", 12, "n/a.png", 20);
        ProductCollections productCollections = new ProductCollections();
        Assertions.assertFalse(productCollections.isQuantityValid(product.getQuantity()));
        Assertions.assertThrows(InvalidStockAmountException.class, () -> productCollections.isQuantityValid(product), "Must throw an InvalidStockAmountException if stock amount is less than 20.");
    }

    @Test
    public void assertIfProductStockIsValid() throws InvalidStockAmountException {
        Product product = new Product("M3", "BMW", "2011 BMW M3 E93",
                1200F, 1.2F, "Automatic", 20, "n/a.png", 20);
        ProductCollections productCollections = new ProductCollections();
        productCollections.isQuantityValid(product);
        Assertions.assertTrue(productCollections.isQuantityValid(product));
    }

    @Test
    public void assertIfProductStockIsValidAndThrowsException() throws InvalidStockAmountException {
        Product product = new Product("M3", "BMW", "2011 BMW M3 E93",
                1200F, 1.2F, "Automatic", 12, "n/a.png", 20);
        ProductCollections productCollections = new ProductCollections();
        Assertions.assertThrows(InvalidStockAmountException.class, () -> productCollections.isQuantityValid(product), "Must throw an InvalidStockAmountException if stock amount is less than 20.");
    }

    @Test
    public void assertNotNullIfProductRecordsAreNotEmpty() {
        ProductCollections productCollections = new ProductCollections();
        Assertions.assertNotNull(productCollections.getProducts(), "Product is null");
    }
}
