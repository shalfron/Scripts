package us.scriptwith.scripts.fishing.resources;

import org.powerbot.script.wrappers.Tile;

/**
 * Date: 9/15/13
 * Time: 4:48 PM
 */

public enum Location {
    BARBARIAN_VILLAGE(new Fish[]{Fish.PIKE, Fish.SALMON, Fish.TROUT},
            new Tile[]{
                    new Tile(3094, 3492, 0),
                    new Tile(3087, 3466, 0),
                    new Tile(3095, 3499, 0),
                    new Tile(3104, 3436, 0)
            },
            new Tile[]{
                    new Tile(3104, 3436, 0),
                    new Tile(3095, 3499, 0),
                    new Tile(3087, 3466, 0),
                    new Tile(3094, 3492, 0)
            }
    ),
    CATHERBY(new Fish[]{Fish.LOBSTER, Fish.SWORDFISH, Fish.SHARK},
            new Tile[]{new Tile(2848, 3430, 0)},
            new Tile[]{new Tile(2809, 3441, 0)}
    ),
    DRAYNOR(new Fish[]{Fish.SHRIMP, Fish.SARDINE, Fish.HERRING, Fish.ANCHOVIES},
            new Tile[]{new Tile(3088, 3235, 0)},
            new Tile[]{new Tile(3092, 3243, 0)}
    ),
    FISHING_GUILD(new Fish[]{Fish.LOBSTER, Fish.SWORDFISH, Fish.SHARK},
            new Tile[]{new Tile(2600, 3424, 0)},
            new Tile[]{new Tile(2585, 3423, 0)}
    ),
    PISCATORIS(new Fish[]{Fish.MONKFISH},
            new Tile[]{new Tile(2331, 3700, 0)},
            new Tile[]{new Tile(2329, 3689, 0)}
    );


    private Fish[] fish;
    private Tile[] toSpot, toBank;

    public Fish[] getFish() {
        return this.fish;
    }

    public Tile[] getPathToSpot() {
        return this.toSpot;
    }

    public Tile[] getPathToBank() {
        return this.toBank;
    }

    private Location(Fish[] fish, Tile[] toSpot, Tile[] toBank) {
        this.fish = fish;
        this.toBank = toBank;
        this.toSpot = toSpot;
    }
}
