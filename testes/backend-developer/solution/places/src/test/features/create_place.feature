# language: en
Feature: Create Place

    Scenario: 01 - create place
        Given Since you should register a place with the following information: name "Praia da Ponta Verde", slug "/praia-ponta-verde" and city "Maceió"
        Then should return a place with id "1" and name "Praia da Ponta Verde"