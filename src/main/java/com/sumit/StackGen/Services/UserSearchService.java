package com.sumit.StackGen.Services;

import com.sumit.StackGen.DTO.Member.UserNameSearch;
import com.sumit.StackGen.Entities.UserSearchDocument;

import java.util.List;

public interface UserSearchService {
    public List<UserNameSearch> search(String query);
}
