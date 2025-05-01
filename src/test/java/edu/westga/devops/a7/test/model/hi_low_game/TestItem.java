package edu.westga.devops.a7.test.model.hi_low_game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.devops.a7.model.Item;

public class TestItem {

    @Test
    public void testCreateItem() {
        Item item = new Item("Milk");
        assertEquals("Milk", item.getName());
        assertEquals(0, item.getQuantity());
    }

    @Test
    public void testToStringFormat() {
        Item item = new Item("Bread");
        assertEquals("Bread (x0)", item.toString());
    }
}