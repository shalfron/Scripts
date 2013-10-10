package us.scriptwith.util;

/**
 * Date: 6/17/13
 * Time: 8:44 PM
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