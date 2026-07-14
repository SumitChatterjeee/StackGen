package com.sumit.StackGen.Services.IMPLEMENTATIONS;

import com.sumit.StackGen.DTO.Member.UserNameSearch;
import com.sumit.StackGen.Entities.UserSearchDocument;
import com.sumit.StackGen.Repositories.UserSearchDocRepo;
import com.sumit.StackGen.Services.UserSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class UserSearchServiceImpl implements UserSearchService {
    UserSearchDocRepo userSearchDocRepo;
    @Override
    public List<UserNameSearch> search(String query) {
        List<UserSearchDocument> users=userSearchDocRepo.findByUsernameContaining(query);
        List<UserNameSearch> usernames=new ArrayList<>();
        for(UserSearchDocument doc:users){
            usernames.add(new UserNameSearch(doc.getUsername()));
        }
        return usernames;
    }
}
