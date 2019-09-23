# language: en
Feature: Find Specific Place

    Background:
        Given that there is a registered place

    Scenario: 01 - search for existing place
        Given that the place entered has id "1"
        Then should return a place named "Pajuçara Beach"

    Scenario: 02 - search for non existent place
        Given that the place entered has id "2"
        Then should return an error with status not found