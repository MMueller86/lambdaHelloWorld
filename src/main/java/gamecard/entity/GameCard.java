package gamecard.entity;

import gamecard.validations.annotations.SingleFieldValid;

/**
 * Created by mimo on 20.05.2017.
 */
public class GameCard {

    @SingleFieldValid(baseDigit = 1)
    private Integer ones;

    @SingleFieldValid(baseDigit = 2)
    private Integer twos;

    @SingleFieldValid(baseDigit = 3)
    private Integer thirds;

    @SingleFieldValid(baseDigit = 4)
    private Integer fourths;

    @SingleFieldValid(baseDigit = 5)
    private Integer fifths;

    @SingleFieldValid(baseDigit = 6)
    private Integer sixths;

    private Integer threeDoubles;

    private Integer fourDoubles;

    private Integer smallStreet;
    private Integer bigStreet;
    private Integer fullHouse;

    private Integer chance;
    private Integer kniffel;
    private Integer bonusKniffel;


}
