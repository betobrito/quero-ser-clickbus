# language: en
Feature: Edit Place

    Background:
        Given that there is a registered place

    Scenario: 01 - edit place
        Given Since you should edit a place with the following information: id "1", name "Praia da Ponta Verde", slug "/praia-ponta-verde" and city "Maceió"
        Then should return a place with id "1", name "Praia da Ponta Verde", slug "/praia-ponta-verde" and city "Maceió"

    Scenario: 02 - edit place for non existent place
        Given Since you should edit a place with the following information: id "2", name "Praia da Ponta Verde", slug "/praia-ponta-verde" and city "Maceió"
        Then should return an error with status not found