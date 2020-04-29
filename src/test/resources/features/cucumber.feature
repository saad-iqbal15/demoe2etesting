Feature: Post functionality

  @Scenario1
  Scenario Outline: As a customer I want to create a new post.
    Given I hit API for creating new post
    When I send a post to be created with post id <post_id>, title <title> and content <content>
    Then I should be able to see my newly created post

    Examples:
    | post_id | title          | content          |
    | 12345   | New post title | New post content |
    | new_ID  | Post title     | This is content  |
