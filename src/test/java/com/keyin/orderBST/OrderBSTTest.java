package com.keyin.orderBST;

import com.keyin.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

public class OrderBSTTest
{
    private OrderBST orderBST;

    @BeforeEach
    public void setup()
    {
        orderBST = new OrderBST();

        Order order1 = new Order(LocalDate.now(), 5, null, null);
        Order order2 = new Order(LocalDate.now(), 8, null, null);
        Order order3 = new Order(LocalDate.now(), 2, null, null);

        orderBST.insert(order1);
        orderBST.insert(order2);
        orderBST.insert(order3);
    }

    @Test
    public void emptyTree_ReturnsEmptyListAndNullForHighestLowest()
    {
        OrderBST emptyBST = new OrderBST();

        assertEquals(0, emptyBST.inorder().size());
        assertNull(null, emptyBST.findHighest());
        assertNull(null,emptyBST.findLowest());
    }

    @Test
    public void inorder_ReturnsOrdersSortedByPriority()
    {
        List<Order> result = orderBST.inorder();

        assertEquals(2, result.get(0).getPriorityLevel());
        assertEquals(5, result.get(1).getPriorityLevel());
        assertEquals(8, result.get(2).getPriorityLevel());
    }

    @Test
    public void findHighestAndLowest_ReturnsHighestAndLowestPriorityOrders()
    {
        assertEquals(8, orderBST.findHighest().getPriorityLevel());
        assertEquals(2, orderBST.findLowest().getPriorityLevel());
    }

    @Test
    public void insertDuplicates_DuplicatePrioritiesAdded()
    {
        Order duplicateOrder = new Order(LocalDate.now(), 5, null, null);

        orderBST.insert(duplicateOrder);
        List<Order> result = orderBST.inorder();

        assertEquals(4, result.size());
        assertEquals(2, result.get(0).getPriorityLevel());
        assertEquals(5, result.get(1).getPriorityLevel());
        assertEquals(5, result.get(2).getPriorityLevel());
        assertEquals(8, result.get(3).getPriorityLevel());
    }
}
