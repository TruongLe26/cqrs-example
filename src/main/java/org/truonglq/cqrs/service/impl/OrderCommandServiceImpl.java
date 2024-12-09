package org.truonglq.cqrs.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.truonglq.cqrs.entity.Product;
import org.truonglq.cqrs.entity.PurchaseOrder;
import org.truonglq.cqrs.entity.User;
import org.truonglq.cqrs.repository.ProductRepository;
import org.truonglq.cqrs.repository.PurchaseOrderRepository;
import org.truonglq.cqrs.repository.UserRepository;
import org.truonglq.cqrs.service.OrderCommandService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private static final long ORDER_CANCELLATION_WINDOW = 30;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    private List<User> users;
    private List<Product> products;

    public OrderCommandServiceImpl(UserRepository userRepository, ProductRepository productRepository, PurchaseOrderRepository purchaseOrderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @PostConstruct
    private void init() {
        users = userRepository.findAll();
        products = productRepository.findAll();
    }

    @Override
    public void createOrder(int userIndex, int productIndex) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(products.get(productIndex).getId());
        purchaseOrder.setUserId(users.get(userIndex).getId());
        purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void cancelOrder(long orderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with this id"));

        LocalDateTime orderDate = purchaseOrder.getOrderDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (Duration.between(orderDate, LocalDateTime.now()).toDays() <= ORDER_CANCELLATION_WINDOW) {
            purchaseOrderRepository.deleteById(orderId);
        } else {
            throw new IllegalArgumentException("Order can't be cancelled after 30 days");
        }
    }
}
