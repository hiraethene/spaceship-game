package com.spaceshipgame;

import static org.junit.jupiter.api.Assertions.*;

class StarshipTest {
    private Starship ship;
    private Starship target;
    private Starbase base;
    private Sector sector;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Board board = new Board(2,2);
        Fleet fleet = new Fleet("Player1",0,0, sector);
        sector = new Sector("sector1", 0,0);
        ship = new Starship(fleet,30,10,10,100,sector);
        target = new Starship(fleet,30, 10, 10, 100, sector);
        base = new Starbase(fleet, 20,500, sector);

    }

    @org.junit.jupiter.api.Test
    void executeAttackShipReducesHealthAndCrew() {
        ship.executeAttackShip(target);
        assertTrue(target.getCurrentHealth() < 100, "Target health should decrease after attack");
        assertTrue(target.getCurrentCrew() < 10, "Target crew should decrease after attack");
    }

    @org.junit.jupiter.api.Test
    void executeAttackBaseReducesHealth() {
        ship.executeAttackBase(base);
        assertTrue(base.getCurrentHealth() < 500, "Target health should decrease after attack");
    }

}