package br.com.android.rafaelsermenho.testedextra;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.android.rafaelsermenho.testedextra.model.Ingredient;
import br.com.android.rafaelsermenho.testedextra.model.Sandwich;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class TesteDextraUnitTest {

    private static Ingredient alface;
    private static Ingredient bacon;
    private static Ingredient ovo;
    private static Ingredient queijo;
    private static Ingredient pao;
    private static Ingredient hamburguer;
    private static Sandwich xEggBacon;
    private static Sandwich xEgg;
    private static Sandwich xBurger;
    private static Sandwich xBacon;

    @Before
    public void buildObjects() {
        buildIngredientList();
        buildSandwichList();
    }

    private static void buildIngredientList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        alface = new Ingredient(1, "Alface", 0.40f, "https://goo.gl/9DhCgk");
        bacon = new Ingredient(2, "Bacon", 2.00f, "https://goo.gl/8qkVH0");
        hamburguer = new Ingredient(3, "Hamburguer de Carne", 3.00f, "https://goo.gl/U01SnT");
        ovo = new Ingredient(4, "'Ovo'", 0.80f, "https://goo.gl/weL1Rj");
        queijo = new Ingredient(5, "Queijo", 1.50f, "https://goo.gl/D69Ow2");
        pao = new Ingredient(6, "Pão com gergelim", 1.00f, "https://goo.gl/evgjyj");

        ingredientList.add(alface);
        ingredientList.add(bacon);
        ingredientList.add(hamburguer);
        ingredientList.add(ovo);
        ingredientList.add(queijo);
        ingredientList.add(pao);
    }

    private static void buildSandwichList() {
        List<Sandwich> sandwichList = new ArrayList<>();

        List<Ingredient> ingredientsOfXBacon = new ArrayList<>();
        ingredientsOfXBacon.add(bacon);
        ingredientsOfXBacon.add(hamburguer);
        ingredientsOfXBacon.add(queijo);
        ingredientsOfXBacon.add(pao);
        xBacon = new Sandwich(1, "X-Bacon", "https://goo.gl/W9WdaF");
        xBacon.setIngredientList(ingredientsOfXBacon);

        List<Ingredient> ingredientsOfXBurger = new ArrayList<>();
        ingredientsOfXBurger.add(hamburguer);
        ingredientsOfXBurger.add(queijo);
        ingredientsOfXBurger.add(pao);
        xBurger = new Sandwich(2, "X-Burger", "https://goo.gl/Cjfxi9");
        xBurger.setIngredientList(ingredientsOfXBurger);

        List<Ingredient> ingredientsOfXEgg = new ArrayList<>();
        ingredientsOfXEgg.add(hamburguer);
        ingredientsOfXEgg.add(ovo);
        ingredientsOfXEgg.add(queijo);
        ingredientsOfXEgg.add(pao);
        xEgg = new Sandwich(3, "X-Egg", "https://goo.gl/FqTafY");
        xEgg.setIngredientList(ingredientsOfXEgg);

        List<Ingredient> ingredientsOfXEggBacon = new ArrayList<>();
        ingredientsOfXEggBacon.add(alface);
        ingredientsOfXEggBacon.add(bacon);
        ingredientsOfXEggBacon.add(hamburguer);
        ingredientsOfXEggBacon.add(ovo);
        ingredientsOfXEggBacon.add(queijo);
        ingredientsOfXEggBacon.add(pao);
        xEggBacon = new Sandwich(4, "X-Egg Bacon", "https://goo.gl/8RM8s2");
        xEggBacon.setIngredientList(ingredientsOfXEggBacon);

        sandwichList.add(xBacon);
        sandwichList.add(xBurger);
        sandwichList.add(xEgg);
        sandwichList.add(xEggBacon);

    }

    @Test
    public void checkOrdinarySandwichPrice() throws Exception {
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(7.50f)));

        sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBurger.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(5.50f)));

        sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xEgg.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(6.30f)));

        sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xEggBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(8.70f)));
    }

    @Test
    public void checkSandwichPriceWithExtrasNoPromotion() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(alface);
        extraIngredient.add(ovo);

        xBacon.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(8.70f)));

        xEggBacon.setIngredientList(extraIngredient);
        sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xEggBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(9.90f)));
    }

    @Test
    public void checkLightPromotionWasApplied() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(alface);

        xBurger.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBurger.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(5.31f)));
    }

    @Test
    public void checkLightPromotionWasNotApplied() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(alface);

        xEggBacon.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xEggBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(9.10f)));
    }

    @Test
    /**
     * Test with 3 hamburger portions (2 extras + 1 from original ingredient)
     */
    public void checkMuitaCarnePromotionWasApplied_3Portions() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);

        xBacon.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(10.50f)));
    }

    @Test
    /**
     * Test with 5 hamburger portions (4 extras + 1 from original ingredient)
     */
    public void checkMuitaCarnePromotionWasApplied_4Portions() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);

        xBacon.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(19.50f)));
    }

    @Test
    /**
     * Test with 6 hamburger portions (5 extras + 1 from original ingredient)
     */
    public void checkMuitaCarnePromotionWasApplied_6Portions() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);
        extraIngredient.add(hamburguer);

        xBacon.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBacon.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(16.50f)));
    }

    @Test
    /**
     * Test with 3 cheese portions (2 extras + 1 from original ingredient)
     */
    public void checkMuitoQueijoPromotionWasApplied_3Portions() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);

        xBurger.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBurger.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(8.50f)));
    }

    @Test
    /**
     * Test with 5 cheese portions (4 extras + 1 from original ingredient)
     */
    public void checkMuitoQueijoPromotionWasApplied_4Portions() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);

        xBurger.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBurger.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(11.50f)));
    }

    @Test
    /**
     * Test with 6 cheese portions (5 extras + 1 from original ingredient)
     */
    public void checkMuitoQueijoPromotionWasApplied_6Portions() throws Exception {
        List<Ingredient> extraIngredient = new ArrayList<>();
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);
        extraIngredient.add(queijo);

        xBurger.setIngredientList(extraIngredient);
        float sandwichPrice = Float.valueOf(String.format(Locale.US, "%.2f", xBurger.getSandwichPrice()));
        assertThat(sandwichPrice, is(equalTo(13f)));
    }
}