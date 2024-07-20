package com.quizapp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quizapp.models.Question;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class QuestionLoader {
    public static List<Question> loadQuestions(String subject) {
        Gson gson = new Gson();
        Type questionListType = new TypeToken<List<Question>>(){}.getType();
        String filePath = "src/main/resources/questions/" + subject.toLowerCase() + "_questions.json";
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, questionListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
