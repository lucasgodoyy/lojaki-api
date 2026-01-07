package com.lucasgodoy.lojaki.domain.user.model;

/**
 * Represents the role of a user in the system.
 */
public enum Role {
    ADMIN,      // Administrator with full permissions
    CUSTOMER,   // Customer who can place orders
    STAFF       // Employee managing orders or inventory
}