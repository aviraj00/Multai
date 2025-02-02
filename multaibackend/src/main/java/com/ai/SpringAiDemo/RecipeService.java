package com.ai.SpringAiDemo;



import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel=chatModel;
    }

    public String createRecipe(String ingredients,String cuisine,String dietaryRestrictions){
        var template = """
                I want to create recipe using the following ingredients:{ingredients}.The cuisine type i prefer is{cuisine}.
                please Consider the foloowing dietary restrictions:{dietaryRestrictions}.Please provide me with a detailde recipe ncluding title ,list of ingredients,and cooking instructions.
                """;

        PromptTemplate promptTemplate=new PromptTemplate(template);
        Map<String,Object> params=Map.of("ingredients",ingredients,"cuisine",cuisine,"dietaryRestrictions",dietaryRestrictions);

        Prompt prompt=promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
