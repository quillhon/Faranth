package org.faranth.model.Skill;

import org.faranth.component.SkillFactor;
import org.faranth.component.Formation;
import org.faranth.model.Pet;
import org.faranth.model.Round;

import java.util.ArrayList;

/**
 * Created by quill on 21/11/13.
 */
public class BoostByAttributeLeaderSkill implements LeaderSkill {

    private ArrayList<Pet.Attribute> attributes;

    // Scale
    private float hpScale;
    private float atkScale;
    private float rcvScale;

    public BoostByAttributeLeaderSkill(
            ArrayList<Pet.Attribute> attrs,
            float hpScale,
            float atkScale,
            float rcvScale) {
        this.attributes = attrs;
        this.hpScale = hpScale;
        this.atkScale = atkScale;
        this.rcvScale = rcvScale;
    }

    @Override
    public SkillFactor calculate(Round round, Formation formation) {
        SkillFactor df = new SkillFactor();
        for (Pet.Attribute attr : attributes)
            df.addType(attr);
        df.setHpScale(hpScale);
        df.setAtkScale(atkScale);
        df.setRcvScale(rcvScale);
        return df;
    }
}
