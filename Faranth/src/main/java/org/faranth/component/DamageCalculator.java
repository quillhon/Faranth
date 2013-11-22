package org.faranth.component;

import org.faranth.model.Combo;
import org.faranth.model.Pet;
import org.faranth.model.Round;
import org.faranth.model.Skill.ActiveSkill;
import org.faranth.model.Skill.AwokenSkill;
import org.faranth.model.Skill.LeaderSkill;

import java.util.ArrayList;

/**
 * Created by quill on 20/11/13.
 */
public class DamageCalculator {

    private static final int FORMATION_SIZE = 6;
    private static final double ORB_BOURNS_SCALE = 0.25;
    private static final double COMBO_BOURNS_SCALE = 0.25;
    private static final double ORB_ENHANCE_BOURNS = 0.06;
    private static final double SAME_VICE_ATTRIBUTE_SCALE = 0.1;
    private static final double DIFF_VICE_ATTRIBUTE_SCALE = 0.3;


    private PetDamage[] petDamages;
    private ArrayList<SkillFactor> skillFactors;

    private Round round;
    private Formation formation;

    private int roundComboCount;
    private double comboScale;

    class PetDamage {
        private Pet pet;
        private Damage mainAttributeDamge;
        private Damage viceAttributeDamge;

        public PetDamage(Pet pet) {
            this.pet = pet;
            this.mainAttributeDamge = new Damage(pet.getAttributes()[0], 0);
            this.viceAttributeDamge = new Damage(pet.getAttributes()[1], 0);
        }

        public Pet getPet() {
            return pet;
        }

        public void addMainDamage(int damage) {
            mainAttributeDamge.addDamage(damage);
        }

        public void scaleMainDamage(double scale) {
            mainAttributeDamge.scaleDamage(scale);
        }

        public void addViceDamage(int damage) {
            viceAttributeDamge.addDamage(damage);
        }

        public void scaleViceDamage(double scale) {
            viceAttributeDamge.scaleDamage(scale);
        }
    }

    class Damage {
        private Pet.Attribute attribute;
        private int damage;

        public Damage(Pet.Attribute attr, int damage) {
            this.attribute = attr;
            this.damage = damage;
        }

        public void addDamage(int damage) {
            this.damage = this.damage + damage;
        }

        public void scaleDamage(double scale) {
            this.damage = (int) Math.round(this.damage * scale);
        }
    }

    public DamageCalculator(Round round, Formation formation) {
        this.round = round;
        this.formation = formation;

        petDamages = new PetDamage[FORMATION_SIZE];
    }

    public void calaculateBaseDamage() {
        roundComboCount = round.getComboCount();
        comboScale = (1 + (roundComboCount - 1) * COMBO_BOURNS_SCALE);

        for (int i = 0; i < Formation.MAX_FORMATION_PETS; ++i) {
            petDamages[i] = new PetDamage(formation.getPets()[i]);
            calculateBaseDamageOfPet(i);
        }
    }

    public void calculateBaseDamageOfPet(int pos) {
        if (petDamages[pos] == null) {
            return;
        }

        Pet pet = petDamages[pos].getPet();

        Pet.Attribute mainAttr = pet.getAttributes()[0];
        ArrayList<Combo> mainAttrCombos = round.getCombosByAttribute(mainAttr);
        for (Combo c : mainAttrCombos) {
            int amountOfDamage = (int) Math.round(
                    pet.getAtk()
                    * (1 + (c.getOrbCount() - 3) * ORB_BOURNS_SCALE)
                    * (1 + c.getEnhancedOrbEcount() * ORB_ENHANCE_BOURNS)
            );
            petDamages[pos].addMainDamage(amountOfDamage);
            petDamages[pos].scaleMainDamage(comboScale);
        }

        if (pet.getAttributes()[1] != null) {
            double viceAttributeScale = pet.getAttributes()[0] == pet.getAttributes()[1] ? SAME_VICE_ATTRIBUTE_SCALE : DIFF_VICE_ATTRIBUTE_SCALE;
            Pet.Attribute viceAttr = pet.getAttributes()[0];
            ArrayList<Combo> viceAttrCombos = round.getCombosByAttribute(viceAttr);
            for (Combo c : viceAttrCombos) {
                int amountOfDamage = (int) Math.round(
                        pet.getAtk()
                        * (1 + (c.getOrbCount() - 3) * ORB_BOURNS_SCALE)
                        * (1 + c.getEnhancedOrbEcount() * ORB_ENHANCE_BOURNS)
                        * viceAttributeScale
                ) ;
                petDamages[pos].addViceDamage(amountOfDamage);
                petDamages[pos].scaleViceDamage(comboScale);
            }
        }
    }

    public void calculateAdditionalDamage() {
        skillFactors.clear();
        calculateActiveSkill();
        calculateLeaderSkillFactor();
        calculateAwokenSkill();

        for (SkillFactor sf : skillFactors) {
            for (PetDamage pd : petDamages) {
                int petTypeMask = 0x0;
                for (Pet.Type type : pd.getPet().getTypes()) {
                    petTypeMask = petTypeMask | type.value();
                }
                int skillFactorTypeMask = 0x0;
                for (Pet.Type type : sf.getTypes()) {
                    skillFactorTypeMask = skillFactorTypeMask | type.value();
                }
                if ((petTypeMask & skillFactorTypeMask) == 0) {
                    continue;
                }

                int skillFactorAttributeMask = 0x0;
                for (Pet.Attribute attr : sf.getAttributes()) {
                    skillFactorAttributeMask = skillFactorAttributeMask | attr.value();
                }

                if ( (pd.getPet().getAttributes()[0].value() & skillFactorAttributeMask) > 0) {
                    pd.scaleMainDamage(sf.getAtkScale());
                }

                if ( (pd.getPet().getAttributes()[1].value() & skillFactorAttributeMask) > 0) {
                    pd.scaleViceDamage(sf.getAtkScale());
                }
            }
        }
    }

    public void calculateActiveSkill() {
        for (Pet p : formation.getPets()) {
            SkillFactor sf = getFactorOfActiveSkill(p);
            if (sf != null) {
                skillFactors.add(sf);
            }
        }

    }

    public void calculateLeaderSkillFactor() {
        SkillFactor sf;
        sf = getFactorOfLeaderSkill(formation.getLeader());
        if (sf != null) {
            skillFactors.add(sf);
        }

        sf = getFactorOfLeaderSkill(formation.getLeader());
        if (sf != null) {
            skillFactors.add(sf);
        }
    }

    public void calculateAwokenSkill() {
        for (Pet p : formation.getPets()) {
            SkillFactor[] sfs = getFactorOfAwokenSkill(p);
            for (SkillFactor sf : sfs) {
                skillFactors.add(sf);
            }
        }
    }

    public SkillFactor getFactorOfActiveSkill(Pet pet) {
        ActiveSkill as = pet.getActiveSkill();
        return as.calculate(round, formation);
    }

    public SkillFactor getFactorOfLeaderSkill(Pet pet) {
        LeaderSkill ls = pet.getLeaderSkill();
        return ls.calculate(round, formation);
    }

    public SkillFactor[] getFactorOfAwokenSkill(Pet pet) {
        ArrayList<SkillFactor> dfs = new ArrayList<SkillFactor>();
        for (AwokenSkill ak : pet.getAwokenSkills()) {
            dfs.add(ak.calculate(round, formation));
        }
        SkillFactor[] dfsRet = new SkillFactor[dfs.size()];
        return dfs.toArray(dfsRet);
    }
}
