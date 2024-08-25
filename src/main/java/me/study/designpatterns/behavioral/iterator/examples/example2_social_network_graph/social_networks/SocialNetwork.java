package me.study.designpatterns.behavioral.iterator.examples.example2_social_network_graph.social_networks;

import me.study.designpatterns.behavioral.iterator.examples.example2_social_network_graph.iterators.ProfileIterator;

public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}
