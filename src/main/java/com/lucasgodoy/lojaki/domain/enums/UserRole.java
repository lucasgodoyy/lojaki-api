package com.lucasgodoy.lojaki.domain.enums;

/**
 * Represents the role of a user in the system.
 */
public enum UserRole {
    ADMIN,      // Administrator with full permissions
    CUSTOMER,   // Customer who can place orders
    STAFF       // Employee managing orders or inventory
}