package pages;

public enum PropertiesLabel {
	URL("url"), //
	KILL_BROWSER("killBrowser"),//
	USERNAME("username"), //
	PASSWORD("password"), //
	BROWSER("browser"); //
	
	private String propertiesLabel;

	private PropertiesLabel(String propertiesLabel) {
		this.setPropertiesLabel(propertiesLabel);
	}

	public String getPropertiesLabel() {
		return propertiesLabel;
	}

	public void setPropertiesLabel(String propertiesLabel) {
		this.propertiesLabel = propertiesLabel;
	}
}
