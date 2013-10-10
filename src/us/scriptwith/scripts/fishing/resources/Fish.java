package us.scriptwith.scripts.fishing.resources;

/**
 * Date: 9/15/13
 * Time: 4:47 PM
 */

/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public enum Fish {
    // action to fish, item id, bait id (bait, feathers, etc), fishing spot Npc IDs
    SHRIMP("Net", 317, -1, 327),
    SARDINE("Bait", 327, 313, 327),
    HERRING("Bait", 345, 313, 327),
    ANCHOVIES("Net", 321, -1, 327),
    TROUT("Lure", 335, 314, 329),
    PIKE("Bait", 349, 313, 329),
    SALMON("Lure", 331, 314, 329),
    TUNA("Harpoon", 359, -1, 324),
    LOBSTER("Cage", 377, -1, 324),
    SWORDFISH("Harpoon", 371, -1, 324),
    MONKFISH("Net", 7944, -1, 3848),
    SHARK("Harpoon", 383, -1, 313, 322);

    private int fishId;
    private int baitId;
    private int[] fishSpotIds;
    private String action;

    public int getFishId() {
        return this.fishId;
    }

    public int getBaitId() {
        return this.baitId;
    }

    public int[] getSpotIds() {
        return this.fishSpotIds;
    }

    public String getAction() {
        return this.action;
    }

    public static int[] allFishIds() {
        int[] ids = new int[values().length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = values()[i].getFishId();
        }
        return ids;
    }

    private Fish(String action, int fishId, int baitId, int... fishSpotIds) {
        this.action = action;
        this.fishId = fishId;
        this.baitId = baitId;
        this.fishSpotIds = fishSpotIds;
    }
}
