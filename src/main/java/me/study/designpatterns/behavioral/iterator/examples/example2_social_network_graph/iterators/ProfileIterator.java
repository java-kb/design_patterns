package me.study.designpatterns.behavioral.iterator.examples.example2_social_network_graph.iterators;

import me.study.designpatterns.behavioral.iterator.examples.example2_social_network_graph.profile.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}