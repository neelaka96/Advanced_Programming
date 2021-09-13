package com.jkwebservice.service.order.cotrollers;

import com.jkwebservice.service.core.RequestDTO;
import com.jkwebservice.service.models.Order;
import com.jkwebservice.service.models.OrderDetails;
import com.jkwebservice.service.models.User;
import com.jkwebservice.service.order.repos.OrderRepo;
import com.jkwebservice.service.user.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    private Integer finalResult;

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Long id) {

        Order order = new Order();

        Optional<Order> details = orderRepo.findById(id);
        if (details.isPresent()) {
            order = details.get();
        }

        return order;
    }

    @GetMapping("/order")
    public List<Order> getOrders() {

        return orderRepo.findAll();
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody RequestDTO<Order> data){

        Order order = new Order();
        order = data.getData();

        List<OrderDetails> orderDetails = null;
        if (order.getList() != null){
            orderDetails = order.getList();
        }

        if (orderDetails != null && orderDetails.size() > 0){
            for (int i=0; i < orderDetails.size(); i++){
                OrderDetails details = orderDetails.get(i);
                details.setOrder(order);
            }
        }

        order.setDate(new Date());

        orderRepo.save(order);

        return order;
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()){
            Order order1 = new Order();
            order1 = order.get();

            orderRepo.delete(order1);
        }

        return ResponseEntity.ok().build();
    }
}
