package org.fastcampus.post.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content, PostPublicationState state){
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(Long id, User author, String content){
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    public Post(Long id, User author, Content content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
    }

    public Post(Long id, User author, Content content, PostPublicationState state) {
        if(author == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user){
        if (this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.likeCount.increase();
    }

    public void unlike(User user){
        this.likeCount.decrease();
    }

    public void updatePost(User user, String updatedContent, PostPublicationState state){
        if( !this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updatedContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public Content getContentObject(){
        return content;
    }

    public String getContent() {
        return content.getContentText();
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

}
