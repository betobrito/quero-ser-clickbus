# language: en
Feature: List places by name

    Background:
        Given that there is a registered place

    Scenario: 01 - list existing places by name
        Given Given that when calling the list places service by name with name "Pajuçara"
        Then should return a list with a place, with the characteristics below:
            | name                     | slug            | city         |
            | Pajuçara Beach           | /pajucara-beach | Maceió       |

    Scenario: 02 - list existing places by name without records in the database
        Given Given that when calling the list places service by name with name "Jatiúca"
        Then should return a empty list