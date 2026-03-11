package com.likhith.joblisting.repository;

import com.likhith.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);

}
