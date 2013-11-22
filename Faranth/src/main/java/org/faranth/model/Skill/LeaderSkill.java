package org.faranth.model.Skill;

import org.faranth.component.SkillFactor;
import org.faranth.component.Formation;
import org.faranth.model.Round;

/**
 * Created by quill on 20/11/13.
 */
public interface LeaderSkill {

    abstract public SkillFactor calculate(Round round, Formation formation);
}
