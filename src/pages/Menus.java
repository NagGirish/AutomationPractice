package pages;

public interface Menus {

	public interface MainMenu {
		String WOMEN = "Women";
		String MAIN_DRESSES = "Dresses";
		String MAIN_T_SHIRT = "T-shirts";
	}

	public interface FirstLevelMenu {

		// Women
		String TOPS = MainMenu.WOMEN + ">" + "Tops";
		String T_SHIRTS = MainMenu.WOMEN + ">" + "T-shirts";
		String BLOUSE = MainMenu.WOMEN + ">" + "Blouses";
		String DRESSES = MainMenu.WOMEN + ">" + "Dresses";
		String WOMEN_CASUAL_DRESSES = MainMenu.WOMEN + ">" + "Casual Dresses";
		String WOMEN_EVENING_DRESSES = MainMenu.WOMEN + ">" + "Evening Dresses";
		String WOMEN_SUMMER_DRESSES = MainMenu.WOMEN + ">" + "Summer Dresses";

		// Dresses
		String CASUAL_DRESSES = MainMenu.MAIN_DRESSES + ">" + "Casual Dresses";
		String EVENING_DRESSES = MainMenu.MAIN_DRESSES + ">" + "Evening Dresses";
		String SUMMER_DRESSES=MainMenu.MAIN_DRESSES + ">" + "Summer Dresses";
	}
}
