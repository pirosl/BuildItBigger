package com.udacity.gradle.javajokes;


import java.util.Random;

/**
 * Somple jokes class provider
 *
 * @author Lucian Piros
 */
public class Jokes {
    private static String[] jokes = {
            "How do you make a tissue dance? You put a little boogie in it.",
            "Why did the policeman smell bad? He was on duty.",
            "Why does Snoop Dogg carry an umbrella? FO DRIZZLE!",
            "Why can’t you hear a pterodactyl in the bathroom? Because it has a silent pee.",
            "What did the Zen Buddist say to the hotdog vendor? Make me one with everything.",
            "What kind of bees make milk instead of honey? Boobies.",
            "Horse walks into a bar. Bartender says, \"Why the long face?\"",
            "A mushroom walks into a bar. The bartender says, \"Hey, get out of here! We don’t serve mushrooms here\". Mushroom says, \"why not? I’m a fungai!\"",
            "I never make mistakes…I thought I did once; but I was wrong.",
            "What’s Beethoven’s favorite fruit?…Ba-na-na-naaa!",
            "What did the little fish say when he swam into a wall? DAM!",
            "Knock knock. Who’s there? Smell mop. (finish this joke in your head)",
            "Where does a sheep go for a haircut? To the baaaaa baaaaa shop!",
            "What does a nosey pepper do? Gets jalapeno business!"
    };

    public static String getJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}
