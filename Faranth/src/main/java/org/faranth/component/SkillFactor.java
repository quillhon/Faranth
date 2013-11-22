package org.faranth.component;

import org.faranth.model.Pet;

import java.util.ArrayList;

/**
 * Created by quill on 21/11/13.
 */
public class SkillFactor {
    // Conditions
    private ArrayList<Pet.Type> types;
    private ArrayList<Pet.Attribute> attributes;

    // Scale
    private float hpScale;
    private float atkScale;
    private float rcvScale;

    public SkillFactor() {
        hpScale = 1;
        atkScale = 1;
        rcvScale = 1;

        types = new ArrayList<Pet.Type>();
        attributes = new ArrayList<Pet.Attribute>();
    }

    public void addType(Pet.Type type) {
        types.add(type);
    }

    public void addType(Pet.Attribute attr) {
        attributes.add(attr);
    }

    public void setHpScale(float scale) {
        hpScale = scale;
    }

    public void setAtkScale(float scale) {
        atkScale = scale;
    }

    public void setRcvScale(float scale) {
        rcvScale = scale;
    }

    public float getHpScale() {
        return hpScale;
    }

    public float getAtkScale() {
        return atkScale;
    }

    public float getRcvScale() {
        return rcvScale;
    }

    public ArrayList<Pet.Type> getTypes() {
        return types;
    }

    public ArrayList<Pet.Attribute> getAttributes() {
        return attributes;
    }
}
