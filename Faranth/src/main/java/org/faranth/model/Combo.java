package org.faranth.model;

import java.util.ArrayList;

/**
 * Created by quill on 20/11/13.
 */
public class Combo {

    private ArrayList<Orb> orbes;
    private boolean isContainRow;
    private boolean[] matchMarker;
    private boolean[] tmpMatchMarker;

    public Combo() {
        orbes = new ArrayList<Orb>();
    }

    public void addOrb(Orb orb) {
        orbes.add(orb);
    }

    public Orb.Attribute getAttribute() {
        return orbes.get(0).getAttribute();
    }

    public int getOrbCount() {
        return orbes.size();
    }

    public int getEnhancedOrbEcount() {
        int cnt = 0;
        for (Orb o : orbes) {
            if (o.isEnhanced())
                cnt = cnt + 1;
        }
        return cnt;
    }

    public boolean isValid() {
        for (int i = 0; i < orbes.size() - 1; ++i) {
            if (orbes.get(i).getAttribute() != orbes.get(i + 1).getAttribute())
                return false;
        }

        cleanMatchMarker();
        cleanTmpMatchMarker();
        for (int i = 0; i < orbes.size(); ++i) {
            int findRet;
            Orb orb = orbes.get(i);

            cleanTmpMatchMarker();
            findRet = findMatchUp(orb.getX(), orb.getY(), 1);
            if (findRet < 2) {
                tmpMatchMarker[i] = true;
                joinMatchMarker();
            }

            cleanTmpMatchMarker();
            findRet = findMatchDown(orb.getX(), orb.getY(), 1);
            if (findRet < 2) {
                tmpMatchMarker[i] = true;
                joinMatchMarker();
            }

            cleanTmpMatchMarker();
            findRet = findMatchLeft(orb.getX(), orb.getY(), 1);
            if (findRet < 2) {
                tmpMatchMarker[i] = true;
                joinMatchMarker();
            }

            cleanTmpMatchMarker();
            findRet = findMatchRight(orb.getX(), orb.getX(), 1);
            if (findRet < 2) {
                tmpMatchMarker[i] = true;
                joinMatchMarker();
            }
        }

        return true;
    }

    public boolean isContainRow() {
        return isContainRow;
    }

    private int findMatchUp(int ox, int oy, int count) {
        if (oy < 0)
            return count;
        for (int i = 0; i < orbes.size(); ++i) {
            Orb orb = orbes.get(i);
            if (orb.getX() == ox && orb.getY() == oy - 1) {
                tmpMatchMarker[i] = true;
                return findMatchUp(ox, oy - 1, count + 1);
            }
        }
        return count;
    }

    private int findMatchDown(int ox, int oy, int count) {
        if (oy > 4)
            return count;
        for (int i = 0; i < orbes.size(); ++i) {
            Orb orb = orbes.get(i);
            if (orb.getX() == ox && orb.getY() == oy + 1) {
                tmpMatchMarker[i] = true;
                return findMatchUp(ox, oy + 1, count + 1);
            }
        }
        return count;
    }

    private int findMatchLeft(int ox, int oy, int count) {
        if (ox < 0) {
            if (count == 6)
                isContainRow = true;
            return count;
        }

        for (int i = 0; i < orbes.size(); ++i) {
            Orb orb = orbes.get(i);
            if (orb.getX() == ox - 1 && orb.getY() == oy) {
                tmpMatchMarker[i] = true;
                return findMatchUp(ox - 1, oy, count + 1);
            }
        }
        return count;
    }

    private int findMatchRight(int ox, int oy, int count) {
        if (ox > 5) {
            if (count == 6)
                isContainRow = true;
            return count;
        }

        for (int i = 0; i < orbes.size(); ++i) {
            Orb orb = orbes.get(i);
            if (orb.getX() == ox + 1 && orb.getY() == oy) {
                tmpMatchMarker[i] = true;
                return findMatchUp(ox + 1, oy, count + 1);
            }
        }
        return count;
    }

    private void cleanMatchMarker() {
        matchMarker = new boolean[orbes.size()];
    }

    private void cleanTmpMatchMarker() {
        tmpMatchMarker = new boolean[orbes.size()];
    }

    private void joinMatchMarker() {
        for (int i = 0; i < orbes.size(); ++i)
            matchMarker[i] = tmpMatchMarker[i] || matchMarker[i];
    }
}
