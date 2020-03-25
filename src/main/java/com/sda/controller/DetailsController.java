package com.sda.controller;

import com.sda.controller.GeneralController;
import com.sda.entity.Customer;

/**
 * @author StanislavR
 */
public class DetailsController extends GeneralController {

    public DetailsController(Customer customer) {
        super(customer);
    }

    public DetailsController() {
    }
}
