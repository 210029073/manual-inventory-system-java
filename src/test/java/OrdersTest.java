import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.manual.model.OrderCollections;
import com.manual.model.Order;
import com.manual.orders.OrdersController;

import java.util.Date;

/**
 * @author Victory Mpokosa
 * Test all the order operations
 * */
public class OrdersTest {
    private OrderCollections oc;
    private Order ord;

    private OrdersController ctr;

    @BeforeEach
    public void setup(){
        oc = new OrderCollections();
        ord = new Order(1,1,3,300000,new Date(),new Date(),false);
    }

    @Test
    public void loadOrder() throws Exception{
        Assertions.assertNotNull(oc,"Order collection null");
        Assertions.assertNotNull(oc.getOrders(),"Orders list empty");
        Assertions.assertNotNull(oc.getPastOrders(),"Orders haven't full-filled");
        oc.getPendingOrders();
        //Assertions.assertEquals(oc.getPastOrders().size());
    }

    @Test
    public void updateOrder() throws Exception{
        oc.updateOrders(ord,true);
    }

    @Test
    public void orderTypeTest(){
        OrderCollections oc = new OrderCollections();
        for (Order o :oc.getOrders()){
            // Assertions.a
        }
    }

    @Test
    public void updateNull() throws Exception{
        try{
            // ord = new Order(1,1,2,30000.0F,new Date(),new Date(),false);
            oc.updateOrders(new Order(),false);

        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void loadTest(){
        ctr.load();
    }
}
