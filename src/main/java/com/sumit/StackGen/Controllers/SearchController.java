package com.sumit.StackGen.Controllers;

import com.sumit.StackGen.DTO.Member.UserNameSearch;
import com.sumit.StackGen.Services.UserSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final UserSearchService userSearchService;
    @GetMapping
    public List<UserNameSearch> userNameSearches(@RequestParam String query){
        return userSearchService.search(query);
    }
}
