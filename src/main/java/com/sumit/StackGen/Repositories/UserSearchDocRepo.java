package com.sumit.StackGen.Repositories;

import com.sumit.StackGen.Entities.UserSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserSearchDocRepo extends ElasticsearchRepository<UserSearchDocument, Long> {
    List<UserSearchDocument> findByUsernameContaining(String username);
}
