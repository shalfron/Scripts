package us.scriptwith.util;

/**
 * Date: 6/17/13
 * Time: 8:44 PM
 */

public enum Food {
    NONE(-1),
    TROUT(333),
    PIKE(351),
    SALMON(329),
    TUNA(361),
    PLAIN_PIZZA(2289, 2291),
    LOBSTER(379),
    CAKE(1891, 1893, 1895),
    SWORDFISH(373),
    MEAT_PIZZA(2293, 2295),
    CHOCOLATE_CAKE(1897, 1899, 1901),
    ANCHOVY_PIZZA(2297, 2299),
    MONKFISH(7946),
    PINEAPPLE_PIZZA(2301, 2303),
    SHARK(385),
    TUNA_POTATO(7060),
    ROCKTAIL(15272);

    public int[] getIds() {
        return ids;
    }

    private int[] ids;

    private Food(int... ids) {
        this.ids = ids;
    }
}