package org.faranth.model;

import java.util.ArrayList;

/**
 * Created by quill on 20/11/13.
 */
public class Round {
    private ArrayList<Combo> combos;

    public int getComboCount() {
        return combos.size();
    }

    public ArrayList<Combo> getCombosByAttribute(Pet.Attribute attr) {
        ArrayList<Combo> targetCombo = new ArrayList<Combo>();
        for (Combo c : combos) {
            if (c.getAttribute().value() == attr.value()) {
                targetCombo.add(c);
            }
        }
        return targetCombo;
    }
}
