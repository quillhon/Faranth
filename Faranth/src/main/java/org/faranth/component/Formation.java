package org.faranth.component;

import org.faranth.model.Pet;

/**
 * Created by quill on 21/11/13.
 */
public class Formation {

    public static final int LEADER_INDEX = 0;
    public static final int FRIEND_LEADER_INDEX = 5;
    public static final int MAX_FORMATION_PETS = 6;

    private Pet[] pets;

    public Formation() {
        pets = new Pet[MAX_FORMATION_PETS];
    }

    public void setLeader(Pet pet) {
        pets[LEADER_INDEX] = pet;
    }

    public void setMembers(int pos, Pet pet) {
        pets[pos - 1] = pet;
    }

    public void setFriendLeader(Pet pet) {
        pets[FRIEND_LEADER_INDEX] = pet;
    }

    public Pet getLeader() {
        return pets[LEADER_INDEX];
    }

    public Pet getMembers(int pos) {
        return pets[pos - 1];
    }

    public Pet getFriendLeader() {
        return pets[FRIEND_LEADER_INDEX];
    }

    public Pet[] getPets() {
        return pets;
    }
}
