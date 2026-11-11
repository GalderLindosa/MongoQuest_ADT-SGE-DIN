/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.util.HashMap;

/**
 *
 * @author Unai.Ibarguren
 */
public interface WithCart {
    HashMap<Product, Integer> getCart();
    void setCart(Product p, Integer cant);
}
