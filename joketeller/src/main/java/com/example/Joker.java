package com.example;

import java.util.Random;

public class Joker{

    public String getJoke(){

        String jokesArr[] = {
                "Why did the scientist install a knocker on his door? He wanted to win the No-bell prize!"
                ,
                "British scientists have demonstrated that cigarettes can harm your children. Fair enough, use an ashtray."
                ,
                "Standing in the park, I was wondering why a Frisbee gets larger the closer it gets. Then it hit me."
                ,
                "I got an odd-job man in. He was useless. Gave him a list of eight things to do and he only did numbers one, three, five and seven."
                ,

                "So I rang up British Telecom, I said 'I want to report a nuisance caller', he said 'Not you again'."
                ,
                "I'm on a whiskey diet. I've lost three days already."
                ,
                "Doctor: I'm sorry but you suffer from a terminal illness and have only 10 to live.\n"+
                        "Patient: What do you mean, 10? 10 what? Months? Weeks?!\n"+ "Doctor: Nine."
                ,
                "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away."
                ,

                "My wife’s cooking is so bad we usually pray after our food."
                ,

                "Scientists have now discovered how women keep their secrets. They do so within groups of 40. ",

                "I'd like to buy a new boomerang please. Also, can you tell me how to throw the old one away?"
                ,

                "When my wife starts to sing I always go out and do some garden work so our neighbors can see there's no domestic violence going on. "

        };

        //Choosing a random joke id
        Random random = new Random();
        int randomJokeId = random.nextInt(jokesArr.length);

        return jokesArr[randomJokeId];



    }
}
