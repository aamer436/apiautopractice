Feature: Testing get call for a git api

@Testkmp
Scenario: Fetch the response of git api
Given user launches the api using restassuredgit
When conditionAgit
Then resultAgit
And resultBgit