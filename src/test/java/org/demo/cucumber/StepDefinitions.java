package org.demo.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StepDefinitions {


    private String postUrl = "http://localhost:8086/post/";
    private String postId = "";
    private String post_content = "";
    private String post_title = "";

    @Given("I hit API for creating new post")
    public void i_create_a_new_post() {
       baseURI = postUrl;
        RequestSpecification request = RestAssured.given();
        Response restAssuredResponse = request.get();
        assertNotNull(restAssuredResponse);
    }

    @When("^I send a post to be created with post id (.*), title (.*) and content (.*)$")
    public void i_sending_post(String post_id, String title, String content) throws JSONException {
        baseURI = postUrl;
        RequestSpecification request = RestAssured.given();
        postId = post_id;
        post_content = content;
        post_title = title;

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", postId);
        requestBody.put("title", post_title);
        requestBody.put("content", post_content);
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response restAssuredResponse = request.post();
        //request.post().then().assertThat().statusCode(HttpStatus.OK.value());
        assertEquals(HttpStatus.OK.value(), restAssuredResponse.statusCode());
    }

    @Then("I should be able to see my newly created post")
    public void i_should_see_my_newly_created_post() {

        baseURI = postUrl;

        RequestSpecification request = RestAssured.given();
        Response response = request.get(postId);
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertEquals(post_content, jsonPathEvaluator.get("content"));
        assertEquals(post_title, jsonPathEvaluator.get("title"));
        request.delete(postId);

    }
}
