package com.sumit.StackGen.Entities;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@Document(indexName = "users")
@Data
public class UserSearchDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String username;

    public UserSearchDocument() {

    }

    public UserSearchDocument(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
