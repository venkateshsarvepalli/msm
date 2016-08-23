package com.msm.helpers;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * Created by Venkatesh on 23/08/16.
 */@Component
public class JsonMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static class EateryList {}
    public static class RecipeList {}
    public static class BlogList {}
    public static class VoucherList {}
    public static class UserAccountList {}
    public static class BuddeeList {}
    public static class NotificationList {}

    @PostConstruct
    public void config() {
        objectMapper.registerModule(new Hibernate4Module());
    }

    public String map(Object obj) throws JsonProcessingException {
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return objectMapper.writeValueAsString(obj);
    }
}
