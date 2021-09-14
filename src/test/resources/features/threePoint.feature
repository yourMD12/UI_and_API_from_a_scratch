Feature: Verify the info from all environment

  @wip @ui
  Scenario: Get the info from UI
    Given User logs in with "sbirdbj@fc2.com" and "asenorval"
    When User navigates to mySelf page
    Then User gets the UI info

    @wip @api
      Scenario: Get the info from API
      When User logs in BookIt API using "sbirdbj@fc2.com" and "asenorval"
      Then User gets API information

      @wip @db
        Scenario: Get the info from db
        When User sends a query to DataBase with "sbirdbj@fc2.com"
        Then User gets DataBase information

        @wip @db
        Scenario: Verify all environments
          Given User logs in with "sbirdbj@fc2.com" and "asenorval"
          When User navigates to mySelf page
          Then User gets the UI info
          When User logs in BookIt API using "sbirdbj@fc2.com" and "asenorval"
          Then User gets API information
          When User sends a query to DataBase with "sbirdbj@fc2.com"
          Then User gets DataBase information
          Then All information from environments must match

