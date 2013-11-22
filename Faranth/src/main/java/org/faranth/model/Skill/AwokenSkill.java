package org.faranth.model.Skill;

import org.faranth.component.SkillFactor;
import org.faranth.component.Formation;
import org.faranth.model.Round;

/**
 * Created by quill on 21/11/13.
 */
public interface AwokenSkill {
    abstract public SkillFactor calculate(Round round, Formation formation);
}
