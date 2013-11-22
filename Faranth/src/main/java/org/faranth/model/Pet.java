package org.faranth.model;

import org.faranth.model.Skill.ActiveSkill;
import org.faranth.model.Skill.AwokenSkill;
import org.faranth.model.Skill.LeaderSkill;

/**
 * Created by quill on 20/11/13.
 */
public class Pet {

    private static final int FIRE_HASH      = 0x1;
    private static final int WATER_HASH     = 0x2;
    private static final int WOOD_HASH      = 0x4;
    private static final int LIGHT_HASH     = 0x8;
    private static final int DARK_HASH      = 0x10;

    private static final int DRAGON_HASH            = 0x1;
    private static final int BANLANCED_HASH         = 0x2;
    private static final int GOD_HASH               = 0x4;
    private static final int DEVIL_HASH             = 0x8;
    private static final int ATTACKER_HASH          = 0x10;
    private static final int PYHSICAL_HASH          = 0x20;
    private static final int HEALER_HASH            = 0x40;
    private static final int EVO_MATERIAL_HASH      = 0x80;
    private static final int ENHANCE_MATERIAL_HASH  = 0x100;
    private static final int SPECIAL_PROTECTED_HASH = 0x200;

    private static final double NORMAL_GROWTH_RATE   = 1;
    private static final double LATE_GROWTH_RATE     = 0.75;
    private static final double EAILER_GROWTH_RATE   = 1.25;

    public enum Attribute {
        FIRE(FIRE_HASH),
        WATER(WATER_HASH),
        WOOD(WOOD_HASH),
        LIGHT(LIGHT_HASH),
        DARK(DARK_HASH);

        private final int value;

        private Attribute(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public enum Type {
        DRAGON(DRAGON_HASH),
        BANLANCED(BANLANCED_HASH),
        GOD(GOD_HASH),
        DEVIL(DEVIL_HASH),
        ATTACKER(ATTACKER_HASH),
        PYHSICAL(PYHSICAL_HASH),
        HEALER(HEALER_HASH),
        EVO_MATERIAL(EVO_MATERIAL_HASH),
        ENHANCE_MATERIAL(ENHANCE_MATERIAL_HASH),
        SPECIAL_PROTECTED(SPECIAL_PROTECTED_HASH);

        private final int value;

        private Type(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    public enum Growth {
        NORMAL,
        LATE,
        EARIL
    }

    // static information
    private int number;
    private String name;
    private Attribute[] attributes;
    private Type[] types;
    private int rarity;
    private int expToMaxLv;
    private int cost;
    private int startHP;
    private int startATK;
    private int startRCV;
    private int finalHP;
    private int finalATK;
    private int finalRCV;
    private Growth hpGrowth;
    private Growth atkGrowth;
    private Growth rcvGrowth;

    private ActiveSkill activeSkill;
    private LeaderSkill leaderSkill;
    private AwokenSkill[] awokenSkills;

    // data for formation
    private int currentLv;

    public int getAtk() {
        return 1245;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
        this.types = types;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getExpToMaxLv() {
        return expToMaxLv;
    }

    public void setExpToMaxLv(int expToMaxLv) {
        this.expToMaxLv = expToMaxLv;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStartHP() {
        return startHP;
    }

    public void setStartHP(int startHP) {
        this.startHP = startHP;
    }

    public int getStartATK() {
        return startATK;
    }

    public void setStartATK(int startATK) {
        this.startATK = startATK;
    }

    public int getStartRCV() {
        return startRCV;
    }

    public void setStartRCV(int startRCV) {
        this.startRCV = startRCV;
    }

    public int getFinalHP() {
        return finalHP;
    }

    public void setFinalHP(int finalHP) {
        this.finalHP = finalHP;
    }

    public int getFinalATK() {
        return finalATK;
    }

    public void setFinalATK(int finalATK) {
        this.finalATK = finalATK;
    }

    public int getFinalRCV() {
        return finalRCV;
    }

    public void setFinalRCV(int finalRCV) {
        this.finalRCV = finalRCV;
    }

    public Growth getHpGrowth() {
        return hpGrowth;
    }

    public void setHpGrowth(Growth hpGrowth) {
        this.hpGrowth = hpGrowth;
    }

    public Growth getAtkGrowth() {
        return atkGrowth;
    }

    public void setAtkGrowth(Growth atkGrowth) {
        this.atkGrowth = atkGrowth;
    }

    public Growth getRcvGrowth() {
        return rcvGrowth;
    }

    public void setRcvGrowth(Growth rcvGrowth) {
        this.rcvGrowth = rcvGrowth;
    }

    public ActiveSkill getActiveSkill() {
        return activeSkill;
    }

    public void setActiveSkill(ActiveSkill activeSkill) {
        this.activeSkill = activeSkill;
    }

    public LeaderSkill getLeaderSkill() {
        return leaderSkill;
    }

    public void setLeaderSkill(LeaderSkill leaderSkill) {
        this.leaderSkill = leaderSkill;
    }

    public AwokenSkill[] getAwokenSkills() {
        return awokenSkills;
    }

    public void setAwokenSkills(AwokenSkill[] awokenSkills) {
        this.awokenSkills = awokenSkills;
    }
}
